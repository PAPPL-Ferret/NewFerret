package fr.ferret.view.panel.header;

import fr.ferret.FerretTest;

import javax.swing.*;
import java.awt.*;
import java.net.URI;
import java.net.URISyntaxException;

public class AboutFrame extends JFrame {
    public AboutFrame() {
        super(FerretTest.locale.getString("about.title"));
        JPanel aboutPanel = new JPanel();
        JLabel ferretVersionLabel = new JLabel(FerretTest.locale.getString("about.version"));
        JLabel ferretDateLabel = new JLabel(FerretTest.locale.getString("about.date"));
        JTextArea ferretCitation = new JTextArea(FerretTest.locale.getString("about.citation"), 4, 50);

        LinkLabel ferretWebLabelAbout = null;
        try{ //FIXME MAIS WAW
            ferretWebLabelAbout = new LinkLabel(new URI("http://limousophie35.github.io/Ferret/"),"http://limousophie35.github.io/Ferret/");
        }catch (URISyntaxException e){
            e.printStackTrace();
        }

        this.getContentPane().add(aboutPanel);
        this.setResizable(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        aboutPanel.setLayout(new BoxLayout(aboutPanel, BoxLayout.Y_AXIS));
        aboutPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        aboutPanel.add(ferretVersionLabel);
        aboutPanel.add(ferretDateLabel);
        aboutPanel.add(Box.createRigidArea(new Dimension(0, 12)));
        ferretWebLabelAbout.setBackgroundColor(aboutPanel.getBackground());
        ferretWebLabelAbout.init();
        ferretWebLabelAbout.setAlignmentX(LEFT_ALIGNMENT);
        ferretWebLabelAbout.setMaximumSize(ferretWebLabelAbout.getPreferredSize());
        aboutPanel.add(ferretWebLabelAbout);
        aboutPanel.add(Box.createRigidArea(new Dimension(0, 12)));
        ferretCitation.setAlignmentX(LEFT_ALIGNMENT);
        ferretCitation.setLineWrap(true);
        ferretCitation.setWrapStyleWord(true);
        ferretCitation.setBackground(aboutPanel.getBackground());
        ferretCitation.setMaximumSize(ferretCitation.getPreferredSize());
        aboutPanel.add(ferretCitation);
        this.pack();
    }
}
