package jtools;

import jtools.checks.MainCheck;
import jtools.listeners.CommandListener;
import jtools.tools.Utils;
import jtools.tools.services.database.Database;
import jtools.tools.handler.CommandHandler;
import jtools.tools.handler.CommandManager;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import jtools.commands.different.*;
import java.util.Properties;

public class jTools {

    public static void main(String[] args) throws Exception {
        Utils utils = new Utils();
        Properties properties = utils.getProperties();
        Database databaseService = new Database(properties);
        CommandListener commandListener = new CommandListener();
        CommandManager commandManager = new CommandManager(utils, databaseService, commandListener);
        commandManager.addCommands(
            new AvatarCommand(),
            new PingCommand(),
            new HelpCommand()
        );
        commandManager.addGlobalCheck(new MainCheck());
        JDABuilder.create(properties.getProperty("token"), GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_PRESENCES)
            .addEventListeners(new CommandHandler(commandManager))
            .disableCache(CacheFlag.VOICE_STATE, CacheFlag.EMOTE)
            .setStatus(OnlineStatus.IDLE)
            .setActivity(Activity.playing("j!help"))
            .build();
    }
}
