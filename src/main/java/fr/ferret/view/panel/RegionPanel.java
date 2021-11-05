package fr.ferret.view.panel;

import fr.ferret.FerretTest;
import fr.ferret.view.region.Region;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RegionPanel extends JPanel {
    private final List<SubPanel> regions = new ArrayList<>();

    public RegionPanel() {
        setLayout(new GridLayout(2, 3));
        setBorder(BorderFactory.createEmptyBorder(0, 20, 5, 20));

        for (Region region : Region.values()) {
            SubPanel panel = new SubPanel(region);
            regions.add(panel);
            add(panel);
        }
    }

    public List<SubPanel> getRegions() {
        return regions;
    }

    public class SubPanel extends JPanel {
        private final JCheckBox[] checkBoxes;
        private final Region region;

        public SubPanel(Region region) {
            JLabel label = new JLabel(FerretTest.locale.translate("region." + region.name().toLowerCase(Locale.ROOT))); //TODO TRADUIRE
            label.setFont(new Font("Serif", Font.BOLD, 20));
            add(label);

            this.checkBoxes = new JCheckBox[region.getZones().length];
            this.region = region;
            for (int i = 0; i < checkBoxes.length; i++) {
                checkBoxes[i] = new JCheckBox(region.getZones()[i] + " " +
                        FerretTest.locale.translate("region." + region.getZones()[i]) +
                        " (n=" + region.getIndividualCount()[i] + ")");
                add(checkBoxes[i]);
                if (region.getIndividualCount()[i] == 0) {
                    checkBoxes[i].setEnabled(false);
                }
            }
            checkBoxes[0].addActionListener(action -> {
                boolean state = !checkBoxes[0].isSelected();
                setCheckBoxesState(1, state);

                if (region == Region.ALL_POPULATIONS) {
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
