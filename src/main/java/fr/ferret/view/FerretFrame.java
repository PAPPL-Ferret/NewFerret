package fr.ferret.view;

import fr.ferret.FerretMain;
import fr.ferret.view.panel.*;
import fr.ferret.view.panel.header.MenuPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.util.logging.Level;

/**
 * Main Ferret frame
 */
public class FerretFrame extends JFrame
{
    private final MenuPanel headerPanel;
    private final JTabbedPane inputTabs;
    private final LocusPanel locusPanel;
    private final RegionPanel regionPanel;
    private final RunPanel runPanel;
    private final GenePanel genePanel;
    private final VariantPanel variantPanel;

    public FerretFrame() {
        //Set look
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            FerretMain.getLog().log(Level.WARNING, "Failed to set ferret look and feel !", e);
        }
        //Set icon
        try {
            setIconImage(ImageIO.read(getClass().getResourceAsStream("/ferret.jpg")));
        } catch (IOException e) {
            FerretMain.getLog().log(Level.WARNING, "Failed to set ferret icon !", e);
        }

        headerPanel = new MenuPanel(this);
        locusPanel = new LocusPanel();
        genePanel = new GenePanel();
        variantPanel = new VariantPanel();
        regionPanel = new RegionPanel();
        runPanel = new RunPanel(this);

        //Créer le conteneur des onglets
        inputTabs = new JTabbedPane();
        //Définir la position de conteneur d'onglets
        inputTabs.setBounds(40,20,300,300);
        //Associer chaque panneau à l'onglet correspondant
        inputTabs.add("Locus", locusPanel);
        inputTabs.add("Gene", genePanel);
        inputTabs.add("Variant", variantPanel);

        setTitle("Ferret v3");
        setJMenuBar(headerPanel);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(inputTabs);
        panel.add(regionPanel);
        panel.add(runPanel);

        // affecte le panneau a la fenetre
        setContentPane(panel);
        pack();

        //maFrame.add(new JButton("Button 1")); //SwingConstants.CENTER
       // setSize(800,790);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public MenuPanel getHeaderPanel() {
        return headerPanel;
    }

    public JTabbedPane getInputTabs() {
        return inputTabs;
    }

    public LocusPanel getLocusPanel() {
        return locusPanel;
    }

    public GenePanel getGenePanel() {
        return genePanel;
    }

    public VariantPanel getVariantPanel() {
        return variantPanel;
    }

    public RegionPanel getRegionPanel() {
        return regionPanel;
    }

    public RunPanel getRunPanel() {
        return runPanel;
    }
}