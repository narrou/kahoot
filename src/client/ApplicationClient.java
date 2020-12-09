package client;
import modele.*;
import serveur.Connexion;
import serveur.Serveur;

//TODO GERER LES SQL EXECPTION


import javax.swing.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ApplicationClient extends JFrame {
    private static int IDPORT=49513;
    private LoginForm log ;
    private MenuForm menu;
    private AttenteForm attente;
    private JeuForm jeu;
    private tableauScore finjeu;
    private KahootRequete provider;
    private List<Categorie> categorieList =new ArrayList<>();
    private List<String> tableauScore = new ArrayList<>();
    private Joueur joueur = null;
    private Partie maPartie;
    private Socket s1;
    private ObjectOutputStream out;
    private Serveur serv;

    public ApplicationClient(){
        try {
            provider= new KahootRequete();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        log = new LoginForm(this);
        menu = new MenuForm(this);
        attente = new AttenteForm(this);
        jeu = new JeuForm(this);
        finjeu=new tableauScore(this);
        setContentPane(log.getContentPane());
    }

    public void way(String actionCommand){

        switch (actionCommand) {

            case "S'inscrire":
                if (log.getLogininsc().getText().isEmpty() || log.getMdpinsc().getText().isEmpty()) {
                    log.getInfoLabel().setText("completer les deux champs");
                    this.pack();
                    break;
                }
                Joueur newJoueur = new Joueur(log.getLogininsc().getText(), log.getMdpinsc().getText());
                try {
                    provider.addjoueur(newJoueur);
                } catch (SQLException throwables) {
                    log.getInfoLabel().setText(throwables.getMessage());
                    this.pack();
                }
                joueur = newJoueur;

            case "Connexion": case  "Menu" :
                if (joueur == null) {
                    if (log.getLogin().getText().isEmpty() || log.getMdp().getText().isEmpty()) {
                        log.getInfoLabel().setText("completer les deux champs");
                        this.pack();
                        break;
                    }
                    try {
                        joueur = provider.getJoueur(log.getLogin().getText(), log.getMdp().getText());
                    } catch (SQLException throwables) {
                        log.getInfoLabel().setText(throwables.getMessage());
                        this.pack();
                    }
                }
                menu.getPseudo().setText(joueur.getLogin());
                updatecombobox(categorieList);
                setContentPane(menu.getContentPane());
                this.revalidate();
                this.pack();
                break;

            case "Ajouter JSON":
                final JFileChooser fc = new JFileChooser();
                int returnVal = fc.showOpenDialog(this);
                File file = fc.getSelectedFile();
                if(returnVal == 0)
                provider.remplirBdd(file.getAbsolutePath());
                updatecombobox(categorieList);
                this.revalidate();
                this.pack();
                break;

            case "Creer partie":
                serv= new Serveur(IDPORT, this);
                serv.start();
                attente.getReadyButton().setVisible(true);
                try {
                    this.s1 = new Socket(InetAddress.getLocalHost(), IDPORT);

                    this.out = new ObjectOutputStream(this.s1.getOutputStream());
                    this.out.writeObject(joueur);
                    Ecouteur ec = new Ecouteur(new ObjectInputStream(this.s1.getInputStream()), this);
                    ec.start();
                    Categorie c = (Categorie) menu.getComboBoxCat().getSelectedItem();

                    maPartie= provider.addPartie(joueur,IDPORT, c.getIdCat());
                    provider.addJoueurPartie(maPartie.getIdPartie(),joueur.getId());
                } catch (IOException | SQLException e) {
                    e.printStackTrace();
                }
                IDPORT++;
                attente.getCatname().setText(menu.getComboBoxCat().getSelectedItem().toString());
                attente.getSalleattentlabel().setText("Salle d'attente : "+maPartie.getCodePartie());


                setContentPane(attente.getContentPane());
                this.revalidate();
                this.pack();
                break;
            case "Rejoindre partie":
                String codejoin = menu.getJoinCode().getText();
                try {
                    ResultSet res = provider.getPartie(codejoin);
                    if (!res.isBeforeFirst() ) {
                       break;
                    }
                    res.next();
                    maPartie= new Partie(res.getInt("ID_PARTIE"),res.getString("code"),res.getInt("port"), res.getInt("ID_CATEGORIE"));
                    provider.addJoueurPartie(maPartie.getIdPartie(),joueur.getId());
                    try {
                        this.s1 = new Socket("192.168.43.58", res.getInt("port"));
                        this.out = new ObjectOutputStream(this.s1.getOutputStream());
                        this.out.writeObject(joueur);
                        Ecouteur ec = new Ecouteur(new ObjectInputStream(this.s1.getInputStream()), this);
                        ec.start();
                        setContentPane(attente.getContentPane());
                        attente.getCatname().setText(provider.getCategorie(maPartie.getIdCategorie()));
                        attente.getSalleattentlabel().setText("Salle d'attente : "+maPartie.getCodePartie());
                        attente.getReadyButton().setVisible(false);
                        this.revalidate();
                        this.pack();
                        break;

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            case  "Logout" :
                joueur =null;
                log.getInfoLabel().setText("");
                log.getLogin().setText("");
                log.getMdp().setText("");
                log.getLogininsc().setText("");
                log.getMdpinsc().setText("");
                setContentPane(log.getContentPane());
                this.revalidate();
                this.pack();
                break;


            case  "Ready" :
                try {
                    List<Question> ques = provider.getQuestion(maPartie.getIdCategorie());
                    Collections.shuffle(ques);
                    int i = ques.size()-1;
                    serv.envoyerQuestion(ques.get(i));
                    i--;
                    ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
                    for (i=i; i>=0;i--) {
                        jeu.enablebutton(true);
                        int finalI = i;
                        exec.schedule(new Runnable() {
                            public void run() {
                                serv.envoyerQuestion(ques.get(finalI));
                                try {
                                    Thread.sleep(15000);
                                    if (finalI==0){
                                        serv.isOver();
                                        serv.fermerSocketEcoute();
                                    }
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, 15, TimeUnit.SECONDS);

                    }


                    
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }


                break;
        }

        }
    public void updatecombobox(List<Categorie> categorieList){
        menu.getComboBoxCat().removeAllItems();
        try {
            categorieList = provider.getCategories();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        for (Categorie cat: categorieList) {
            menu.getComboBoxCat().addItem(cat);
        }
    }

        public void endgame(){
            try {
                tableauScore = provider.getTableauScore(maPartie.getIdPartie());
                for(int i=0; tableauScore.size()!=i;i++)
                    finjeu.getTextArea1().append(tableauScore.get(i)+"\n");
                setContentPane(finjeu.getContentPane());
                this.revalidate();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    public void afficherQuestion(Question q) throws SQLException {
        jeu.getNomJoueur().setText(joueur.getLogin());
        jeu.getCategorie().setText(q.getCategorie().getCategorie());
        jeu.getQuestion().setText(q.getTexteOption());
        jeu.getRepA().setText(q.getProposition().get(0).getTexteOption());
        jeu.getRepB().setText(q.getProposition().get(1).getTexteOption());
        jeu.getRepC().setText(q.getProposition().get(2).getTexteOption());
        jeu.getRepD().setText(q.getProposition().get(3).getTexteOption());
        jeu.getBonneReponse().setText(q.getBonneReponse().toString());
        jeu.getTimer().setText(Integer.toString(15));

        int score= provider.getScore(joueur.getId(),maPartie.getIdPartie());
        jeu.getScore().setText(Integer.toString(score));

        int i = 14;
        ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);

        for (i=i; i!=0;i--) {
            jeu.enablebutton(true);
            int finalI = i;
            exec.schedule(new Runnable() {
                public void run() {
                    jeu.getTimer().setText(Integer.toString(finalI));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, 1, TimeUnit.SECONDS);
        }

    }

    public boolean validation(String choix)
    {

        if (choix.equals(jeu.getBonneReponse().getText())) {
            try {
                provider.setScore(joueur.getId(),maPartie.getIdPartie());
                int score= provider.getScore(joueur.getId(),maPartie.getIdPartie());
                jeu.getScore().setText(Integer.toString(score));
                return true;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        return false;
    }

    public JeuForm getJeu() {
        return jeu;
    }

    public AttenteForm getAttente() {
        return attente;
    }

    public static void main(String[] args) {
        ApplicationClient f = new ApplicationClient();
        f.setVisible(true);
        f.pack();
        ApplicationClient f1 = new ApplicationClient();
        f1.setVisible(true);
        f1.pack();
       /* ApplicationClient f2= new ApplicationClient();
        f2.setVisible(true);
        f2.pack();
        ApplicationClient f3= new ApplicationClient();
        f3.setVisible(true);
        f3.pack();*/
    }
}






