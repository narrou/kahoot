package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JPanel {
    private JPanel contentPane;
    private JButton connectionButton;
    private JTextField logininsc;
    private JTextField mdpinsc;
    private JButton sInscrireButton;
    private JTextField login;
    private JTextField mdp;
    private ApplicationClient app ;

    public JTextField getLogininsc() {
        return logininsc;
    }

    public JTextField getMdpinsc() {
        return mdpinsc;
    }

    public JTextField getLogin() {
        return login;
    }

    public JTextField getMdp() {
        return mdp;
    }

    public  LoginForm(ApplicationClient application) {
        app=application;
        sInscrireButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               app.way(e.getActionCommand());
            }
        });
        connectionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                app.way(e.getActionCommand());
            }
        });
    }

    public JPanel getContentPane() {
        return contentPane;
    }

    public JButton getsInscrireButton() {
        return sInscrireButton;
    }


    /* public static void main(String[] args) {
        LoginForm dialog = new LoginForm();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }*/
}
