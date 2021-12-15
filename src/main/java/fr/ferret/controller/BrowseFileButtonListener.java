package fr.ferret.controller;

import fr.ferret.FerretTest;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Listens events of the run button and sends input data to the model
 */
public class BrowseFileButtonListener implements ActionListener {
    /**
     * todo
     */
    private final JPanel panel;
    private final JLabel selectedFileLabel;

    private File selectedFile;

    /**
     * @param panel The panel owning the button
     * @param runButton The button to listen
     * @param selectedFileLabel
     */
    public BrowseFileButtonListener(JPanel panel, JButton runButton, JLabel selectedFileLabel) {
        this.panel = panel;
        this.selectedFileLabel = selectedFileLabel;
        runButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser saveFileChooser = new JFileChooser();
        String fileNameAndPath;
        saveFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        saveFileChooser.setDialogTitle(FerretTest.locale.getString("run.save"));
        int returnVal = saveFileChooser.showSaveDialog(panel);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = saveFileChooser.getSelectedFile();
            fileNameAndPath = file.getAbsolutePath();
            selectedFileLabel.setText("Selected file: " + fileNameAndPath); //TODO TRANSLATE
            selectedFile = file;
        }
    }

    public File getSelectedFile() {
        return selectedFile;
    }

    public JPanel getPanel() {
        return panel;
    }

    public JLabel getSelectedFileLabel() {
        return selectedFileLabel;
    }
}