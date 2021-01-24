package jtools.tools.impl;

import jtools.tools.bases.BaseCommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import java.util.List;
import java.util.Map;


public interface ICommandContext {
    Guild getGuild();
    TextChannel getChannel();
    BaseCommand getCommand();
    GuildMessageReceivedEvent getEvent();
    JDA getJDA();
    Message getMessage();
    User getAuthor();
    Member getMember();
    SelfUser getSelfUser();
    List<String> getArgs();
    Map<String, String> getLanguage();
}
