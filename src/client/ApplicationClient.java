package client;
import modele.*;

import javax.swing.*;
import java.io.IOException;
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
                if(log.getLogininsc().getText().isEmpty() || log.getMdpinsc().getText().isEmpty()){
                    log.getInfoLabel().setText("completer les deux champs");
                    this.pack();
                    break;
                }
                    Joueur newJoueur = new Joueur(log.getLogininsc().getText(),log.getMdpinsc().getText());
                try {
                    provider.addjoueur(newJoueur);
                } catch (SQLException throwables) {
                    System.out.println("cc");
                    log.getInfoLabel().setText( throwables.getMessage());
                    this.pack();
                }
                coJoueur=newJoueur;
            case "Connection" :

                    if(coJoueur==null) {
                        if(log.getLogin().getText().isEmpty() || log.getMdp().getText().isEmpty()){
                            log.getInfoLabel().setText("completer les deux champs");
                            this.pack();
                            break;
                        }
                        try {
                            coJoueur= provider.getJoueur(log.getLogin().getText(),log.getMdp().getText());
                        } catch (SQLException throwables ) {
                            log.getInfoLabel().setText(throwables.getMessage());
                            this.pack();
                        }
                    }
                    menu.getPseudo().setText(coJoueur.getLogin());

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

            case  "Logout" :
                coJoueur=null;
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



    public static void main(String[] args) {

        ApplicationClient f = new ApplicationClient();
        f.setVisible(true);
        f.pack();

    }
}






