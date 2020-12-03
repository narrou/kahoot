package modele;

import java.io.Serializable;

public class Reponse extends Option implements Serializable {
    public Reponse(int noOption, String texteOption) {
        super(noOption, texteOption);
    }

    @Override
    public String toString() {
        return super.getTexteOption();
    }

    public static void main(String[] args) {

    }

}
