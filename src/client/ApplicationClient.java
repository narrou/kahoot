package client;
import modele.*;
import serveur.Serveur;


import javax.swing.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ApplicationClient extends JFrame {
    private static int IDPORT=49513; //Port disponnibles des parties
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
        //Affectation des forms sans oublier de spécifier l'applicationClient qui l'affecte
        log = new LoginForm(this);
        menu = new MenuForm(this);
        attente = new AttenteForm(this);
        jeu = new JeuForm(this);
        finjeu=new tableauScore(this);
        setContentPane(log.getContentPane()); //Récuperation du contenu de log
    }

    public void way(String actionCommand){
        //Cette fonction est appelée à chaque appuie sur un bouton,
        // elle permet de rediriger vers les bonnes commandes a réalisées
        switch (actionCommand) {

            case "S'inscrire":
                //Le code ci dessous permet d'ajouter un nouveau joueur a la bdd
                if (log.getLogininsc().getText().isEmpty() || log.getMdpinsc().getText().isEmpty()) {
                    log.getInfoLabel().setText("completer les deux champs");
                    this.pack();
                    break;
                }
                Joueur newJoueur = new Joueur(log.getLogininsc().getText(), log.getMdpinsc().getText());
                try {
                    provider.addjoueur(newJoueur);  // On add un joueur si les deux champs sont bien renseignés.
                } catch (SQLException throwables) {
                    log.getInfoLabel().setText(throwables.getMessage());
                    this.pack();
                }
                joueur = newJoueur; // On sauvegarde le joueur

            case "Connexion": case  "Menu" :
                //On s'occupe de verifié que les champs remplis sont corrects et on sauvegarde le joueur aussi
                if (joueur == null) { // Cas au l'utilisateur se connecte sans s'inscrire
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
                updatecombobox(categorieList); //Afficher les catégories
                setContentPane(menu.getContentPane()); //Recuperer le contenu de la page menu pour l'afficher
                this.revalidate(); //Les 2 lignes suivante sont obligatoire pour afficher la nouvelle fenetre
                this.pack();
                break;

            case "Ajouter JSON":
                //Add JSON
                final JFileChooser fc = new JFileChooser(); //Ouvrir une fenetre windows pour choisir un fichier
                int returnVal = fc.showOpenDialog(this);
                File file = fc.getSelectedFile();
                if(returnVal == 0) //Verifier si le fichier est correcte
                provider.remplirBdd(file.getAbsolutePath());
                updatecombobox(categorieList);
                this.revalidate();
                this.pack();
                break;

            case "Creer partie":
                serv= new Serveur(IDPORT, this);
                serv.start(); //Demarage du serv avec un port autoincrementé
                attente.getReadyButton().setVisible(true);
                try {
                    // On crée une connexion
                    this.s1 = new Socket(InetAddress.getLocalHost(), IDPORT);

                    this.out = new ObjectOutputStream(this.s1.getOutputStream());
                    this.out.writeObject(joueur);
                    // On envoie un objet joueur à toutes les connexions
                    Ecouteur ec = new Ecouteur(new ObjectInputStream(this.s1.getInputStream()), this);
                    ec.start();
                    Categorie c = (Categorie) menu.getComboBoxCat().getSelectedItem();

                    maPartie= provider.addPartie(joueur,IDPORT, c.getIdCat());
                    provider.addJoueurPartie(maPartie.getIdPartie(),joueur.getId());
                    // On ajoute dans la BDD la partie
                } catch (IOException | SQLException e) {
                    e.printStackTrace();
                }
                IDPORT++;
                attente.getCatname().setText(menu.getComboBoxCat().getSelectedItem().toString()); //Afficher la catégorie seletionner dans le combobox
                attente.getSalleattentlabel().setText("Salle d'attente : "+maPartie.getCodePartie());
                setContentPane(attente.getContentPane());
                this.revalidate();
                this.pack();
                break;
            case "Rejoindre partie":
                String codejoin = menu.getJoinCode().getText(); //Recuperation du code entré par le joueur
                try {
                    ResultSet res = provider.getPartie(codejoin); // Verifié si une partie a ce code
                    if (!res.isBeforeFirst() ) {
                       break;
                    }
                    res.next();
                   //Sauvegarde de la partie
                    maPartie= new Partie(res.getInt("ID_PARTIE"),res.getString("code"),res.getInt("port"), res.getInt("ID_CATEGORIE"));
                    provider.addJoueurPartie(maPartie.getIdPartie(),joueur.getId());
                    try {
                        // On crée une connexion
                        this.s1 = new Socket(InetAddress.getLocalHost(), res.getInt("port"));
                        this.out = new ObjectOutputStream(this.s1.getOutputStream());
                        this.out.writeObject(joueur);
                        Ecouteur ec = new Ecouteur(new ObjectInputStream(this.s1.getInputStream()), this);
                        ec.start();
                        setContentPane(attente.getContentPane());
                        attente.getCatname().setText(provider.getCategorie(maPartie.getIdCategorie()));
                        attente.getSalleattentlabel().setText("Salle d'attente : "+maPartie.getCodePartie());
                        attente.getReadyButton().setVisible(false);
                        // On ne montre le bouton ready qu'a l'hote
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
                //Se déconnecter + reset des champs
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
                    List<Question> ques = provider.getQuestion(maPartie.getIdCategorie()); //Get toute les questions de la bdd avec l'id Cat
                    Collections.shuffle(ques); //Mélange des questions
                    int i = ques.size()-1;
                    serv.envoyerQuestion(ques.get(i)); //Afficher la permière question avant d'attendre 15 sec
                    i--;
                    //Dans cette partie on crée un Threads qui envoie au serveur les questions toutes les 15 secondes
                    ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
                    for (i=i; i>=0;i--) {
                        //Décompte des question pour la list
                        jeu.enablebutton(true); //Activation des boutons
                        int finalI = i;
                        exec.schedule(new Runnable() {
                            public void run() {
                                serv.envoyerQuestion(ques.get(finalI));
                                try {
                                    Thread.sleep(15000);
                                    if (finalI==0){
                                        serv.isOver(); //Fin de partie plus de question on envoie a l'écouteur pour informer le serv
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
        //Remplir le comboBox qui liste les catégories
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
        //Fonction de fin de partie (affichage des scores)
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
        //Remplissage du jeu avec les questions, le nom du joueur, le timer, les reponses et la bonne reponse cachée
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
        //Threads Décompte du timer toutes les 1secondes
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
    //vérification que le bouton reponse appuyer est le bon (ou non) + 1pt en plus dans la bdd si bonne réponse
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
        //Création des joueurs
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






