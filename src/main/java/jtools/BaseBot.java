package jtools;

import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.entities.Activity;

public class BaseBot {
    public static void main(String[] args) throws Exception {
        final JDA jda = new JDABuilder(AccountType.BOT)
                .setToken("Nzc2MDEwNzA1MDIyNzQ2NjM2.X6uqSQ.7HDX11tOSu7brU2S_bJpzU3hbOE")
                .setActivity(Activity.playing("j!help"))
                .build();

        CommandClientBuilder builder = new CommandClientBuilder();
        builder.setPrefix("j!")
                .setOwnerId("660110922865704980")
                .setAlternativePrefix("<@776010705022746636>")
                .setHelpWord("help");
        CommandClient client = builder.build();
        jda.addEventListener(client);
    }
}
