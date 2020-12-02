package client;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Ecouteur extends Thread {
    private ObjectInputStream in;
    private JTextArea texte;

    public Ecouteur(ObjectInputStream in) {
        this.in = in;
    }

    public void setTexte(JTextArea texte) {
        this.texte = texte;
    }

    public void run() {
        try {
            while (true) {
                Object txt = in.readObject();
                if (txt != null){
                    System.out.println("EC : " + txt.toString());
                    texte.append(txt.toString());
                    texte.append("\n");
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
