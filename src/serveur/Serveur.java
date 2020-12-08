package serveur;

import client.ApplicationClient;
import modele.Joueur;
import modele.ListeJoueur;
import modele.Question;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Serveur extends Thread{
    private ServerSocket socEcoute;
    private List<Connexion> connexions = new ArrayList<>();
    private ListeJoueur listJoueurs = new ListeJoueur();
    private int port;
    private ApplicationClient app;

    public Serveur(int port, ApplicationClient app) {
        try {
            this.socEcoute = new ServerSocket(port);
            this.app = app;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized List<Connexion> getConnexions() {
        return connexions;
    }

    public void fermerSocketEcoute(){
        try {
            for (Connexion co: this.connexions){
                co.fermerSocket();
            }
            socEcoute.close();
        } catch (IOException e) {
            System.out.println("Socket fermé");
        }
    }

    private void envoyerMessages(){
        for (Connexion c : connexions){
            try {
                ObjectOutputStream oos = c.getOut();
                oos.reset();
                oos.writeObject("NotReady");
                oos.writeObject(listJoueurs);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void isOver(){
        for (Connexion c : connexions){
            try {
                ObjectOutputStream oos = c.getOut();
                oos.reset();
                oos.writeObject("Over");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    public void envoyerQuestion(Question q){
        for (Connexion c : connexions){
            try {
                ObjectOutputStream oos = c.getOut();
                oos.reset();
                oos.writeObject("Ready");
                oos.writeObject(q);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }




    public void run(){
        try {
            do {
                System.out.println("En attente de connexion");
                Socket co = this.socEcoute.accept();
                System.out.println("Connexion acceptée");
                ObjectOutputStream oos = new ObjectOutputStream(co.getOutputStream());
                ObjectInputStream ois = new ObjectInputStream(co.getInputStream());
                Joueur j = (Joueur) ois.readObject();
                Connexion c = new Connexion(co, oos, ois,j, this.app);
                listJoueurs.add(j);
                connexions.add(c);
                connexions.get(connexions.size()-1).start();
                envoyerMessages();
                System.out.println("Connexion démarrée");
                System.out.println(connexions.size());
            }while (true);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        finally {
            fermerSocketEcoute();
        }
    }

}
