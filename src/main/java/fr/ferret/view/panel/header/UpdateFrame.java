package fr.ferret.view.panel.header;

import fr.ferret.FerretMain;
import fr.ferret.controller.UpdateChecker;

import javax.swing.*;
import java.awt.*;

public class UpdateFrame extends JFrame
{
    private final Boolean[] checkedForUpdate = {false};
    private final JPanel updatePanel = new JPanel();
    private final JPanel updateBarHolder = new JPanel();
    private final JProgressBar updateProgressBar = new JProgressBar();
    private final JLabel updateLabel = new JLabel(FerretMain.getLocale().getString("update.checking"));

    public UpdateFrame() {
        super(FerretMain.getLocale().getString("update.title"));

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
        JLabel updateDetailLabel = new JLabel("");
        updateDetailLabel.setAlignmentX(CENTER_ALIGNMENT);
        updateBarHolder.add(updateProgressBar);
        updatePanel.add(updateBarHolder);
        JPanel updateButtonHolder = new JPanel();
        updatePanel.add(updateButtonHolder);
        updateButtonHolder.setLayout(new BoxLayout(updateButtonHolder, BoxLayout.X_AXIS));
        JButton updateOK = new JButton(FerretMain.getLocale().getString("settings.ok"));
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
                            String link = FerretMain.getLocale().getString("update.link");
                            LinkLabel ferretUpdate = new LinkLabel(link);
                            JLabel updateFerretLabel = new JLabel(FerretMain.getLocale().getString("update.msg"));
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
