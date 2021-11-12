package fr.ferret.view.panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class RunPanel extends JPanel {

    public RunPanel() {
        GridBagLayout gestionnaire = new GridBagLayout();
        // applique le gestionnaire de placement au panneau
        this.setLayout(gestionnaire);

        JFileChooser saveFileChooser = new JFileChooser();

        JButton runbutton = new JButton("Run Ferret, Run!");

        //JLabel fileLocation = new JLabel("File location: None Selected");

        runbutton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        String fileNameAndPath;
                        saveFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                        saveFileChooser.setDialogTitle("Save As");
                        int returnVal = saveFileChooser.showSaveDialog(RunPanel.this);
                        if(returnVal == JFileChooser.APPROVE_OPTION) {
                            File file = saveFileChooser.getSelectedFile();
                            fileNameAndPath = file.getAbsolutePath();
                            //fileLocation.setText("File Location: " + fileNameAndPath);
                        }
                    }
                });

        GridBagConstraints c = new GridBagConstraints();
        c.fill =GridBagConstraints.HORIZONTAL;
        c.weightx =0.5;
        c.gridx =3;
        c.gridy =0;
        this.add(runbutton);



    }
}
