package client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuForm extends JPanel {
    private JPanel contentPane;
    private JTextField textField2;
    private JTextField JSONfield;
    private JComboBox comboBox1;
    private JButton rejoindrePartieButton;
    private JButton creerPartieButton;
    private JButton ajouterJSONButton;
    private JLabel pseudo;
    private JButton logoutButton;
    private ApplicationClient app ;

    public JLabel getPseudo() {
        return pseudo;
    }

    public JTextField getJSONfield() {
        return JSONfield;
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
