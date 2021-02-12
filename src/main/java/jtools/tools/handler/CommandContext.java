package jtools.tools.handler;

import jtools.tools.bases.Command;
import jtools.tools.impl.ICommandContext;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import java.util.List;
import java.util.Map;

public class CommandContext implements ICommandContext {
    private final GuildMessageReceivedEvent event;
    private final Command command;
    private final List<String> args;


    public CommandContext(GuildMessageReceivedEvent event, Command command, List<String> args){
        this.event = event;
        this.command = command;
        this.args = args;
    }

    public Guild getGuild(){
        return this.getEvent().getGuild();
    }

    public TextChannel getChannel(){
        return this.getEvent().getChannel();
    }

    public Command getCommand(){
        return this.command;
    }

    public GuildMessageReceivedEvent getEvent(){
        return this.event;
    }

    public JDA getJDA(){
        return this.getEvent().getJDA();
    }

    public Message getMessage(){
        return this.getEvent().getMessage();
    }

    public User getAuthor(){
        return this.getEvent().getAuthor();
    }

    public Member getMember(){
        return this.getEvent().getMember();
    }

    public SelfUser getSelfUser(){
        return this.getJDA().getSelfUser();
    }

    public List<String> getArgs(){
        return this.args;
    }

    public Map<String, String> getLanguage(){
        return this.getCommand().getLanguage(getGuild().getIdLong());
    }
}
