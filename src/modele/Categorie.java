package modele;
public class Categorie {
    private String categorie;
    private int idCat;

    public Categorie(String categorie, int idCat) {
        this.categorie = categorie;
        this.idCat = idCat;
    }

    public Categorie(String categorie) {
        this.categorie = categorie;
    }

    public Categorie(int idCat) {
        this.idCat = idCat;
    }

    public void setIdCat(int idCat) {
        this.idCat = idCat;
    }

    public int getIdCat() {
        return idCat;
    }

    public String getCategorie() {
        return categorie;
    }


    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        return  categorie +" id=("+ idCat+")";
    }
}


