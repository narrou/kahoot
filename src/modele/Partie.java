package modele;

import java.util.ArrayList;
import java.util.List;

public class Partie {
    private int idPartie;
    private String codePartie;
    private int idCategorie;
    private int port;
    private List<Joueur> listJoueur;

    public Partie(int idPartie, String codePartie,int port, int idCategorie) {
       listJoueur= new ArrayList<>();
        this.idPartie = idPartie;
        this.codePartie = codePartie;
        this.port=port;
        this.idCategorie = idCategorie;
    }

    public int getPort() {
        return port;
    }

    public int getIdPartie() {
        return idPartie;
    }

    public String getCodePartie() {
        return codePartie;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public List<Joueur> getListJoueur() {
        return listJoueur;
    }
}
