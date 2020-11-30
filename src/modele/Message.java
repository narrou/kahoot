package modele;

import java.io.Serializable;

public class Message implements Serializable {
    private String nom;
    private String texte;

    public Message(String nom, String texte) {
        this.nom = nom;
        this.texte = texte;
    }

    public String getNom() {
        return nom;
    }

    public String getTexte() {
        return texte;
    }

    @Override
    public String toString() {
        return nom + ": " + texte;
    }
}
