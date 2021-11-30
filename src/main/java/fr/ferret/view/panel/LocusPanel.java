package fr.ferret.view.panel;

import fr.ferret.FerretTest;

import javax.swing.*;
import java.awt.*;

/**
 * Locus panel <br>
 * Selection of the genes parameters
 */
public class LocusPanel extends JPanel
{
    private final JComboBox<String> chromosomeList;
    private final JTextField inputStart;
    private final JTextField inputEnd;

    public LocusPanel() {
        this.setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 20));
        JPanel panel = new JPanel();
        GridBagLayout gestionnaire = new GridBagLayout();
        // applique le gestionnaire de placement au panneau
        panel.setLayout(gestionnaire);

        JLabel lab_input = new JLabel(FerretTest.locale.getString("locus.input"), SwingConstants.LEFT);
        JLabel lab_chromosome = new JLabel(FerretTest.locale.getString("locus.chromosome"));

        String[] chromosomes = {" ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11",
                "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22"};

        //Create the combo box, select item at index 4.
        //Indices start at 0, so 4 specifies the pig.
        chromosomeList = new JComboBox<>(chromosomes);
        //dayList.setEditable(true);
        chromosomeList.setSelectedIndex(0);
        /*chromosomeList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                JComboBox dayList = (JComboBox) event.getSource();
            }
        });*/

        JLabel lab_start = new JLabel(FerretTest.locale.getString("locus.start"));
        JLabel lab_end = new JLabel(FerretTest.locale.getString("locus.end"));

        inputStart = new JTextField();
        inputEnd = new JTextField();

        JLabel lab_help_title = new JLabel(FerretTest.locale.getString("locus.help"));

        //Font f = lab_help_title.getFont();
        //lab_help_title.setFont(f.deriveFont(f.getStyle() | Font.BOLD));

        //TODO IMPROVE HELP
        JLabel lab_help = new JLabel("Entrer les coordonnées hg19 en bp de la version du génome humain.");
        JLabel lab_help_2 = new JLabel("Aide : Exemple pour CCR5: Chromosome: 3 Début: 46411633 Fin: 46417697", SwingConstants.CENTER);

        GridBagConstraints c = new GridBagConstraints();
        // natural height, maximum width
      /*  c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.3;
        c.gridx = 1;
        c.gridy = 0;*/
        lab_input.setFont(new Font("Calibri", Font.BOLD, 24));
        lab_input.setForeground(new Color(18, 0, 127));
        this.add(lab_input, BorderLayout.NORTH);

        // natural height, maximum width
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.3;
        c.gridx = 1;
        c.gridy = 2;
        lab_chromosome.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 10));
        lab_chromosome.setFont(new Font(lab_chromosome.getFont().getFontName(), Font.PLAIN, 16));
        panel.add(lab_chromosome, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.8;
        c.gridx = 2;
        c.gridy = 2;
        panel.add(chromosomeList, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.3;
        c.gridx = 1;
        c.gridy = 3;
        lab_start.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 10));
        lab_start.setFont(new Font(lab_start.getFont().getFontName(), Font.PLAIN, 16));
        panel.add(lab_start, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.8;
        c.gridx = 2;
        c.gridy = 3;
        panel.add(inputStart, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.3;
        c.gridx = 1;
        c.gridy = 4;
        lab_end.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 10));
        lab_end.setFont(new Font(lab_end.getFont().getFontName(), Font.PLAIN, 16));
        panel.add(lab_end, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.8;
        c.gridx = 2;
        c.gridy = 4;
        panel.add(inputEnd, c);

        add(panel, BorderLayout.CENTER);
        add(lab_help_2, BorderLayout.SOUTH);
    }

    public JComboBox<String> getChromosomeList() {
        return chromosomeList;
    }

    public JTextField getInputStart() {
        return inputStart;
    }

    public JTextField getInputEnd() {
        return inputEnd;
    }
}
