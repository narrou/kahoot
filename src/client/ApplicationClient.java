package client;
import modele.*;

import javax.swing.*;
import java.sql.SQLException;

public class ApplicationClient extends JFrame {
    LoginForm log ;
    MenuForm menu;
    KahootRequete provider ;
    public ApplicationClient(){
        try {
            provider= new KahootRequete();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        log = new LoginForm(this);
        menu = new MenuForm(this);
        setContentPane(log.getContentPane());
    }

    public void way(String actionCommand){
        Joueur coJoueur = null;
        System.out.println(actionCommand);

        switch (actionCommand) {
            case "S'inscrire" :
                Joueur newJoueur = new Joueur(log.getLogininsc().getText(),log.getMdpinsc().getText());
                try {
                    provider.addjoueur(newJoueur);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                coJoueur=newJoueur;
            case "Connection" :
                // menu = new MenuForm(this);
                try {
                    if(coJoueur==null)
                        coJoueur= provider.getJoueur(log.getLogin().getText(),log.getMdp().getText());
                        menu.getPseudo().setText(coJoueur.getLogin());
                        System.out.println(coJoueur.toString());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

                setContentPane(menu.getContentPane());
                this.revalidate();
                this.pack();
                break;
            case "Ajouter JSON":
                provider.remplirBdd(menu.getJSONfield().getText());
                menu.getJSONfield().setText("");
                this.revalidate();
                this.pack();
                break;

            case "Creer partie":

                break;
            case "Rejoindre partie":

                break;


            default:

        }
        }



    public static void main(String[] args) {

        ApplicationClient f = new ApplicationClient();
        f.setVisible(true);
        f.pack();

    }
}






