package fr.ferret.view.panel;

import fr.ferret.FerretMain;
import fr.ferret.controller.RunButtonListener;
import fr.ferret.view.FerretFrame;

import javax.swing.*;
import java.awt.*;

/**
 * The panel with the run button of Ferret
 */
public class RunPanel extends JPanel {
    private final RunButtonListener listener;

    public RunPanel(FerretFrame frame) {
        JButton runButton = new JButton(FerretMain.getLocale().getString("run.button"));
        runButton.setPreferredSize(new Dimension(300, 60));
        runButton.setBackground(new Color(201, 157, 240));
        this.add(runButton);
        this.listener = new RunButtonListener(frame, runButton);
    }
}
