package fr.ferret.view.panel.header;

import fr.ferret.FerretTest;
import fr.ferret.controller.FerretConfig;

import javax.swing.*;
import java.awt.*;
import java.net.URI;

/**
 * Ferret frame menu
 */
public class MenuPanel extends JMenuBar {
    public MenuPanel(JFrame SNPFerret) {
        JMenu ferretMenu = new JMenu("Ferret");
        JMenu helpMenu = new JMenu(FerretTest.locale.getString("menu.help"));

        JMenuItem settingsMenuItem = new JMenuItem(FerretTest.locale.getString("settings.title"));
        JMenuItem updateMenuItem = new JMenuItem(FerretTest.locale.getString("update.title"));
        JMenuItem exitMenuItem = new JMenuItem(FerretTest.locale.getString("menu.quit"));
        JMenuItem aboutMenuItem = new JMenuItem(FerretTest.locale.getString("about.title"));
        JMenuItem faqMenuItem = new JMenuItem(FerretTest.locale.getString("menu.faq"));
        JMenuItem contactMenuItem = new JMenuItem(FerretTest.locale.getString("contact.title"));

        ferretMenu.add(settingsMenuItem);
        ferretMenu.add(updateMenuItem);
        ferretMenu.add(exitMenuItem);
        helpMenu.add(aboutMenuItem);
        helpMenu.add(faqMenuItem);
        helpMenu.add(contactMenuItem);
        this.add(ferretMenu);
        this.add(helpMenu);


        // update window
        UpdateFrame updateFrame = new UpdateFrame();
        updateMenuItem.addActionListener(arg0 -> {
            updateFrame.showFrame(SNPFerret);
        });

        //Settings pane:
        FerretConfig config = FerretTest.config;
        SettingsFrame settingsFrame = new SettingsFrame(config);
        settingsMenuItem.addActionListener(arg0 -> {
            settingsFrame.setLocationRelativeTo(SNPFerret);
            settingsFrame.setVisible(true);
        });

        //About window
        AboutFrame aboutFrame = new AboutFrame();
        aboutMenuItem.addActionListener(arg0 -> {
            aboutFrame.setLocationRelativeTo(SNPFerret);
            aboutFrame.setVisible(true);
        });

        //Contact window
        ContactFrame contactFrame = new ContactFrame();
        contactMenuItem.addActionListener(e -> {
            contactFrame.setLocationRelativeTo(SNPFerret);
            contactFrame.setVisible(true);
        });

        //Other action listeners
        exitMenuItem.addActionListener(arg0 -> System.exit(0));
        faqMenuItem.addActionListener(arg0 -> {
            try {
                //On met le lien dans la traduction : possible de faire des faq dans d'autres langues
                Desktop.getDesktop().browse(new URI(FerretTest.locale.getString("faq.link")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
