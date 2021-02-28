package Configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Properties;

public class Config {
    public static Properties properties = new Properties();

    public static String get(ConfigItem configItem) {
        // performs once when properties are not loaded
        if (properties.size() == 0) {
            try {
                FileInputStream fileInputStream = new FileInputStream("src/test/resources/config.properties");
                properties.load(fileInputStream);
            }
            catch (Exception e) {
                // ignore
            }
        }
        return properties.getProperty(configItem.toString());
    }
}
