package fr.ferret.controller;

import fr.ferret.FerretTest;
import fr.ferret.view.FerretFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Listens events of the run button and sends input data to the model
 */
public class RunButtonListener implements ActionListener {
    /**
     * The ferret frame
     */
    private final FerretFrame frame;

    /**
     * @param frame The ferret frame
     * @param runButton The button to listen
     */
    public RunButtonListener(FerretFrame frame, JButton runButton) {
        this.frame = frame;
        runButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser saveFileChooser = new JFileChooser();
        String fileNameAndPath;
        saveFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        saveFileChooser.setDialogTitle(FerretTest.locale.getString("run.save"));
        int returnVal = saveFileChooser.showSaveDialog(frame.getRunPanel());
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = saveFileChooser.getSelectedFile();
            fileNameAndPath = file.getAbsolutePath();
            //fileLocation.setText("File Location: " + fileNameAndPath);
            validateInfosAndRun(fileNameAndPath);
        }
    }

    private void validateInfosAndRun(String fileNameAndPath) {
        IInputController controller = null;
        switch (getFrame().getInputTabs().getSelectedIndex()) {
            case 0:
                controller = new LocusPanelController(frame, frame.getLocusPanel());
                break;
            case 1:
                controller = new GenePanelController(frame, frame.getGenePanel());
                break;
                //TODO VARIANT
        }
        controller.validateInfosAndRun(fileNameAndPath);
    }

    public FerretFrame getFrame() {
        return frame;
    }
}
