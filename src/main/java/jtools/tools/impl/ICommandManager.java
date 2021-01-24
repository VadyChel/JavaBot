package jtools.tools.impl;

import jtools.tools.Utils;
import jtools.tools.bases.BaseCommand;
import jtools.tools.handler.CommandContext;
import jtools.tools.handler.CommandEvents;
import jtools.tools.handler.exceptions.CommandException;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ICommandManager {
    ArrayList<BaseCommand> getCommands();
    void addCommand(BaseCommand command);
    void addCommands(BaseCommand ... commands);
    void removeCommand(BaseCommand command);
    CommandEvents getCommandListener();
    Utils getUtils();
    Map<String, List<Map<String, List<Map<String, String>>>>> getLanguages();
    BaseCommand getCommand(String name);
    void process(GuildMessageReceivedEvent event, String prefix);
    void terminateCommand(CommandContext ctx, CommandException commandException);
    void successCommand(CommandContext ctx);
}
