package fr.ferret.view.panel.header;

import fr.ferret.FerretTest;
import fr.ferret.controller.settings.FerretConfig;
import fr.ferret.view.FerretFrame;

import javax.swing.*;
import java.awt.*;
import java.net.URI;

/**
 * Menu of the Ferret Frame
 */
public class MenuPanel extends JMenuBar {
    public MenuPanel(FerretFrame ferretFrame) {
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
        updateMenuItem.addActionListener(arg0 -> {
            new UpdateFrame().showFrame(ferretFrame);
        });

        //Settings pane:
        FerretConfig config = FerretTest.config;
        settingsMenuItem.addActionListener(arg0 -> {
            SettingsFrame settingsFrame = new SettingsFrame(ferretFrame, config);
            settingsFrame.setLocationRelativeTo(ferretFrame);
            settingsFrame.setVisible(true);
        });

        //About window
        aboutMenuItem.addActionListener(arg0 -> {
            AboutFrame aboutFrame = new AboutFrame();
            aboutFrame.setLocationRelativeTo(ferretFrame);
            aboutFrame.setVisible(true);
        });

        //Contact window
        contactMenuItem.addActionListener(e -> {
            ContactFrame contactFrame = new ContactFrame();
            contactFrame.setLocationRelativeTo(ferretFrame);
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
