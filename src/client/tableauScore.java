package client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class tableauScore extends JDialog {
    private JPanel contentPane;
    private JTextArea textArea1;
    private JButton menuButton;
    private ApplicationClient app;

    public tableauScore(ApplicationClient app) {
        this.app = app;
        menuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                app.way(e.getActionCommand());
            }
        });
    }

    public JTextArea getTextArea1() {
        return textArea1;
    }
}
