package client;

import javax.swing.*;

public class loginForm extends JDialog {
    private JPanel contentPane;
    private JButton connectionButton;
    private JTextField textField1;
    private JTextField textField2;
    private JButton sInscrireButton;
    private JTextField textField3;
    private JTextField textField4;
    private JButton buttonOK;

    public loginForm() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
    }

    public static void main(String[] args) {
        loginForm dialog = new loginForm();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
