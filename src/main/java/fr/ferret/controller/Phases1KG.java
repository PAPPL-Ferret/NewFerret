package fr.ferret.controller;

import fr.ferret.view.region.Region;
import fr.ferret.view.region.RegionsList;

/**
 * Versions du projet 1KG supportées
 */
public enum Phases1KG
{
    //Populations name at the start of "GUI" of the old Ferret
    V3(new RegionsList( //TODO UPDATE DATA WITH V3 DATA (THIS IS V0)
            new Region("all_populations", new String[]{"ALL"}, new int[]{661}),
            new Region("africa", new String[]{"AFR","ACB","ASW","ESN","GWD","LWK","MSL","YRI"},
                    new int[]{661, 96, 61, 99, 113, 99, 85, 108}),
            new Region("europe", new String[]{"EUR","CEU","GBR","FIN","IBS","TSI"}, new int[]{503, 99, 91, 99, 107, 107}),
            new Region("east_asia", new String[]{"EAS","CDX","CHB","CHS","JPT","KHV","CHD"}, new int[]{504, 93, 103, 105, 104, 99, 0}),
            new Region("america", new String[]{"AMR","CLM","MXL","PEL","PUR"}, new int[]{347, 94, 64, 85, 104}),
            new Region("south_asia", new String[]{"SAS","BEB","GIH","ITU","PJL","STU"}, new int[]{489, 86, 103, 102, 96, 102})
    )), NYGC_30X(new RegionsList());

    private final RegionsList regions;

    Phases1KG(RegionsList regions) {
        this.regions = regions;
    }

    public RegionsList getRegionsList() {
        return regions;
    }

    public Region[] getRegions() {
        return regions.getRegions();
    }
}
