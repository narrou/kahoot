package client;

import javax.swing.*;

public class JeuForm extends JDialog {
    private JPanel contentPane;
    private JButton aButton;
    private JButton bButton;
    private JButton cButton;
    private JButton dButton;
    private JButton buttonOK;

    public JeuForm() {
        setContentPane(contentPane);
        setModal(true);
    }

    public static void main(String[] args) {
        JeuForm dialog = new JeuForm();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
