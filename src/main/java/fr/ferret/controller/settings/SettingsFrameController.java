package fr.ferret.controller.settings;

import fr.ferret.view.panel.header.SettingsFrame;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Groups all controllers for the {@link fr.ferret.view.panel.header.SettingsFrame}
 */
public class SettingsFrameController {
    /**
     * Cancel button click listener
     */
    public static class CancelButtonListener implements ActionListener
    {
        private final SettingsFrame settingsFrame;

        public CancelButtonListener(SettingsFrame settingsFrame) {
            this.settingsFrame = settingsFrame;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            settingsFrame.dispose();
        }
    }

    /**
     * Save button click listener
     */
    public static class SaveButtonListener implements ActionListener
    {
        private final SettingsFrame settingsFrame;
        private final JRadioButton[] phaseButtons;
        private final JRadioButton[] humanVersionButtons;
        private final JFormattedTextField mafText;
        private final JRadioButton allFilesButton;
        private final JRadioButton freqFileButton;
        private final JRadioButton vcfFileButton;

        public SaveButtonListener(SettingsFrame settingsFrame, JRadioButton[] phaseButtons, JRadioButton[] humanVersionButtons,
                                  JFormattedTextField mafText, JRadioButton allFilesButton, JRadioButton freqFileButton,
                                  JRadioButton vcfFileButton) {
            this.settingsFrame = settingsFrame;
            this.phaseButtons = phaseButtons;
            this.humanVersionButtons = humanVersionButtons;
            this.mafText = mafText;
            this.allFilesButton = allFilesButton;
            this.freqFileButton = freqFileButton;
            this.vcfFileButton = vcfFileButton;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
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
            settingsFrame.getConfig().setSelectedVersion(selected);

            settingsFrame.getConfig().setMafThreshold(((Number) mafText.getValue()).doubleValue());
            if (allFilesButton.isSelected()) {
                settingsFrame.getConfig().setSelectedOutputType(FileOutputType.ALL);
            } else if (freqFileButton.isSelected()) {
                settingsFrame.getConfig().setSelectedOutputType(FileOutputType.FRQ);
            } else if (vcfFileButton.isSelected()) {
                settingsFrame.getConfig().setSelectedOutputType(FileOutputType.VCF);
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
            settingsFrame.getConfig().setSelectedHumanGenome(selectedv);

            //TODO LINK WITH LOCUS PANEL : CONTROLLER ?
            /*if (settingsFrame.getConfig().getSelectedHumanGenome() == HumanGenomeVersions.V38) {
                questionMarkLocusInput.setToolTipText("hg38");
            } else if (settingsFrame.getConfig().getSelectedHumanGenome() == HumanGenomeVersions.V19) {
                questionMarkLocusInput.setToolTipText("hg19");
            }*/

            settingsFrame.dispose();
        }
    }

    /**
     * Maf inputs listener
     */
    public static class MafInputListener implements PropertyChangeListener, ChangeListener
    {
        private final JFormattedTextField mafText;
        private final JSlider mafSlider;

        public MafInputListener(JFormattedTextField mafText, JSlider mafSlider) {
            this.mafText = mafText;
            this.mafSlider = mafSlider;
        }

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            //Maf text input change
            double localMAFThreshold = ((Number) mafText.getValue()).doubleValue();
            if (localMAFThreshold > 0.5) {
                localMAFThreshold = 0.5;
                mafText.setValue(localMAFThreshold);
            } else if (localMAFThreshold < 0.0) {
                localMAFThreshold = 0.0;
                mafText.setValue(localMAFThreshold);
            }
            mafSlider.setValue((int) (localMAFThreshold * 10000));
        }

        @Override
        public void stateChanged(ChangeEvent e) {
            //Maf slider change
            double localMAFThreshold = mafSlider.getValue();
            mafText.setValue(localMAFThreshold / 10000);
        }
    }
}
