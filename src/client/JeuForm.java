package client;

import javax.swing.*;

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
    private JButton buttonOK;
    private ApplicationClient app;

    public JeuForm(ApplicationClient app) {
        this.app = app;
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

}
