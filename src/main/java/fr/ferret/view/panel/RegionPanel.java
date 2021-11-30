package fr.ferret.view.panel;

import fr.ferret.FerretTest;
import fr.ferret.view.region.Region;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Region panel <br>
 * Selection of the regions of the 1KG project
 */
public class RegionPanel extends JPanel {
    private final List<SubPanel> regions = new ArrayList<>();

    public RegionPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));

        JLabel label = new JLabel(FerretTest.locale.getString("region.input"), SwingConstants.LEFT);
        label.setFont(new Font("Calibri", Font.BOLD, 24));
        add(label, BorderLayout.NORTH);
        label.setForeground(new Color(18, 0, 127));

        JPanel container = new JPanel();
        container.setLayout(new GridLayout(2, 3));

        for (Region region : FerretTest.config.getSelectedVersion().getRegions()) {
            SubPanel panel = new SubPanel(region);
            regions.add(panel);
            container.add(panel);
        }

        add(container, BorderLayout.CENTER);
    }

    public List<SubPanel> getRegions() {
        return regions;
    }

    public class SubPanel extends JPanel {
        private final JCheckBox[] checkBoxes;
        private final Region region;

        public SubPanel(Region region) {
            JLabel label = new JLabel(FerretTest.locale.getString("region." + region.getName().toLowerCase(Locale.ROOT)));
            label.setFont(new Font("Calibri", Font.BOLD, 20));
            add(label);
            label.setForeground(new Color(131, 55, 192));

            this.checkBoxes = new JCheckBox[region.getZones().length];
            this.region = region;
            for (int i = 0; i < checkBoxes.length; i++) {
                checkBoxes[i] = new JCheckBox(region.getZones()[i] + " " +
                        FerretTest.locale.getString("region." + region.getZones()[i]) +
                        " (n=" + region.getIndividualCount()[i] + ")");
                if(i == 0) {
                    checkBoxes[i].setFont(new Font(checkBoxes[i].getFont().getFontName(), Font.BOLD, 14));
                } else {
                    checkBoxes[i].setFont(new Font(checkBoxes[i].getFont().getFontName(), Font.PLAIN, 14));
                }
                add(checkBoxes[i]);
                if (region.getIndividualCount()[i] == 0) {
                    checkBoxes[i].setEnabled(false);
                }
            }
            checkBoxes[0].addActionListener(action -> {
                boolean state = !checkBoxes[0].isSelected();
                setCheckBoxesState(1, state);

                if (region == FerretTest.config.getSelectedVersion().getRegions()[0]) {
                    for (SubPanel panel : RegionPanel.this.regions) {
                        if (panel != this) {
                            panel.setCheckBoxesState(0, state);
                        }
                    }
                }
            });
            setLayout(new GridLayout(9, 1));
        }

        private void setCheckBoxesState(int start, boolean state) {
            for (int i = start; i < checkBoxes.length; i++) {
                if (region.getIndividualCount()[i] != 0) {
                    checkBoxes[i].setEnabled(state);
                }
                checkBoxes[i].setSelected(false);
                checkBoxes[i].updateUI();
            }
        }

        public Region getRegion() {
            return region;
        }

        public JCheckBox[] getCheckBoxes() {
            return checkBoxes;
        }
    }
}
