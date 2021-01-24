package jtools.tools.handler;

import jtools.tools.Utils;
import jtools.tools.bases.BaseCommand;
import jtools.tools.services.database.Database;
import jtools.tools.handler.exceptions.CheckFailureException;
import jtools.tools.handler.exceptions.CommandException;
import jtools.tools.impl.ICommandManager;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class CommandManager implements ICommandManager {
    private final Utils utils;
    private final Database databaseService;
    private final ArrayList<BaseCommand> commands = new ArrayList<>();
    private final Map<String, List<Map<String, List<Map<String, String>>>>> languages;
    private final CommandEvents commandListener;

    public CommandManager(Utils utils, Database databaseService, CommandEvents commandListener){
        this.utils = utils;
        this.databaseService = databaseService;
        this.languages = this.getUtils().loadLanguages();
        this.commandListener = commandListener;
    }

    public ArrayList<BaseCommand> getCommands() {
        return this.commands;
    }

    public void addCommand(BaseCommand command) {
        command.setCommandManager(this);
        command.setDatabaseService(this.databaseService);
        command.setUtils(this.utils);
        this.commands.add(command);
    }

    public void addCommands(BaseCommand ... commands) {
        for(BaseCommand command: commands){
            this.addCommand(command);
        }
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
            if(!command.getTargetChannels(ctx.getGuild().getIdLong()).isEmpty()){
                if(!command.getIgnoreChannels(ctx.getGuild().getIdLong()).contains(ctx.getChannel().getIdLong())){
                    this.terminateCommand(ctx, new CheckFailureException("You don't have target role"));
                    return;
                }
            }

            if(!command.getTargetRoles(ctx.getGuild().getIdLong()).isEmpty()){
                if(ctx.getMember().getRoles().stream().noneMatch(role -> command.getIgnoreRoles(ctx.getGuild().getIdLong()).contains(role.getIdLong()))){
                    this.terminateCommand(ctx, new CheckFailureException("You don't use this command in target channel"));
                    return;
                }
            }

            if(ctx.getMember().getRoles().stream().anyMatch(role -> command.getIgnoreRoles(ctx.getGuild().getIdLong()).contains(role.getIdLong()))){
                this.terminateCommand(ctx, new CheckFailureException("You cannot use this command"));
                return;
            }

            if(command.getIgnoreChannels(ctx.getGuild().getIdLong()).contains(ctx.getChannel().getIdLong())){
                this.terminateCommand(ctx, new CheckFailureException("You cannot use this command in this channel"));
                return;
            }

            command.execute(ctx);
            this.successCommand(ctx);
        }
    }

    public void terminateCommand(CommandContext ctx, CommandException commandException){
        this.getCommandListener().onCommandException(ctx, commandException);
    }

    public void successCommand(CommandContext ctx){
        this.getCommandListener().onCommand(ctx);
    }

    public Utils getUtils(){
        return this.utils;
    }

    public Map<String, List<Map<String, List<Map<String, String>>>>> getLanguages(){
        return this.languages;
    }

    public CommandEvents getCommandListener(){
        return this.commandListener;
    }
}
