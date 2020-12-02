package client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AttenteForm extends JPanel {
    private JPanel contentPane;
    private JButton retourButton;
    private JButton readyButton;
    private JLabel catname;
    private ApplicationClient app ;

    public JPanel getContentPane() {
        return contentPane;
    }

    public JLabel getCatname() {
        return catname;
    }

    public AttenteForm(ApplicationClient application) {
        app=application;
        readyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                app.way(e.getActionCommand());
            }
        });
    }

}
