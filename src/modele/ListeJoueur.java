package modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListeJoueur implements Serializable {
    private List<Joueur> listJoueur;

    public ListeJoueur() {
        this.listJoueur = new ArrayList<>();
    }

    public void add(Joueur j){
        this.listJoueur.add(j);
    }

    public List<Joueur> getListJoueur() {
        return listJoueur;
    }

    public void clear(){
        this.listJoueur.clear();
    }
}
