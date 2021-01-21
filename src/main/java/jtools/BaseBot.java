package jtools;

import jtools.tools.Utils;
import jtools.tools.handler.CommandListener;
import jtools.tools.handler.CommandManager;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import jtools.commands.different.*;
import java.io.InputStream;
import java.util.Properties;

public class BaseBot {

    public static void main(String[] args) throws Exception {
        InputStream propertiesFile = BaseBot.class.getClassLoader().getResourceAsStream("Config.properties");
        Properties properties = new Properties();
        properties.load(propertiesFile);

        Utils utils = new Utils();
        CommandManager commandManager = new CommandManager(utils);
        System.out.println(utils.loadLanguages());
        commandManager.addCommand(new AvatarCommand(commandManager, utils));
        commandManager.addCommand(new HelpCommand(commandManager, utils));
        commandManager.addCommand(new PingCommand(commandManager, utils));
        CommandListener commandListener = new CommandListener(commandManager);

        JDABuilder.create(properties.getProperty("token"), GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_PRESENCES)
            .addEventListeners(commandListener)
            .disableCache(CacheFlag.VOICE_STATE, CacheFlag.EMOTE)
            .setStatus(OnlineStatus.IDLE)
            .setActivity(Activity.playing("j!help"))
            .build();
    }
}
