package modele;
public class Reponse extends Option{
    public Reponse(int noOption, String texteOption) {
        super(noOption, texteOption);
    }

    @Override
    public String toString() {
        return super.getTexteOption();
    }

}
