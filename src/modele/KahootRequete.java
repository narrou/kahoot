package modele;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class KahootRequete {
    private static Scanner scanner = new Scanner(System.in);
    private static Connection connect;
    private static String url = "jdbc:mysql://localhost:3306/kahoot3?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
    private static String user = "admin";
    private static String mdp = "Marie0212";

    public KahootRequete() throws SQLException {
        connect = DriverManager.getConnection(url, user, mdp);
    }

    public static int choixCategorie() {
        try {
            KahootRequete maRequete = new KahootRequete();
            List<Categorie> List = new ArrayList<>();

            int choix=0;
            System.out.println("Quel catégorie souhaitez-vous ?");
            List= maRequete.getCategories();
            for (Categorie cat: List) {
                System.out.println(cat);
            }
            choix = scanner.nextInt();
            return choix;
        } catch (SQLException throwables) { return -1 ; }

    }


    public List<Categorie> getCategories() throws SQLException {
        List<Categorie> List = new ArrayList<>();
        String requet = "SELECT * FROM categorie";
        Statement stmt = connect.createStatement();
        ResultSet res = stmt.executeQuery(requet);
        while (res.next()) {
            List.add(new Categorie(res.getString(2),res.getInt(1)));
        }
        res.close();
        stmt.close();
        return List;
    }

    public List<Question> getQuestion(int id) throws SQLException {
        List<Question> ListQ = new ArrayList<>();
        List<Reponse> ListR = new ArrayList<>();
        String requet = "SELECT question.ID_QUESTION,question.texteQUESTION,reponse.texteREPONSE,ID_CATEGORIE,bonneReponse,reponse.ID_REPONSE, categorie.texteCATEGORIE FROM question,propositions,reponse,categorie WHERE `ID_CATEGORIE`="+id+" AND question.ID_QUESTION =propositions.ID_QUESTION AND propositions.ID_REPONSE=reponse.ID_REPONSE AND categorie.idCATEGORIE=question.ID_CATEGORIE";
        Statement stmt = connect.createStatement();
        ResultSet res = stmt.executeQuery(requet);

        Categorie cat = new Categorie(id);
        Reponse bonne=null;
        while (res.next()) {
            int bonnerep=res.getInt("bonneReponse");
            int idRep=res.getInt("ID_REPONSE");
            String texteRep= res.getString("texteREPONSE");
            if(bonnerep==1){
                bonne = new Reponse(idRep,texteRep);
                ListR.add(bonne);
            }else{

                ListR.add(new Reponse(idRep,texteRep));

            }

            if(ListR.size()==4){
                String textcat = res.getString("texteCATEGORIE");
                cat.setCategorie(textcat);
                ListQ.add(new Question(res.getInt("ID_QUESTION"),res.getString("texteQUESTION"),cat,ListR,bonne));
                ListR.clear();
            }
        }

        res.close();
        stmt.close();
        return ListQ;
    }
    public int getNbJoueurs() throws SQLException {
        String requete = "SELECT count(*) AS NBJOUEURS FROM JOUEUR";
        Statement stmt = connect.createStatement();
        ResultSet res = stmt.executeQuery(requete);
        int countJoueurs = 0;
        while (res.next()) {
            countJoueurs = res.getInt("NBJOUEURS");
        }
        res.close();
        stmt.close();
        return countJoueurs;
    }

    public Joueur getJoueur(int idJoueur) throws SQLException {
        Joueur leJoueur = null;
        String requete = "SELECT * FROM JOUEUR WHERE idJoueur = ?";
        if (getNbJoueurs() > 0) {
            PreparedStatement pstnt = connect.prepareStatement(requete);
            pstnt.setInt(1, idJoueur);
            ResultSet res = pstnt.executeQuery();
            while (res.next()) {

                leJoueur = new Joueur(res.getString("login"), res.getString("mdp"));
            }
        }
        return leJoueur;
    }

    public int addjoueur(Joueur joueur) throws SQLException {
        String requete = "INSERT INTO JOUEUR(login,mdp) VALUES ('" + joueur.getLogin() + "','" + joueur.getMdp() + "')";
        PreparedStatement pstmt = connect.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
        pstmt.executeUpdate();
        ResultSet res = pstmt.getGeneratedKeys();
        int id = 0;
        if (res.next()) {
            id = res.getInt(1);
        }
        res.close();
        pstmt.close();
        return (id);
    }

    public int addCategorie(Categorie categorie) throws SQLException {
        String requete = "INSERT INTO CATEGORIE(texteCATEGORIE) VALUES ('" + categorie.getCategorie() + "')";
        PreparedStatement pstmt = connect.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
        pstmt.executeUpdate();
        ResultSet res = pstmt.getGeneratedKeys();
        int id = 0;
        if (res.next()) {
            id = res.getInt(1);
        }
        res.close();
        pstmt.close();
        return (id);
    }

    public int addReponse(Reponse reponse) throws SQLException {
        String requete = "INSERT INTO REPONSE(texteREPONSE) VALUES ('" + reponse.getTexteOption() + "')";
        PreparedStatement pstmt = connect.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
        pstmt.executeUpdate();
        ResultSet res = pstmt.getGeneratedKeys();
        int id = 0;
        if (res.next()) {
            id = res.getInt(1);
            reponse.setNoOption(id);
        }
        res.close();
        pstmt.close();
        return (id);
    }


    public int addQuestion(Question question, Reponse bonneReponse) throws SQLException {
        int idBonneReponse = bonneReponse.getNoOption();
        String requete = "INSERT INTO question(ID_BONNE_REPONSE, ID_CATEGORIE, texteQUESTION) VALUES (?, ?, ?)";
        PreparedStatement pstmt = connect.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, Integer.toString(idBonneReponse));
        pstmt.setString(2, Integer.toString(question.getCategorie().getIdCat()));
        pstmt.setString(3, question.getTexteOption());
        pstmt.executeUpdate();
        ResultSet res = pstmt.getGeneratedKeys();
        int id = 0;
        if (res.next()) {
            id = res.getInt(1);
            question.setNoOption(id);
        }
        res.close();
        pstmt.close();
        return id;

    }

    public boolean addPropositions(Question q) throws SQLException {
        String requete = "INSERT INTO propositions(ID_QUESTION,ID_REPONSE,bonneReponse) VALUES(?,?,?)";
        PreparedStatement pstmt = null;
        List<Reponse> lesReponses = q.getProposition();
        try {
            Iterator iterator = lesReponses.iterator();
            Reponse r;
            while (iterator.hasNext()) {
                r = (Reponse) iterator.next();
                pstmt = connect.prepareStatement(requete);
                pstmt.setInt(1, q.getNoOption()); // Identifiant de la question qui vient d'être créée
                pstmt.setInt(2, r.getNoOption()); // Identifiant de la réponse de cette proposition
                String a = q.getBonneReponse().getTexteOption();

                if (a.equals(r.getTexteOption())) {
                    pstmt.setInt(3, 1);
                } else {
                    pstmt.setInt(3, 0);
                }
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}



