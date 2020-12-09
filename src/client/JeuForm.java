package client;

import modele.Question;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JeuForm extends JDialog {
    private JPanel contentPane;
    private JButton aButton;
    private JButton bButton;
    private JButton cButton;
    private JButton dButton;
    private JLabel question;
    private JLabel repA;
    private JLabel repB;
    private JLabel repC;
    private JLabel repD;
    private JLabel categorie;
    private JLabel timer;
    private JLabel bonneReponse;
    private JLabel toStringBonneRep;
    private JLabel score;
    private JLabel toStringScore;
    private JLabel nomJoueur;
    private ApplicationClient app;

    public JeuForm(ApplicationClient app) {
        this.app = app;
        aButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               boolean v=  app.validation(getRepA().getText());
                if (v)
                    aButton.setBackground(Color.green);
                else
                    aButton.setBackground(Color.red);
                enablebutton(false);
            }
        });
        bButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean v=  app.validation(getRepB().getText());
                if (v)
                    bButton.setBackground(Color.green);
                else
                    bButton.setBackground(Color.red);
                enablebutton(false);
            }
        });
        cButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean v=  app.validation(getRepC().getText());
                if (v)
                    cButton.setBackground(Color.green);
                else
                    cButton.setBackground(Color.red);
                enablebutton(false);
            }
        });
        dButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean v=  app.validation(getRepD().getText());
                if (v)
                    dButton.setBackground(Color.green);
                else
                    dButton.setBackground(Color.red);
                enablebutton(false);
            }
        });
    }

    public void enablebutton(boolean b){
        toStringBonneRep.setVisible(!b);
        bonneReponse.setVisible(!b);
        aButton.setEnabled(b);
        bButton.setEnabled(b);
        cButton.setEnabled(b);
        dButton.setEnabled(b);
        if (b){
            aButton.setBackground(null);
            bButton.setBackground(null);
            cButton.setBackground(null);
            dButton.setBackground(null);
        }

    }
    public JLabel getTimer() {
        return timer;
    }

    public JLabel getQuestion() {
        return question;
    }

    public JLabel getRepA() {
        return repA;
    }

    public JLabel getBonneReponse() {
        return bonneReponse;
    }

    public JLabel getRepB() {
        return repB;
    }

    public JLabel getRepC() {
        return repC;
    }

    public JLabel getRepD() {
        return repD;
    }

    public JLabel getCategorie() {
        return categorie;
    }

    public JPanel getContentPane() {
        return contentPane;
    }

    public JLabel getNomJoueur() {
        return nomJoueur;
    }

    public JLabel getScore() {
        return score;
    }
}
