package modele;
public class Joueur {
    private String  login;
    private String mdp;
    private int id;
    public Joueur(String login, String mdp) {
        this.login = login;
        this.mdp = mdp;
    }

    public Joueur(String login, String mdp, int id) {
        this.login = login;
        this.mdp = mdp;
        this.id = id;
    }

    public int getId() {
        return id;
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
