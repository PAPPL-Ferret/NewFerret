package fr.ferret.view.region;

import java.util.Arrays;
import java.util.List;

/**
 * A list of {@link Region}s, depending on the {@link fr.ferret.controller.Phases1KG} version
 */
public class RegionsList
{
    private final Region[] regions;

    public RegionsList(Region... regions) {
        this.regions = regions;
    }

    public Region[] getRegions() {
        return regions;
    }
}
