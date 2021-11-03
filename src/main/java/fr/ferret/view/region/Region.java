package fr.ferret.view.region;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum Region
{
    //Populations name at the start of "GUI" of the old Ferret
    //To put in a translation system

    ALL_POPULATIONS(new String[]{"ALL"}, new int[]{661}),
    AFRICA(new String[]{"AFR","ACB","ASW","ESN","GWD","LWK","MSL","YRI"},
            new int[]{661, 96, 61, 99, 113, 99, 85, 108}),
    EUROPE(new String[]{"EUR","CEU","GBR","FIN","IBS","TSI"}, new int[]{503, 99, 91, 99, 107, 107}),
    EAST_ASIA(new String[]{"EAS","CDX","CHB","CHS","JPT","KHV","CHD"}, new int[]{504, 93, 103, 105, 104, 99, 0}),
    AMERICA(new String[]{"AMR","CLM","MXL","PEL","PUR"}, new int[]{347, 94, 64, 85, 104}),
    SOUTH_ASIA(new String[]{"SAS","BEB","GIH","ITU","PJL","STU"}, new int[]{489, 86, 103, 102, 96, 102});

    private final String[] zones;
    private final int[] individualCount;

    Region(String[] zones, int[] individualCount) {
        this.zones = zones;
        this.individualCount = individualCount;
        assert zones.length == Arrays.stream(individualCount).count() : "Zones length doesn't match to individuals count";
    }

    public String[] getZones() {
        return zones;
    }

    public int[] getIndividualCount() {
        return individualCount;
    }
}
