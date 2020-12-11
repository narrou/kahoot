package client;

import modele.Joueur;
import modele.ListeJoueur;
import modele.Question;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.SQLException;

public class Ecouteur extends Thread {
    private ObjectInputStream in;
    private ApplicationClient app;

    public Ecouteur(ObjectInputStream in, ApplicationClient app) {
        // On recupere l'input stream de la connexion et la fenêtre
        this.in = in;
        this.app = app;
    }

    public void run() {
        try {
            while (true) {
                // On a trois états dans une partie

                String verif =(String) in.readObject();
                if (verif.equals("NotReady")) {
                    // NotReady est la liste d'attente
                    ListeJoueur txt = (ListeJoueur) in.readObject();
                    if (txt != null) {
                        app.getAttente().getListeJoueur().setText("");
                        for (Joueur j : txt.getListJoueur()) {

                            app.getAttente().getListeJoueur().append(j.getLogin() + "\n");
                        }

                    }
                }
                else if (verif.equals("Ready")) {
                    // Ready est le jeu avec l'envoi des questions
                    app.setContentPane(app.getJeu().getContentPane());
                    app.revalidate();
                    app.setSize(700,500);
                    Question q = (Question) in.readObject();
                    app.afficherQuestion(q);

                }
                else if (verif.equals("Over")) {
                    // Over termine la partie
                    app.endgame();
                }

            }
        } catch (IOException | SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

}
