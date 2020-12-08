package client;

import modele.Joueur;
import modele.ListeJoueur;
import modele.Question;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.SQLException;

public class Ecouteur extends Thread {
    private ObjectInputStream in;
    private ApplicationClient app;

    public Ecouteur(ObjectInputStream in, ApplicationClient app) {

        this.in = in;
        this.app = app;
    }

    public void run() {
        try {
            while (true) {
                System.out.println("En attente d'un nouveau joueur");
                String verif =(String) in.readObject();
                if (verif.equals("NotReady")) {
                    ListeJoueur txt = (ListeJoueur) in.readObject();
                    if (txt != null) {
                        System.out.println(txt.toString());
                        app.getAttente().getListeJoueur().setText("");
                        for (Joueur j : txt.getListJoueur()) {

                            app.getAttente().getListeJoueur().append(j.getLogin() + "\n");
                        }

                    }
                }
                else {
                    app.setContentPane(app.getJeu().getContentPane());
                    app.revalidate();
                    app.setSize(700,500);
                    while (true){
                        Question q = (Question) in.readObject();
                        app.afficherQuestion(q);
                    }

                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
