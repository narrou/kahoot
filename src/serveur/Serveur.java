package serveur;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Serveur extends Thread{
    private ServerSocket socEcoute;
    private List<Connexion> connexions = new ArrayList<>();
    private int port;

    public Serveur(int port) {
        try {
            this.socEcoute = new ServerSocket(port);
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
    public void run(){
        try {
            do {
                System.out.println("En attente de connexion");
                Socket co = this.socEcoute.accept();
                System.out.println("Connexion acceptée");
                connexions.add(new Connexion(co));
                System.out.println("Connexion démarrée");
                connexions.get(connexions.size()-1).start();
                System.out.println(connexions.size());
            }while (true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            fermerSocketEcoute();
        }
    }

}
