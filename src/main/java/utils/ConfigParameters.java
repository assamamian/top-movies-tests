package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;

public class ConfigParameters {

    private static HashMap<String, String> confs = new HashMap<>();

    public ConfigParameters() {
        Properties properties = new Properties();
        try {
            URL url = ClassLoader.getSystemResource("parameters.properties");
            properties.load(new FileInputStream(url.getFile()));
            for (String key : properties.stringPropertyNames()) {
                confs.put(key, properties.getProperty(key));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getValue(String key) {
        String conf = confs.get(key);
        if (conf == null) {
            throw new RuntimeException("conf not found : " + key);
        }
        return conf;
    }
}
