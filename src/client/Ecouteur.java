package client;

import modele.Joueur;
import modele.ListeJoueur;
import modele.Question;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.SQLException;


//TODO A COMMENTER
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
                String verif =(String) in.readObject();
                if (verif.equals("NotReady")) {
                    ListeJoueur txt = (ListeJoueur) in.readObject();
                    if (txt != null) {
                        app.getAttente().getListeJoueur().setText("");
                        for (Joueur j : txt.getListJoueur()) {

                            app.getAttente().getListeJoueur().append(j.getLogin() + "\n");
                        }

                    }
                }
                else if (verif.equals("Ready")) {
                    app.setContentPane(app.getJeu().getContentPane());
                    app.revalidate();
                    app.setSize(700,500);
                    Question q = (Question) in.readObject();
                    app.afficherQuestion(q);

                }
                else if (verif.equals("Over")) {
                    app.endgame();
                }

            }
        } catch (IOException | SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

}
