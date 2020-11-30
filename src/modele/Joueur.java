package modele;
public class Joueur {
    private String  login;
    private String mdp;

    public Joueur(String login, String mdp) {
        this.login = login;
        this.mdp = mdp;
    }

    @Override
    public String toString() {
        return "Joueur{" +
                "login='" + login + '\'' +
                ", mdp='" + mdp + '\'' +
                '}';
    }

    public String getLogin() {
        return login;
    }

    public String getMdp() {
        return mdp;
    }
}
