package fr.ferret;

import fr.ferret.view.FerretFrame;
import fr.ferret.view.Locale;


public class FerretTest
{
    public static Locale locale;

    public static void main(String[] args) {
        locale = new Locale("en_us");
        FerretFrame frame = new FerretFrame(); //Show ferret frame
        frame.setVisible(true);
    }
}