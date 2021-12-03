package fr.ferret.view.panel;

import fr.ferret.FerretTest;

import javax.swing.*;
import java.awt.*;

/**
 * Locus panel <br>
 * Selection of the genes parameters
 */
public class GenePanel extends JPanel
{
    //private final JComboBox<String> chromosomeList;
    private final JTextField inputStart;
    private final JTextField inputEnd;

    public GenePanel() {
        this.setLayout(new BorderLayout());
        JPanel inputPanel = new JPanel();
        GridBagLayout gestionnaire = new GridBagLayout();
        // applique le gestionnaire de placement au panneau
        inputPanel.setLayout(gestionnaire);

        JLabel titleLabel = new JLabel(FerretTest.locale.getString("gene.input"), SwingConstants.LEFT);
        JLabel lab_or = new JLabel(FerretTest.locale.getString("gene.or"));

        JLabel lab_inputnameorid = new JLabel(FerretTest.locale.getString("gene.inputnameorid"));
        // lab_name = new JLabel(FerretTest.locale.getString("gene.name"));

        inputStart = new JTextField();
        inputEnd = new JTextField();

        JLabel lab_help_title = new JLabel(FerretTest.locale.getString("locus.help"));

        //Font f = lab_help_title.getFont();
        //lab_help_title.setFont(f.deriveFont(f.getStyle() | Font.BOLD));

        //TODO IMPROVE HELP
        JLabel lab_help = new JLabel("Entrer les coordonnées hg19 en bp de la version du génome humain.");
        JLabel helpLabel = new JLabel("Aide : Exemple pour CCR5: Chromosome: 3 Début: 46411633 Fin: 46417697", SwingConstants.CENTER);

        GridBagConstraints c = new GridBagConstraints();
        // natural height, maximum width
      /*  c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.3;
        c.gridx = 1;
        c.gridy = 0;*/
        titleLabel.setFont(new Font("Calibri", Font.BOLD, 24));
        titleLabel.setForeground(new Color(18, 0, 127));
        this.add(titleLabel, BorderLayout.NORTH);

        // natural height, maximum width
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.3;
        c.gridx = 1;
        c.gridy = 2;
        //lab_chromosome.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 10));
        //lab_chromosome.setFont(new Font(lab_chromosome.getFont().getFontName(), Font.PLAIN, 16));
        //panel.add(lab_chromosome, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.8;
        c.gridx = 2;
        c.gridy = 2;
        //panel.add(chromosomeList, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.3;
        c.gridx = 1;
        c.gridy = 3;
        lab_inputnameorid.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 10));
        lab_inputnameorid.setFont(new Font(lab_inputnameorid.getFont().getFontName(), Font.PLAIN, 16));
        inputPanel.add(lab_inputnameorid, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.8;
        c.gridx = 2;
        c.gridy = 3;
        inputPanel.add(inputStart, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.3;
        c.gridx = 1;
        c.gridy = 4;
        //lab_end.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 10));
        //lab_end.setFont(new Font(lab_end.getFont().getFontName(), Font.PLAIN, 16));
        //panel.add(lab_end, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.8;
        c.gridx = 2;
        c.gridy = 4;
        inputPanel.add(inputEnd, c);

        add(inputPanel, BorderLayout.CENTER);
        add(helpLabel, BorderLayout.SOUTH);

        //Borders
        setBorder(BorderFactory.createLineBorder(new Color(131, 55, 192, 140), 4));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 20));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        helpLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
    }

    public JTextField getInputStart() {
        return inputStart;
    }

    public JTextField getInputEnd() {
        return inputEnd;
    }
}
