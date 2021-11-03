package fr.ferret.view;

import fr.ferret.view.panel.HeaderPanel;
import fr.ferret.view.panel.LocusPanel;
import fr.ferret.view.panel.RegionPanel;

import javax.swing.*;

public class FerretFrame extends JFrame
{
    private final HeaderPanel headerPanel;
    private final LocusPanel locusPanel;
    private final RegionPanel regionPanel;

    public FerretFrame() {
        headerPanel = new HeaderPanel();
        locusPanel = new LocusPanel();
        regionPanel = new RegionPanel();

        setTitle("Ferret v3");

        JPanel panel = new JPanel();
        panel.add(locusPanel);
        panel.add(regionPanel);

        // affecte le panneau a la fenetre
        setContentPane(panel);
        pack();

        //maFrame.add(new JButton("Button 1")); //SwingConstants.CENTER
        setSize(800,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public LocusPanel getLocusPanel() {
        return locusPanel;
    }
}