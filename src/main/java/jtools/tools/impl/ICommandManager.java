package jtools.tools.impl;

import jtools.tools.Utils;
import jtools.tools.bases.Command;
import jtools.tools.handler.CommandContext;
import jtools.tools.handler.CommandEvents;
import jtools.tools.handler.exceptions.CommandException;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ICommandManager {
    CommandEvents getCommandListener();
    Utils getUtils();
    Command getCommand(String name);
    ArrayList<Command> getCommands();
    Map<String, List<Map<String, List<Map<String, String>>>>> getLanguages();
    void process(GuildMessageReceivedEvent event, String prefix);
    void terminateCommand(CommandContext ctx, CommandException commandException);
    void successCommand(CommandContext ctx);
    void addCommand(Command command);
    void addCommands(Command... commands);
    void removeCommand(Command command);
    void addGlobalCheck(CommandCheck obj);
}
