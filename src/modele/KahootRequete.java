package modele;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.*;
import java.util.*;

public class KahootRequete {
    private static Scanner scanner = new Scanner(System.in);
    private static Connection connect;
    private static String url = "jdbc:mysql://localhost:3306/kahoot3?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
    private static String user = "root";
    private static String mdp = "";

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
    public Joueur getJoueur(String log , String mdp ) throws SQLException {
        Joueur leJoueur = null;
        String requete = "SELECT * FROM JOUEUR WHERE login = ? AND mdp = ? ";
        if (getNbJoueurs() > 0) {
            PreparedStatement pstnt = connect.prepareStatement(requete);
            pstnt.setString(1, log);
            pstnt.setString(2, mdp);
            ResultSet res = pstnt.executeQuery();
            while (res.next()) {
                leJoueur = new Joueur(res.getString("login"), res.getString("mdp"),res.getInt("idJOUEUR"));
            }
        }
        return leJoueur;
    }
    public ResultSet getPartie(String code) throws SQLException {
        String requete = "SELECT * FROM partie WHERE code LIKE ?";

        PreparedStatement pstnt = connect.prepareStatement(requete);
        pstnt.setString(1, code);
        ResultSet res = pstnt.executeQuery();
        return res;
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

    public Partie addPartie(Joueur Host,int port) throws SQLException {

            int leftLimit = 65; // letter 'A'
            int rightLimit = 90; // letter 'Z'
            int targetStringLength = 5;
            Random random = new Random();

            String generatedString = random.ints(leftLimit, rightLimit + 1)
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();

        String requete = "INSERT INTO partie (ID_HOTE,code,port) VALUES (?,?,?);";
        PreparedStatement pstnt = connect.prepareStatement(requete,Statement.RETURN_GENERATED_KEYS);
        pstnt.setInt(1, Host.getId());
        pstnt.setString(2, generatedString);
        pstnt.setInt(3, port);
        pstnt.executeUpdate();
        ResultSet res = pstnt.getGeneratedKeys();
        int id = 0;
        if (res.next()) {
            id = res.getInt(1);
        }
            return new Partie(id,generatedString,port) ;
    }
public void addJoueurPartie(int idpartie, int idjoueur)throws SQLException{
    String requete = "INSERT INTO joueur_partie (ID_PARTIE,idJOUEUR,SCORE) VALUES (?,?,0);";
    PreparedStatement pstnt = connect.prepareStatement(requete);
    pstnt.setInt(1,idpartie) ;
    pstnt.setInt(2, idjoueur);
    pstnt.executeUpdate();

}



    public void remplirBdd(String fic) {

        JSONParser parser = new JSONParser();

        try {
            KahootRequete maRequete = new KahootRequete();

            try {
                JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(fic));
                Categorie theme = new Categorie((String) jsonObject.get("thème"));
                int idCat = maRequete.addCategorie(theme);
                theme.setIdCat(idCat);
                JSONArray tableauFrDeb = (JSONArray) ((JSONObject) ((JSONObject) jsonObject.get("quizz")).get("fr")).get("expert");
                Iterator iteratorQuestion = tableauFrDeb.iterator();
                while (iteratorQuestion.hasNext()) {

                    JSONObject blocQuestion = (JSONObject) iteratorQuestion.next();
                    String question = (String) blocQuestion.get("question");
                    String stringBonneReponse = (String) blocQuestion.get("réponse");
                    Reponse bonneReponse = new Reponse(1, stringBonneReponse);
                    JSONArray tableauDeProp = (JSONArray) blocQuestion.get("propositions");
                    List<Reponse> propositions = new ArrayList<>();
                    int compteur = 1;
                    for (Object props : tableauDeProp) {
                        String proposition = (String) props;
                        if (proposition.equals(stringBonneReponse)) {
                            bonneReponse.setNoOption(compteur);
                            propositions.add(bonneReponse);
                            int idrep = maRequete.addReponse(bonneReponse);
                        } else {
                            Reponse a =new Reponse(compteur, proposition);
                            int idRep= maRequete.addReponse(a);
                            a.setNoOption(idRep);
                            propositions.add(a);
                        }
                        compteur++;
                    }

                    Long indexLong = (Long) blocQuestion.get("id");
                    int index = indexLong.intValue();
                    Question q = new Question(index,question, theme, propositions, bonneReponse);
                    int idquestion = maRequete.addQuestion(q, bonneReponse);
                    boolean prop = maRequete.addPropositions(q);


                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        } catch (SQLException throwables) {
            //    throwables.printStackTrace();
        }
    }

}



