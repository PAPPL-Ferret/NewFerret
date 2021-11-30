package fr.ferret.view.panel;

import fr.ferret.FerretTest;
import fr.ferret.controller.RunButtonListener;
import fr.ferret.view.FerretFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class RunPanel extends JPanel {
    private final RunButtonListener listener;

    public RunPanel(FerretFrame frame) {
        GridBagLayout gestionnaire = new GridBagLayout();
        // applique le gestionnaire de placement au panneau
        this.setLayout(gestionnaire);

        JButton runButton = new JButton(FerretTest.locale.getString("run.button"));
        runButton.setPreferredSize(new Dimension(300, 60));
        runButton.setBackground(new Color(201, 157, 240));
        //JLabel fileLocation = new JLabel("File location: None Selected");

        /*GridBagConstraints c = new GridBagConstraints();
        c.fill =GridBagConstraints.HORIZONTAL;
        c.weightx =0.5;
        c.gridx =3;
        c.gridy =0;*/
        this.add(runButton);

        listener = new RunButtonListener(frame, runButton);
    }
}
