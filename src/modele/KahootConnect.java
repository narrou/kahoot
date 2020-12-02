package modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Julien
 */
public class KahootConnect{
    private static Connection connect;
    private static String url="jdbc:mysql://localhost:3306/kahoot3?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
    private static String user="root";
    private static String mdp="Marie0212";

    public KahootConnect() {
        try {
            this.RequeteKahoot();
        } catch (SQLException ex) {
            Logger.getLogger(KahootConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    public void RequeteKahoot() throws SQLException {
        connect = DriverManager.getConnection(url,user,mdp);
    }

    public static void main(String[] args) {
        KahootConnect k = new KahootConnect();
        try {
            k.RequeteKahoot();
        } catch (SQLException ex) {
            Logger.getLogger(KahootConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}