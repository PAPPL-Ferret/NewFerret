package fr.ferret.view.panel;

import fr.ferret.FerretMain;
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
    /**
     * Panels for each supported {@link Region}
     */
    private final List<ZonesPanel> regions = new ArrayList<>();

    /**
     * Inits a new RegionPanel
     */
    public RegionPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));

        initPanel();
    }

    /**
     * Inits the panel : adds all components
     */
    private void initPanel() {
        JLabel label = new JLabel(FerretMain.getLocale().getString("region.input"), SwingConstants.LEFT);
        label.setFont(new Font("Calibri", Font.BOLD, 24));
        add(label, BorderLayout.NORTH);
        label.setForeground(new Color(18, 0, 127));

        JPanel container = new JPanel();
        container.setLayout(new GridLayout(2, 3));

        Region[] regions1 = FerretMain.getConfig().getSelectedVersion().getRegions();
        for (int i = 0; i < regions1.length; i++) {
            Region region = regions1[i];
            ZonesPanel panel = new ZonesPanel(region, i >= 3 ? 7 : 9);
            regions.add(panel);
            container.add(panel);
        }

        add(container, BorderLayout.CENTER);
    }

    /**
     * Reloads the contents of the panel <br>
     * Called when the Ferret settings are modified
     */
    public void reloadPanel() {
        removeAll();
        initPanel();
        updateUI();
    }

    /**
     * @return Panels for each supported {@link Region}
     */
    public List<ZonesPanel> getRegions() {
        return regions;
    }

    /**
     * A JPanel containing a {@link Region} <br>
     * Contains all the selectable zones of the region
     */
    public class ZonesPanel extends JPanel {
        /**
         * The region displayed on this panel
         */
        private final Region region;
        /**
         * The checkboxes for each zone of the region
         */
        private final JCheckBox[] checkBoxes;

        /**
         * Creates a panel for the given region
         * @param region The region
         * @param lines The number of lines of the layout, to keep coherence with other displayed SubPanels
         */
        public ZonesPanel(Region region, int lines) {
            this.region = region;
            this.setLayout(new GridLayout(lines, 1));

            //Title
            JLabel label = new JLabel(FerretMain.getLocale().getString("region." + region.getName().toLowerCase(Locale.ROOT)));
            label.setFont(new Font("Calibri", Font.BOLD, 20));
            label.setForeground(new Color(131, 55, 192));
            add(label);

            //Zones selection
            this.checkBoxes = new JCheckBox[region.getZones().length];
            for (int i = 0; i < checkBoxes.length; i++) {
                checkBoxes[i] = new JCheckBox(region.getZones()[i] + " " +
                        FerretMain.getLocale().getString("region." + region.getZones()[i]) +
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

                if (region == FerretMain.getConfig().getSelectedVersion().getRegions()[0]) {
                    for (ZonesPanel panel : RegionPanel.this.regions) {
                        if (panel != this) {
                            panel.setCheckBoxesState(0, state);
                        }
                    }
                }
            });
        }

        /**
         * Changes the states of all zone checkboxes between start and checkBoxes.length
         * @param start The start offset
         * @param state The new selected state of the checkboxes
         */
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
