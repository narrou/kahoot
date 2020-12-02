package serveur;

import modele.Message;
import java.io.*;
import java.net.Socket;

public class Connexion extends Thread{
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Serveur serv;

    public Connexion(Socket socket) {
        try {
            this.socket = socket;
           // this.serv = s; TODO PLEURER
           this.out = new ObjectOutputStream(this.socket.getOutputStream());
  //          this.in = new ObjectInputStream(this.socket.getInputStream());

            System.out.println("Fin création connexion");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run(){
        try {
            while (true){
                Message txt = (Message) this.in.readObject();
                if (txt != null){
                    envoyerMessage(txt.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private synchronized void envoyerMessage(String message){
        for (Connexion co: serv.getConnexions()){
            try {
                co.out.writeObject(message);
                co.out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void fermerSocket() throws IOException {
        this.socket.close();
    }
}
