package fr.ferret.view.panel.header;

import fr.ferret.FerretTest;
import fr.ferret.controller.settings.FerretConfig;
import fr.ferret.controller.settings.HumanGenomeVersions;
import fr.ferret.controller.settings.Phases1KG;
import fr.ferret.controller.settings.SettingsFrameController;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Hashtable;

/**
 * The ferret settings frame
 */
public class SettingsFrame extends JFrame {
    private final FerretConfig config;

    public SettingsFrame(FerretConfig config) {
        super(FerretTest.locale.getString("settings.title"));
        this.config = config;

        URL questionMarkURL = getClass().getResource("/questionMark25.png");
        ImageIcon questionMark = new ImageIcon(questionMarkURL);
        JLabel questionMarkLocusInput = new JLabel(questionMark); //TODO LINK WITH LOCUS PANEL
        JLabel questionMarkMAFThreshold = new JLabel(questionMark);

        JPanel settingsPanel = new JPanel();
        this.getContentPane().add(settingsPanel);

        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));
        settingsPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.setResizable(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JRadioButton[] phaseButtons = new JRadioButton[Phases1KG.values().length];
        JRadioButton[] humanVersionButtons = new JRadioButton[HumanGenomeVersions.values().length];

        //Phases
        {
            JLabel vcfVersionLabel = new JLabel(FerretTest.locale.getString("settings.genversion"));
            vcfVersionLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
            settingsPanel.add(vcfVersionLabel);

            ButtonGroup vcfRadioButtons = new ButtonGroup();
            for (int i = 0; i < phaseButtons.length; i++) {
                phaseButtons[i] = new JRadioButton(FerretTest.locale.getString("settings.phase." + Phases1KG.values()[i].name()));
                vcfRadioButtons.add(phaseButtons[i]);
                settingsPanel.add(phaseButtons[i]);
            }
            phaseButtons[config.getSelectedVersion().ordinal()].setSelected(true); //Default button
            phaseButtons[Phases1KG.NYGC_30X.ordinal()].setEnabled(false); //Disable NYGC : not implemented
            //TODO INCOMPATIBILITIES WITH HUMAN VERSION BUTTONS
        }

        //MAF
        //TODO IMPROVE CODE
        //TODO PGROU : une barre pour le min, une barre pour le max

        JSlider mafSlider = new JSlider(0, 5000, 0);
        JLabel MAFThresholdLabel = new JLabel(FerretTest.locale.getString("settings.mafthresold"));
        JLabel MAFOptionLabel = new JLabel(FerretTest.locale.getString("settings.maf"));

        JPanel mafPanel = new JPanel();
        JPanel mafESPPanel = new JPanel();

        //JCheckBox ESPMAF = new JCheckBox("Apply MAF threshold to the Exome Sequencing Project");
        //TODO bonus : GnomAD si le temps

        NumberFormat mafFormat = NumberFormat.getNumberInstance();
        mafFormat.setMaximumFractionDigits(4);
        final JFormattedTextField mafText = new JFormattedTextField(mafFormat);
        {
            settingsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            mafPanel.setAlignmentX(LEFT_ALIGNMENT);
            settingsPanel.add(MAFOptionLabel);
            MAFOptionLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
            settingsPanel.add(mafPanel);
            mafPanel.setLayout(new BoxLayout(mafPanel, BoxLayout.X_AXIS));
            mafPanel.add(MAFThresholdLabel);
            mafText.setColumns(5);
            mafText.setMaximumSize(mafText.getPreferredSize());
            mafPanel.add(mafText);

            mafSlider.setMajorTickSpacing(1000);
            mafSlider.setPaintTicks(true);
            Hashtable labelTable = new Hashtable();
            labelTable.put(new Integer(0), new JLabel("0.0"));
            labelTable.put(new Integer(5000), new JLabel("0.5"));
            mafSlider.setLabelTable(labelTable);
            mafSlider.setValue(0);
            mafSlider.setPaintLabels(true);
            mafPanel.add(mafSlider);

            SettingsFrameController.MafInputListener mafController = new SettingsFrameController.MafInputListener(mafText, mafSlider);
            mafText.addPropertyChangeListener(mafController);
            mafSlider.addChangeListener(mafController);
            mafText.setValue(config.getMafThreshold());

            mafPanel.add(questionMarkMAFThreshold);
            questionMarkMAFThreshold.setToolTipText(FerretTest.locale.getString("settings.maf.help"));
            mafPanel.add(Box.createHorizontalGlue());
            mafESPPanel.setLayout(new BoxLayout(mafESPPanel, BoxLayout.X_AXIS));
            mafESPPanel.setAlignmentX(LEFT_ALIGNMENT);
            //mafESPPanel.add(ESPMAF);
            settingsPanel.add(mafESPPanel);
        }

        //File output type
        settingsPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        JLabel filesLabel = new JLabel(FerretTest.locale.getString("settings.outfiles"));
        settingsPanel.add(filesLabel);
        filesLabel.setFont(new Font("SansSerif", Font.BOLD, 16));

        ButtonGroup fileOutputButtons = new ButtonGroup();
        JRadioButton allFilesButton = new JRadioButton(FerretTest.locale.getString("settings.out.frqmap"));
        JRadioButton freqFileButton = new JRadioButton(FerretTest.locale.getString("settings.out.frq"));
        JRadioButton vcfFileButton = new JRadioButton(FerretTest.locale.getString("settings.out.vcf"));
        {
            fileOutputButtons.add(allFilesButton);
            fileOutputButtons.add(freqFileButton);
            fileOutputButtons.add(vcfFileButton);
            settingsPanel.add(allFilesButton);
            settingsPanel.add(freqFileButton);
            settingsPanel.add(vcfFileButton);
            switch (config.getSelectedOutputType())
            {
                case ALL:
                    allFilesButton.setSelected(true);
                    break;
                case FRQ:
                    freqFileButton.setSelected(true);
                    break;
                case VCF:
                    vcfFileButton.setSelected(true);
                    break;
            }
        }

        //Human genome versions
        {
            JLabel hgVersionLabel = new JLabel(FerretTest.locale.getString("settings.hugversion"));
            settingsPanel.add(Box.createRigidArea(new Dimension(0, 5)));
            settingsPanel.add(hgVersionLabel);
            hgVersionLabel.setFont(new Font("SansSerif", Font.BOLD, 16));

            ButtonGroup hgVersionButtons = new ButtonGroup();
            for (int i = 0; i < humanVersionButtons.length; i++) {
                humanVersionButtons[i] = new JRadioButton(FerretTest.locale.getString("settings.hugen." + HumanGenomeVersions.values()[i].name()));
                hgVersionButtons.add(humanVersionButtons[i]);
                settingsPanel.add(humanVersionButtons[i]);
            }
            humanVersionButtons[config.getSelectedHumanGenome().ordinal()].setSelected(true); //Default button
        }

        //Ok/Cancel buttons

        JPanel settingsButtonPanel = new JPanel();
        JButton settingsOK = new JButton(FerretTest.locale.getString("settings.ok"));
        JButton settingsCancel = new JButton(FerretTest.locale.getString("settings.cancel"));

        settingsButtonPanel.setAlignmentX(LEFT_ALIGNMENT);
        settingsButtonPanel.setLayout(new BoxLayout(settingsButtonPanel, BoxLayout.X_AXIS));
        settingsPanel.add(settingsButtonPanel);
        settingsButtonPanel.add(Box.createHorizontalGlue());

        settingsCancel.addActionListener(new SettingsFrameController.CancelButtonListener(this));
        settingsButtonPanel.add(settingsCancel);

        settingsOK.addActionListener(new SettingsFrameController.SaveButtonListener(this, phaseButtons, humanVersionButtons, mafText, allFilesButton, freqFileButton, vcfFileButton));
        settingsButtonPanel.add(settingsOK);

        this.pack();
    }

    public FerretConfig getConfig() {
        return config;
    }
}
