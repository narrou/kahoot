package serveur;

import client.ApplicationClient;
import modele.Joueur;

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

    private void fermerSocketEcoute(){
        try {
            for (Connexion co: this.connexions){
                co.fermerSocket();
            }
            socEcoute.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void envoyerMessages(Joueur j){
        for (Connexion c : connexions){
            try {
                c.getOut().writeObject(j.getLogin());
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
                ObjectInputStream ois = new ObjectInputStream(co.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(co.getOutputStream());
                Joueur j = (Joueur) ois.readObject();
                System.out.println(j.getLogin());
                Connexion c = new Connexion(co, j, this.app);
                connexions.add(c);
                connexions.get(connexions.size()-1).start();
                envoyerMessages(j);
                System.out.println("Connexion démarrée");

                System.out.println(connexions.size());
            }while (true);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            fermerSocketEcoute();
        }
    }

}
