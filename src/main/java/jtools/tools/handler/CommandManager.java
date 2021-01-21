package jtools.tools.handler;

import jtools.tools.Utils;
import jtools.tools.bases.BaseCommand;
import jtools.tools.impl.ICommandManager;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class CommandManager implements ICommandManager {
    private final Utils utils;
    private final ArrayList<BaseCommand> commands = new ArrayList<>();
    private final Map<String, List<Map<String, List<Map<String, String>>>>> languages;

    public CommandManager(Utils utils){
        this.utils = utils;
        this.languages = this.getUtils().loadLanguages();
    }

    public ArrayList<BaseCommand> getCommands() {
        return this.commands;
    }

    public void addCommand(BaseCommand command) {
        this.commands.add(command);
    }

    public void removeCommand(BaseCommand command) {
        this.commands.remove(command);
    }

    public BaseCommand getCommand(String name) {
        for(BaseCommand command: this.commands){
            if(command.getName().equals(name) || command.getAliases().contains(name)){
                return command;
            }
        }
        return null;
    }

    public void process(GuildMessageReceivedEvent event, String prefix){
        String[] split = event.getMessage().getContentRaw()
            .replaceFirst("(?i)" + Pattern.quote(prefix), "")
            .split("\\s+");

        String invoke = split[0].toLowerCase();
        BaseCommand command = this.getCommand(invoke);

        if (command != null) {
            event.getChannel().sendTyping().queue();
            List<String> args = Arrays.asList(split).subList(1, split.length);

            CommandContext ctx = new CommandContext(event, command, args);
            command.execute(ctx);
        }
    }

    public Utils getUtils(){
        return this.utils;
    }

    public Map<String, List<Map<String, List<Map<String, String>>>>> getLanguages(){
        return this.languages;
    }
}
