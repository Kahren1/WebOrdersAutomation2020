package com.weborders.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class ConfigurationReader {
    private static Properties configFile;

    static {

        try {
            //location of .properties file
            String path = System.getProperty("user.dir") + "/configuration.properties";
            //get that file as a stream
            //getting a file with data, converting it into something Java understands
            // FileInputStream throws Exception
            FileInputStream input = new FileInputStream(path);
            //create object of the properties class
            configFile = new Properties();
            //load .properties file into Properties object
            configFile.load(input);
            //close the input streat at the end
            input.close();


        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load properties file!");
        }
    }
//method returns a String, a property of a Properties object, which contains  configuration.properties file as a stream

    /**
     * This method returns property value from configuration.properties file
     *
     * @param keyName property name
     * @return property value
     */
    public static String getProperty(String keyName) {
        return configFile.getProperty(keyName);
    }

}
