package client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuForm extends JPanel {
    private JPanel contentPane;
    private JTextField joinCode;
    private JTextField JSONfield;
    private JComboBox ComboBoxCat;
    private JButton rejoindrePartieButton;
    private JButton creerPartieButton;
    private JButton ajouterJSONButton;
    private JLabel pseudo;
    private JButton logoutButton;
    private JLabel erreurlabel;
    private ApplicationClient app ;

    public JTextField getJoinCode() {
        return joinCode;
    }

    public JLabel getErreurlabel() {
        return erreurlabel;
    }

    public JLabel getPseudo() {
        return pseudo;
    }

    public JTextField getJSONfield() {
        return JSONfield;
    }

    public JComboBox getComboBoxCat() {
        return ComboBoxCat;
    }

    public  MenuForm(ApplicationClient application) {
        app=application;
        rejoindrePartieButton.addActionListener(new ActionListener() {
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
