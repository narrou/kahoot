package client;
import modele.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApplicationClient extends JFrame {
    LoginForm log ;
    MenuForm menu;
    AttenteForm attente;
    KahootRequete provider ;
    List<Categorie> categorieList =new ArrayList<>();
    Joueur coJoueur = null;
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
                    System.out.println("cc");
                    log.getInfoLabel().setText(throwables.getMessage());
                    this.pack();
                }
                coJoueur = newJoueur;
            case "Connection":

                if (coJoueur == null) {
                    if (log.getLogin().getText().isEmpty() || log.getMdp().getText().isEmpty()) {
                        log.getInfoLabel().setText("completer les deux champs");
                        this.pack();
                        break;
                    }
                    try {
                        coJoueur = provider.getJoueur(log.getLogin().getText(), log.getMdp().getText());
                    } catch (SQLException throwables) {
                        log.getInfoLabel().setText(throwables.getMessage());
                        this.pack();
                    }
                }
            case "Retour" :
                menu.getPseudo().setText(coJoueur.getLogin());
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
                attente.getCatname().setText(menu.getComboBoxCat().getSelectedItem().toString());
                setContentPane(attente.getContentPane());
                this.revalidate();
                this.pack();
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






