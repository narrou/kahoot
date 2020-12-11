package serveur;

import client.ApplicationClient;
import modele.Joueur;
import java.io.*;
import java.net.Socket;

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
        this.out = out;
        this.in = in;

        System.out.println("Fin création connexion");
    }

    public void run(){
            while (true) {
            }
    }


    public ObjectOutputStream getOut() {
        return out;
    }


    public void fermerSocket() throws IOException {
        this.socket.close();
    }
}
