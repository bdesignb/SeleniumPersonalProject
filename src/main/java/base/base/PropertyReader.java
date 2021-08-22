package base.base;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {

    public String getProperty(String key) throws Exception {
        Properties properties = new Properties();
        File file = new File("src/ConfigFiles/Configuration.properties");
        FileReader fileReader = new FileReader(file);
        properties.load(fileReader);
        return properties.getProperty(key).toString();
    }
}
