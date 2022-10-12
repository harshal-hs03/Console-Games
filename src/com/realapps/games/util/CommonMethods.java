package com.realapps.games.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CommonMethods {

    private static final String PROPERTIES_FILE_NAME = "resources/game.properties";

//    method to return the value of the specified property from the game.properties file
    public static String getPropertyFromPropertiesFile(String propertyName) {
        FileInputStream fis = null;
        Properties prop = null;
        try {
            fis = new FileInputStream(PROPERTIES_FILE_NAME);
            prop = new Properties();
            prop.load(fis);
        } catch(IOException ignored) {
        } finally {
            try {
                fis.close();
            } catch (Exception ignored) {
            }
        }

        return (prop == null) ? null : prop.getProperty(propertyName);
    }

//    method to return all the lines from the specified file in the form of list
    private static List<String> getLinesFromFileInAList(String fileName){
        List<String> returnList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String inputLine;
            while((inputLine = br.readLine()) != null){
                returnList.add(inputLine);
            }
            br.close();
        } catch (IOException ignored) {
        }
        return returnList;
    }
}
