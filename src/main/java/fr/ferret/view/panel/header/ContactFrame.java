package fr.ferret.view.panel.header;

import fr.ferret.FerretTest;

import javax.swing.*;

public class ContactFrame extends JFrame {
    public ContactFrame() {
        super(FerretTest.locale.getString("contact.title"));

        JPanel contactPanel = new JPanel();
        JLabel contactPeopleLabel = new JLabel(FerretTest.locale.getString("contact.text"));
        JTextArea contactEmailLabel = new JTextArea(FerretTest.locale.getString("contact.mail"));

        this.getContentPane().add(contactPanel);
        this.setResizable(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        contactPanel.setLayout(new BoxLayout(contactPanel, BoxLayout.Y_AXIS));
        contactPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        contactPeopleLabel.setAlignmentX(CENTER_ALIGNMENT);
        contactPanel.add(contactPeopleLabel);
        contactEmailLabel.setAlignmentX(CENTER_ALIGNMENT);
        contactEmailLabel.setBackground(contactPanel.getBackground());
        contactPanel.add(contactEmailLabel);
        this.pack();
    }
}
