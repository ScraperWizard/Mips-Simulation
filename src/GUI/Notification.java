package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Notification extends JDialog {
    public Notification(JFrame parent, String message) {
        super(parent, true); // Set modal to true for a blocking dialog
        setLayout(new BorderLayout());
        JLabel label = new JLabel(message, SwingConstants.CENTER);
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the dialog when the close button is clicked
            }
        });

        // Add components to the dialog
        add(label, BorderLayout.CENTER);
        add(closeButton, BorderLayout.SOUTH);

        // Set size and location
        setSize(500, 200);
        setLocationRelativeTo(parent);
    }
}