package jtools;

import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import jtools.different.PingCommand;

public class BaseBot {

    public static void main(String[] args) throws Exception {
        EventWaiter waiter = new EventWaiter();
        CommandClientBuilder client = new CommandClientBuilder();
        client.setPrefix("j!")
                .setOwnerId("660110922865704980")
                .setAlternativePrefix("<@776010705022746636>")
                .setHelpWord("help")
                .addCommand(new PingCommand());

        new JDABuilder(AccountType.BOT)
                .setToken("")
                .setActivity(Activity.playing("j!help"))
                .setStatus(OnlineStatus.IDLE)
                .addEventListeners(waiter, client.build())
                .build();
    }
}
