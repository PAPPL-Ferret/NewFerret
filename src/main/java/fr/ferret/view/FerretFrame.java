package fr.ferret.view;

import fr.ferret.view.panel.header.MenuPanel;
import fr.ferret.view.panel.LocusPanel;
import fr.ferret.view.panel.RegionPanel;
import fr.ferret.view.panel.RunPanel;

import javax.swing.*;

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
        headerPanel = new MenuPanel(this);
        locusPanel = new LocusPanel();
        regionPanel = new RegionPanel();
        runPanel = new RunPanel();

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
        setSize(800,725);
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
}