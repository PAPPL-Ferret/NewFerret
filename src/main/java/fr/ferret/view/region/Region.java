package fr.ferret.view.region;

import java.util.Arrays;

/**
 * A world region of the 1KG project
 */
public class Region {
    /**
     * The region name
     */
    private final String name;
    /**
     * The zones of the region
     */
    private final String[] zones;
    /**
     * The individuals count for each zone, respectively
     */
    private final int[] individualCount;

    /**
     * Creates a new Region
     *
     * @param name The name of the region (for translation)
     * @param zones           The zones
     * @param individualCount The individuals count for each zone, respectively
     */
    public Region(String name, String[] zones, int[] individualCount) {
        this.name = name;
        this.zones = zones;
        this.individualCount = individualCount;
        assert zones.length == Arrays.stream(individualCount).count() : "Zones length doesn't match to individuals count";
    }

    public String getName() {
        return name;
    }

    public String[] getZones() {
        return zones;
    }

    public int[] getIndividualCount() {
        return individualCount;
    }
}
