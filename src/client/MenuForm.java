package client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuForm extends JPanel {
    private JPanel contentPane;
    private JTextField joinCode;
    private JComboBox ComboBoxCat;
    private JButton rejoindrePartieButton;
    private JButton creerPartieButton;
    private JButton ajouterJSONButton;
    private JLabel pseudo;
    private JButton logoutButton;
    private ApplicationClient app ;

    public JTextField getJoinCode() {
        return joinCode;
    }

    public JLabel getPseudo() {
        return pseudo;
    }

    public JComboBox getComboBoxCat() {
        return ComboBoxCat;
    }

    public  MenuForm(ApplicationClient application) {
        app=application;
        rejoindrePartieButton.addActionListener(new ActionListener() {
            //Appel de la fonction de redirection
            public void actionPerformed(ActionEvent e) {
                app.way(e.getActionCommand());
            }
        });
        creerPartieButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                app.way(e.getActionCommand());
            }
        });
        ajouterJSONButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                app.way(e.getActionCommand());
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                app.way(e.getActionCommand());
            }
        });
        }


    public JPanel getContentPane() {
        return contentPane;
    }


}
