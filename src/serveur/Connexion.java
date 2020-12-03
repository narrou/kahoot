package serveur;

import client.ApplicationClient;
import modele.Joueur;
import modele.ListeJoueur;
import modele.Message;
import java.io.*;
import java.net.Socket;
import java.util.List;

public class Connexion extends Thread{
    private Socket socket;
    private Joueur joueur;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private ApplicationClient app;

    public Connexion(Socket socket, ObjectOutputStream out, ObjectInputStream in, Joueur joueur, ApplicationClient app) {
        this.socket = socket;
        this.joueur = joueur;
        this.app = app;
        // this.serv = s; TODO PLEURER
        this.out = out;
        this.in = in;

        System.out.println("Fin création connexion");
    }

    public void run(){
            while (true) {
                //System.out.println("on est la");
            }
    }


    public ObjectOutputStream getOut() {
        return out;
    }

    private synchronized void envoyerMessage(String message){

    }

    public void fermerSocket() throws IOException {
        this.socket.close();
    }
}
