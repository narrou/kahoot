package client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JPanel {
    private JPanel contentPane;
    private JButton connexionButton;
    private JTextField logininsc;
    private JPasswordField mdpinsc;
    private JButton sInscrireButton;
    private JTextField login;
    private JPasswordField mdp;
    private JLabel InfoLabel;
    private JLabel ConnexionLabel;
    private ApplicationClient app ;

    public JLabel getInfoLabel() {
        return InfoLabel;
    }

    public JTextField getLogininsc() {
        return logininsc;
    }

    public JPasswordField getMdpinsc() {
        return mdpinsc;
    }

    public JTextField getLogin() {
        return login;
    }

    public JPasswordField getMdp() {
        return mdp;
    }

    public  LoginForm(ApplicationClient application) {
        app=application;
        sInscrireButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               app.way(e.getActionCommand());
            }
        });
        connexionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                app.way(e.getActionCommand());
            }
        });
    }

    public JPanel getContentPane() {
        return contentPane;
    }


     public static void main(String[] args) {


    }
}
