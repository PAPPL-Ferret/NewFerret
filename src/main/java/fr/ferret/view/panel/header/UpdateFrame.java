package fr.ferret.view.panel.header;

import fr.ferret.FerretTest;
import fr.ferret.controller.UpdateChecker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.URISyntaxException;

public class UpdateFrame extends JFrame
{
    //TODO CLEAN
    final Boolean[] checkedForUpdate = {false};
    JPanel updatePanel = new JPanel();
    JPanel updateBarHolder = new JPanel();
    JPanel updateButtonHolder = new JPanel();
    JProgressBar updateProgressBar = new JProgressBar();
    JLabel updateLabel = new JLabel(FerretTest.locale.getString("update.checking"));
    JLabel updateDetailLabel = new JLabel("");
    JButton updateOK = new JButton(FerretTest.locale.getString("settings.ok"));

    public UpdateFrame() {
        super(FerretTest.locale.getString("update.title"));

        this.setResizable(true);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.getContentPane().add(updatePanel);
        updatePanel.setLayout(new BoxLayout(updatePanel, BoxLayout.Y_AXIS));
        updatePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        updatePanel.add(updateLabel);
        //updatePanel.add(updateDetailLabel);
        updatePanel.add(Box.createRigidArea(new Dimension(500, 0)));
        updateLabel.setAlignmentX(CENTER_ALIGNMENT);
        updateProgressBar.setIndeterminate(true);
        //updateBarHolder.add(updateDetailLabel);
        updateDetailLabel.setAlignmentX(CENTER_ALIGNMENT);
        updateBarHolder.add(updateProgressBar);
        updatePanel.add(updateBarHolder);
        updatePanel.add(updateButtonHolder);
        updateButtonHolder.setLayout(new BoxLayout(updateButtonHolder, BoxLayout.X_AXIS));
        updateButtonHolder.add(updateOK);
        updateOK.addActionListener(e -> UpdateFrame.this.dispose());
        this.pack();
    }
    
    public void showFrame(JFrame SNPFerret) {
        this.setLocationRelativeTo(SNPFerret);
        this.setVisible(true);

        if (!checkedForUpdate[0]) {
            checkedForUpdate[0] = true;
            final UpdateChecker updateWorker = new UpdateChecker();
            updateWorker.addPropertyChangeListener(arg01 -> {
                if (arg01.getPropertyName().equals("state")) {
                    if (arg01.getNewValue() == SwingWorker.StateValue.DONE) {
                        String updateReason = updateWorker.updateStatus();
                        Boolean urgentUpdate = updateWorker.urgentUpdate();
                        Boolean needUpdate = updateWorker.needUpdate();

                        if (urgentUpdate || needUpdate) {
                            updateLabel.setText(updateReason);
                            updateBarHolder.remove(updateProgressBar);
                            LinkLabel ferretUpdate = null;
                            try {
                                String link = FerretTest.locale.getString("update.link");
                                ferretUpdate = new LinkLabel(new URI(link), link);
                            } catch (URISyntaxException e) {
                                e.printStackTrace();
                            }
                            JLabel updateFerretLabel = new JLabel(FerretTest.locale.getString("update.msg"));
                            updateBarHolder.add(updateFerretLabel);
                            updateBarHolder.repaint();
                           // updateFerretLabel.setText("");
                           // updateFerretLabel.setText("Please update Ferret at:");
                            ferretUpdate.setBackgroundColor(updatePanel.getBackground());
                            ferretUpdate.init();
                            ferretUpdate.setAlignmentX(LEFT_ALIGNMENT);
                            ferretUpdate.setMaximumSize(ferretUpdate.getPreferredSize());
                            updateBarHolder.add(ferretUpdate);
                        } else {
                            updateLabel.setText("");
                            updateBarHolder.remove(updateProgressBar);
                            updateBarHolder.add(new JLabel(updateReason));
                        }
                    }
                }
            });
            updateWorker.execute();
        }
    }
}
