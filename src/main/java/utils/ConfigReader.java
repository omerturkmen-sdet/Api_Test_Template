package utils;

import org.apache.commons.configuration.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;
    private static final String endPointPath = "src/main/resources/endpoints.properties";

    /**
     * This method will read the properties file
     * @param fileName:
     */
    private static void setup(String fileName){
        String path = getPath(fileName);
        try {
            assert path != null;
            try (FileInputStream inputStream = new FileInputStream(path)){
                properties = new Properties();
                properties.load(inputStream);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param fileName: it will pass String to define which properties file will be used
     * @return properties file path
     */
    private static String getPath(String fileName){
        switch (fileName){
            case "dev": case "uat":
                return "src/main/resources/" + fileName + "Environment.properties";
            case "url":
                return endPointPath;
        }
        return null;
    }

    public static String get(String key,String filePath) {
        setup(filePath);
        return properties.getProperty(key);
    }


    public String getReportConfigPath(){
        String reportConfigPath = properties.getProperty("reportConfigPath");
        if(reportConfigPath!= null) return reportConfigPath;
        else throw new RuntimeException("Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath");
    }

    /**
     * This method similar with reading properties file, but it also allows reading property with variable
     * CompositeConfiguration will get property by assigning parameter name. You also need to define a property called "parameter" in same file
     *          Example: You can read property like: https://someurl.com/{parameter}
     *
     * @param key: propertyName
     * @return propertyValue
     */
    public static String getEndpoint(String key){
        CompositeConfiguration config = new CompositeConfiguration();
        config.addConfiguration(new SystemConfiguration());
        try {
            config.addConfiguration(new PropertiesConfiguration(endPointPath));
        } catch (ConfigurationException e) {
            throw new RuntimeException(e);
        }

        return config.getString(key);
    }
}
