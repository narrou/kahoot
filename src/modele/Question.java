package modele;
import java.util.ArrayList;
import java.util.List;

public class Question extends Option{
    private Categorie categorie ;
    private List<Reponse> proposition = new ArrayList<>();
    private Reponse bonneReponse;

    public Question(int noOption, String texteOption, Categorie categorie, List<Reponse> proposition, Reponse bonneReponse) {
        super(noOption, texteOption);
        this.categorie = categorie;
        setProposition(proposition);
        setBonneReponse(bonneReponse);
    }

    public boolean isCorrect(int pos){
        return bonneReponse== proposition.get(pos);
    }

    public List<Reponse> getProposition() {
        return new ArrayList<>(proposition);
    }

    public void setProposition(List<Reponse> propositions){
        //if(propositions.size() ==4)
        for(Reponse prop : propositions)
            if(!this.proposition.contains(prop))
                this.proposition.add(prop);
    }

    public Reponse getBonneReponse() {
        return bonneReponse;
    }

    public void setBonneReponse(Reponse bonneReponse){
        if(proposition!=null && proposition.contains(bonneReponse))
            this.bonneReponse=bonneReponse;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    @Override
    public String toString() {
        String res = categorie +"\n"+ super.getTexteOption() + "\n";
        for(Reponse rep : proposition)
            res += rep.toString() + "\n";

        return res;
    }
}
