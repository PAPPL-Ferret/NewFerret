package fr.ferret.controller;

/**
 * Paramètres du programme
 */
public class FerretConfig
{
    /**
     * Version du projet 1KG utilisée
     */
    private Phases1KG selectedVersion = Phases1KG.V3;

    /**
     * Type de fichier voulu en sortie
     */
    private FileOutputType selectedOutputType = FileOutputType.ALL;

    /**
     * Version des gènes à utiliser
     */
    private HumanGenomeVersions selectedHumanGenome = HumanGenomeVersions.V19;

    public Phases1KG getSelectedVersion() {
        return selectedVersion;
    }

    public FileOutputType getSelectedOutputType() {
        return selectedOutputType;
    }

    public HumanGenomeVersions getSelectedHumanGenome() {
        return selectedHumanGenome;
    }

    public void setSelectedVersion(Phases1KG selectedVersion) {
        this.selectedVersion = selectedVersion;
    }

    public void setSelectedOutputType(FileOutputType selectedOutputType) {
        this.selectedOutputType = selectedOutputType;
    }

    public void setSelectedHumanGenome(HumanGenomeVersions selectedHumanGenome) {
        this.selectedHumanGenome = selectedHumanGenome;
    }
}
