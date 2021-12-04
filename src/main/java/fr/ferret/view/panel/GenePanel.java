package fr.ferret.view.panel;

import fr.ferret.FerretTest;
import fr.ferret.controller.RunButtonListener;
import fr.ferret.view.FerretFrame;

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

        JButton browseButton = new JButton(FerretTest.locale.getString("gene.browse"));
        browseButton.setPreferredSize(new Dimension(200, 30));
        browseButton.setBackground(new Color(201, 157, 240));
        //RunButtonListener listener = new RunButtonListener(frame, browseButton);

        ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButton rdoName = new JRadioButton(FerretTest.locale.getString("gene.name"));
        JRadioButton rdoID = new JRadioButton(FerretTest.locale.getString("gene.ID"));

        JLabel lab_help_title = new JLabel(FerretTest.locale.getString("gene.help"));

        //Font f = lab_help_title.getFont();
        //lab_help_title.setFont(f.deriveFont(f.getStyle() | Font.BOLD));

        //TODO IMPROVE HELP
        JLabel lab_help = new JLabel("Entrer les coordonnées hg19 en bp de la version du génome humain.");
        JLabel helpLabel1 = new JLabel("Aide : Exemple : ''CCR5'' comme nom de gène ou ''1234'' comme ID de gène" + System.getProperty("line.separator")+
                "Exemple pour plusieurs gènes à la fois : ''CCR5, HCP5'' si on entre des noms ou ''1234,  10866'' si on entre des ID", SwingConstants.CENTER);

        GridBagConstraints c = new GridBagConstraints();
        // natural height, maximum width
      /*  c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.3;
        c.gridx = 1;
        c.gridy = 0;*/
        titleLabel.setFont(new Font("Calibri", Font.BOLD, 24));
        titleLabel.setForeground(new Color(18, 0, 127));
        this.add(titleLabel, BorderLayout.NORTH);


        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.4;
        c.gridx = 1;
        c.gridy = 1;
        inputPanel.add(inputStart, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.3;
        c.gridx = 2;
        c.gridy = 1;
        lab_or.setBorder(BorderFactory.createEmptyBorder(0, 130, 0, 10));
        lab_or.setFont(new Font(lab_or.getFont().getFontName(), Font.PLAIN, 16));
        inputPanel.add(lab_or, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.3;
        c.gridx = 3;
        c.gridy = 1;
        inputPanel.add(browseButton, c);


        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.3;
        c.gridx = 1;
        c.gridy = 2;
        lab_inputnameorid.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 10));
        lab_inputnameorid.setFont(new Font(lab_inputnameorid.getFont().getFontName(), Font.PLAIN, 16));
        inputPanel.add(lab_inputnameorid, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.3;
        c.gridx = 2;
        c.gridy = 2;
        rdoName.setSelected( true );
        rdoName.setFont(new Font(lab_inputnameorid.getFont().getFontName(), Font.PLAIN, 13));
        inputPanel.add( rdoName, c );
        buttonGroup.add( rdoName );
        //rdoName.addItemListener( this::radioButtons_itemStateChanged );

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.3;
        c.gridx = 3;
        c.gridy = 2;
        rdoID.setFont(new Font(lab_inputnameorid.getFont().getFontName(), Font.PLAIN, 13));
        inputPanel.add( rdoID , c);
        buttonGroup.add( rdoID );
        //rdoGreen.addItemListener( this::radioButtons_itemStateChanged );


        add(inputPanel, BorderLayout.CENTER);
        add(helpLabel1, BorderLayout.SOUTH);

        //Borders
        setBorder(BorderFactory.createLineBorder(new Color(131, 55, 192, 140), 4));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 20));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        helpLabel1.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
    }

    public JTextField getInputStart() {
        return inputStart;
    }

    public JTextField getInputEnd() {
        return inputEnd;
    }
}
