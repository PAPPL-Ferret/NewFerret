package fr.ferret;

import fr.ferret.controller.FerretConfig;
import fr.ferret.view.FerretFrame;

import java.util.Locale;
import java.util.ResourceBundle;


public class FerretTest
{
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