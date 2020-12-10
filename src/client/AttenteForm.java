package client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AttenteForm extends JPanel {
    private JPanel contentPane;
    private JButton readyButton;
    private JLabel catname;
    private JTextArea listeJoueur;
    private JLabel salleattentlabel;
    private ApplicationClient app ;

    public JLabel getSalleattentlabel() {
        return salleattentlabel;
    }

    public JPanel getContentPane() {
        return contentPane;
    }

    public JLabel getCatname() {
        return catname;
    }

    public AttenteForm(ApplicationClient application) {
        app=application;
        readyButton.addActionListener(new ActionListener() {
            //Appel de la fonction de redirection
            public void actionPerformed(ActionEvent e) {
                app.way(e.getActionCommand());
            }
        });
    }

    public JButton getReadyButton() {
        return readyButton;
    }

    public JTextArea getListeJoueur() {
        return listeJoueur;
    }
}
