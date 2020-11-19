package jtools;

import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import jtools.different.*;

public class BaseBot {

    public static void main(String[] args) throws Exception {
        EventWaiter waiter = new EventWaiter();
        CommandClientBuilder builder = new CommandClientBuilder();
        builder.setPrefix("j!")
                .setOwnerId("660110922865704980")
                .useHelpBuilder(false)
                .setStatus(OnlineStatus.IDLE)
                .setActivity(Activity.playing("j!help"))
                .addCommands(
                        new PingCommand(),
                        new HelpCommand(),
                        new AvatarCommand()
                );

        CommandClient client = builder.build();

        JDABuilder.create("Nzc2MDEwNzA1MDIyNzQ2NjM2.X6uqSQ.zQbwXBlgHizRAokI2mNQZyjxmwY", GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_PRESENCES)
                .addEventListeners(waiter, client)
                .disableCache(CacheFlag.VOICE_STATE, CacheFlag.EMOTE)
                .build();
    }
}
