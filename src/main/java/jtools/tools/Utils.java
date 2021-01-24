package jtools.tools;

import jtools.jTools;
import net.dv8tion.jda.api.entities.User;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Utils {
    public String getAvatar(User user){
        return user.getAvatarUrl()+"?size=2048";
    }

    public Map<String, List<Map<String, List<Map<String, String>>>>> loadLanguages() {
        Yaml yaml = new Yaml();
        InputStream inputStream = this.getClass()
            .getClassLoader()
            .getResourceAsStream("languages.yaml");
        return yaml.load(inputStream);
    }

    public Properties getProperties() throws IOException {
        InputStream propertiesFile = jTools.class.getClassLoader().getResourceAsStream("Config.properties");
        Properties properties = new Properties();
        properties.load(propertiesFile);
        return properties;
    }
}
