package jtools.tools;

import net.dv8tion.jda.api.entities.User;
import org.yaml.snakeyaml.Yaml;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class Utils {
    public String getAvatar(User user){
        System.out.println(user.getAvatarUrl()+"?size=2048");
        return user.getAvatarUrl()+"?size=2048";
    }

    public Map<String, List<Map<String, List<Map<String, String>>>>> loadLanguages() {
        Yaml yaml = new Yaml();
        InputStream inputStream = this.getClass()
            .getClassLoader()
            .getResourceAsStream("languages.yaml");
        Map<String, List<Map<String, List<Map<String, String>>>>> languages = yaml.load(inputStream);
        return languages;
    }
}
