package client;
import modele.*;
import serveur.Connexion;
import serveur.Serveur;

//TODO GERER LES SQL EXECPTION


import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApplicationClient extends JFrame {
    static int IDPORT=49513;
    LoginForm log ;
    MenuForm menu;
    AttenteForm attente;
    KahootRequete provider ;
    List<Categorie> categorieList =new ArrayList<>();
    Joueur joueur = null;
    Partie maPartie;
    Socket s1;
    ObjectOutputStream out;
    public ApplicationClient(){
        try {
            provider= new KahootRequete();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        log = new LoginForm(this);
        menu = new MenuForm(this);
        attente = new AttenteForm(this);
        setContentPane(log.getContentPane());
    }

    public void updatecombobox(List<Categorie> categorieList){
            menu.getComboBoxCat().removeAllItems();
        try {
            categorieList = provider.getCategories();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        for (Categorie cat: categorieList) {
            menu.getComboBoxCat().addItem(cat.getCategorie());
        }
}
    public void way(String actionCommand){

        System.out.println(actionCommand);

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

            case "Connection":
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
            case "Retour" :
                //TODO CLOSE LA PARTIE / SERV
                menu.getPseudo().setText(joueur.getLogin());
                updatecombobox(categorieList);
                setContentPane(menu.getContentPane());
                this.revalidate();
                this.pack();
                break;

            case "Ajouter JSON":
                provider.remplirBdd(menu.getJSONfield().getText());
                menu.getJSONfield().setText("");
                updatecombobox(categorieList);
                this.revalidate();
                this.pack();
                break;

            case "Creer partie":
                //TODO LANCER LE SERV ET TOUTE LA MIFA
                System.out.println("la");
                Serveur serv= new Serveur(IDPORT, this);
                serv.start();
                try {
                    this.s1 = new Socket(InetAddress.getLocalHost(), IDPORT);
                    System.out.println("pt");
                    this.out = new ObjectOutputStream(this.s1.getOutputStream());
                    System.out.println("rt");
                    this.out.writeObject(joueur);
                    maPartie= provider.addPartie(joueur,IDPORT);
                    provider.addJoueurPartie(joueur.getId(),maPartie.getIdPartie());
                } catch (IOException | SQLException e) {
                    e.printStackTrace();
                }
                IDPORT++;
                attente.getCatname().setText(menu.getComboBoxCat().getSelectedItem().toString());


                setContentPane(attente.getContentPane());
                this.revalidate();
                this.pack();
                break;
            case "Rejoindre partie":
                //TODO
                String codejoin = menu.getJoinCode().getText();
                try {
                    ResultSet res = provider.getPartie(codejoin);
                    if (!res.isBeforeFirst() ) {
                       break;
                    }
                    res.next();
                    maPartie= new Partie(res.getInt("ID_PARTIE"),res.getString("code"),res.getInt("port"));
                    provider.addJoueurPartie(joueur.getId(),maPartie.getIdPartie());
                    try {
                        this.s1 = new Socket(InetAddress.getLocalHost(), res.getInt("port"));
                        this.out = new ObjectOutputStream(this.s1.getOutputStream());
                        this.out.writeObject(joueur);
                        setContentPane(attente.getContentPane());
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
            default:

        }

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
    }
}






