package fr.ferret;

import fr.ferret.controller.settings.FerretConfig;
import fr.ferret.view.FerretFrame;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;


public class FerretTest
{
    public static final Logger log = Logger.getLogger("Ferret");
    /**
     * Ressources du programme (langue et propriétés)
     */
    public static ResourceBundle locale;

    /**
     * Paramètres du programme
     */
    public static FerretConfig config = new FerretConfig();

    public static void main(String[] args) {
        locale = ResourceBundle.getBundle("ferret", Locale.FRENCH);
        FerretFrame frame = new FerretFrame(); //Show ferret frame
        frame.setVisible(true);
    }
}