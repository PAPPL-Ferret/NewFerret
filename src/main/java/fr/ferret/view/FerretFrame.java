package fr.ferret.view;

import fr.ferret.FerretTest;
import fr.ferret.view.panel.header.MenuPanel;
import fr.ferret.view.panel.LocusPanel;
import fr.ferret.view.panel.RegionPanel;
import fr.ferret.view.panel.RunPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main Ferret frame
 */
public class FerretFrame extends JFrame
{
    private final MenuPanel headerPanel;
    private final LocusPanel locusPanel;
    private final RegionPanel regionPanel;
    private final RunPanel runPanel;

    public FerretFrame() {
        //Set look
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            FerretTest.log.log(Level.WARNING, "Failed to set ferret look and feel !", e);
        }
        //Set icon
        try {
            setIconImage(ImageIO.read(getClass().getResourceAsStream("/ferret.jpg")));
        } catch (IOException e) {
            FerretTest.log.log(Level.WARNING, "Failed to set ferret icon !", e);
        }

        headerPanel = new MenuPanel(this);
        locusPanel = new LocusPanel();
        regionPanel = new RegionPanel();
        runPanel = new RunPanel(this);

        setTitle("Ferret v3");
        setJMenuBar(headerPanel);

        JPanel panel = new JPanel();
        panel.add(locusPanel);
        panel.add(regionPanel);
        panel.add(runPanel);

        // affecte le panneau a la fenetre
        setContentPane(panel);
        pack();

        //maFrame.add(new JButton("Button 1")); //SwingConstants.CENTER
        setSize(800,790);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public MenuPanel getHeaderPanel() {
        return headerPanel;
    }

    public LocusPanel getLocusPanel() {
        return locusPanel;
    }

    public RegionPanel getRegionPanel() {
        return regionPanel;
    }

    public RunPanel getRunPanel() {
        return runPanel;
    }
}