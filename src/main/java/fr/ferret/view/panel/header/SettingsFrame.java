package fr.ferret.view.panel.header;

import fr.ferret.FerretTest;
import fr.ferret.controller.FerretConfig;
import fr.ferret.controller.FileOutputType;
import fr.ferret.controller.HumanGenomeVersions;
import fr.ferret.controller.Phases1KG;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Hashtable;

public class SettingsFrame extends JFrame {
    //TODO VIEW CONTROLLER-ISATION


    public SettingsFrame(FerretConfig config) {
        super(FerretTest.locale.getString("settings.title"));

        //TODO CLEAN THIS
        final double[] mafThreshold = {0.0};

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
            mafText.setValue(new Double(0));
            mafPanel.add(mafText);

            mafText.addPropertyChangeListener(evt -> {
                double localMAFThreshold = ((Number) mafText.getValue()).doubleValue();
                if (localMAFThreshold > 0.5) {
                    localMAFThreshold = 0.5;
                    mafText.setValue(localMAFThreshold);
                } else if (localMAFThreshold < 0.0) {
                    localMAFThreshold = 0.0;
                    mafText.setValue(localMAFThreshold);
                }
                mafSlider.setValue((int) (localMAFThreshold * 10000));
            });

            mafSlider.setMajorTickSpacing(1000);
            mafSlider.setPaintTicks(true);
            Hashtable labelTable = new Hashtable();
            labelTable.put(new Integer(0), new JLabel("0.0"));
            labelTable.put(new Integer(5000), new JLabel("0.5"));
            mafSlider.setLabelTable(labelTable);
            mafSlider.setValue(0);
            mafSlider.setPaintLabels(true);
            mafPanel.add(mafSlider);
            mafSlider.addChangeListener(e -> {
                double localMAFThreshold = mafSlider.getValue();
                mafText.setValue(localMAFThreshold / 10000);
            });
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
            allFilesButton.setSelected(true);
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
        settingsButtonPanel.add(settingsCancel);
        settingsCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < phaseButtons.length; i++) {
                    JRadioButton button = phaseButtons[i];
                    button.setSelected(i == config.getSelectedVersion().ordinal());
                    button.setEnabled(true);
                }
                for (int i = 0; i < humanVersionButtons.length; i++) {
                    JRadioButton button = humanVersionButtons[i];
                    button.setSelected(i == config.getSelectedHumanGenome().ordinal());
                    button.setEnabled(true); //TODO CONFLICT THINGS
                }

                mafText.setValue(new Double(mafThreshold[0]));
                mafSlider.setValue((int) (mafThreshold[0] * 10000));

                allFilesButton.setSelected(config.getSelectedOutputType() == FileOutputType.ALL);
                freqFileButton.setSelected(config.getSelectedOutputType() == FileOutputType.FRQ);
                vcfFileButton.setSelected(config.getSelectedOutputType() == FileOutputType.VCF);
                vcfFileButton.setEnabled(true);

                SettingsFrame.this.dispose();
            }
        });
        settingsButtonPanel.add(settingsOK);

        settingsOK.addActionListener(e -> {
            Phases1KG selected = null;
            for (int i = 0; i < phaseButtons.length; i++) {
                JRadioButton button = phaseButtons[i];
                if (button.isSelected()) {
                    selected = Phases1KG.values()[i];
                    break;
                }
            }
            if (selected == null) {
                throw new IllegalStateException("No phases selected");
            }
            config.setSelectedVersion(selected);

            mafThreshold[0] = ((Number) mafText.getValue()).doubleValue();
            if (allFilesButton.isSelected()) {
                config.setSelectedOutputType(FileOutputType.ALL);
            } else if (freqFileButton.isSelected()) {
                config.setSelectedOutputType(FileOutputType.FRQ);
            } else if (vcfFileButton.isSelected()) {
                config.setSelectedOutputType(FileOutputType.VCF);
            }

            HumanGenomeVersions selectedv = null;
            for (int i = 0; i < humanVersionButtons.length; i++) {
                JRadioButton button = humanVersionButtons[i];
                if (button.isSelected()) {
                    selectedv = HumanGenomeVersions.values()[i];
                    break;
                }
            }
            if (selectedv == null) {
                throw new IllegalStateException("No human gene version selected");
            }
            config.setSelectedHumanGenome(selectedv);

            //TODO LINK WITH LOCUS PANEL : CONTROLLER ?
            if (config.getSelectedHumanGenome() == HumanGenomeVersions.V38) {
                questionMarkLocusInput.setToolTipText("hg38");
            } else if (config.getSelectedHumanGenome() == HumanGenomeVersions.V19) {
                questionMarkLocusInput.setToolTipText("hg19");
            }

            SettingsFrame.this.dispose();
        });

        this.pack();
    }
}
