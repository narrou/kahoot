package serveur;

import client.ApplicationClient;
import modele.Joueur;
import modele.Message;
import java.io.*;
import java.net.Socket;

public class Connexion extends Thread{
    private Socket socket;
    private Joueur joueur;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private ApplicationClient app;

    public Connexion(Socket socket, Joueur joueur, ApplicationClient app) {
        try {
            this.socket = socket;
            this.joueur = joueur;
            this.app = app;
           // this.serv = s; TODO PLEURER
            this.out = new ObjectOutputStream(this.socket.getOutputStream());
            this.in = new ObjectInputStream(this.socket.getInputStream());

            System.out.println("Fin création connexion");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        try {
            while (true) {
                String newLogin = (String) this.in.readObject();
                app.getAttente().getListeJoueur().append(newLogin);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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
