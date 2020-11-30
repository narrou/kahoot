package client;

import javax.swing.*;

public class AttenteForm extends JDialog {
    private JPanel contentPane;
    private JButton quitterButton;
    private JButton readyButton;


    public AttenteForm() {
        setContentPane(contentPane);
        setModal(true);
    }

    public static void main(String[] args) {
        AttenteForm dialog = new AttenteForm();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
