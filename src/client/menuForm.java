package client;

import javax.swing.*;

public class menuForm extends JDialog {
    private JPanel contentPane;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField pseudoTextField;
    private JComboBox comboBox1;
    private JButton rejoindrePartieButton;
    private JButton créerPartieButton;
    private JButton ajouterJSONButton;

    public menuForm() {
        setContentPane(contentPane);
        setModal(true);
    }

    public static void main(String[] args) {
        menuForm dialog = new menuForm();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
