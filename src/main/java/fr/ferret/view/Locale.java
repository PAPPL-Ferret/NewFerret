package fr.ferret.view;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Locale
{
    private final Map<String, String> translation = new HashMap<>();

    public Locale(String localeName) {
        System.out.println("Res : "+localeName);
        InputStream file = getClass().getResourceAsStream("/lang/"+localeName+".txt");
        Scanner input = new Scanner(file); //if null ?
        while (input.hasNextLine()) {
            String line = input.nextLine();
            if(!line.startsWith("#") && line.contains("=")) {
                String[] split = line.split("=");
                if(split.length == 2) {
                    translation.put(split[0], split[1]);
                }
                //else ignore ?
            }
        }
    }

    public String translate(String key) {
        return translation.getOrDefault(key, key);
    }

    public Map<String, String> getTranslation() {
        return translation;
    }
}
