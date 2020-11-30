package modele;
import java.util.Objects;

public class Option {
    private int noOption;
    private String texteOption;
    static private int nb;

    public boolean estUneQuestion() {
        return texteOption.contains("?");
    }

    public Option(int noOption, String texteOption) {
        this.noOption = noOption;
        this.texteOption = texteOption;
        nb++;
    }

    public static void setNb(int nb) {
        Option.nb = nb;
    }
    public void setNoOption(int noOption) {
        this.noOption = noOption;
    }
    public void setTexteOption(String texteOption) {
        this.texteOption = texteOption;
    }
    public String getTexteOption() {
        return texteOption;
    }

    public int getNoOption() {
        return noOption;
    }

    public static int getNb() {
        return nb;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Option option = (Option) o;
        return Objects.equals(texteOption, option.texteOption);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noOption, texteOption);
    }


}
