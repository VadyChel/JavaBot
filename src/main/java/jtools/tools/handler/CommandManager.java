package jtools.tools.handler;

import jtools.tools.Utils;
import jtools.tools.bases.Command;
import jtools.tools.handler.exceptions.CheckFailureException;
import jtools.tools.impl.CommandCheck;
import jtools.tools.services.database.Database;
import jtools.tools.handler.exceptions.CommandException;
import jtools.tools.impl.ICommandManager;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class CommandManager implements ICommandManager {
    private final Utils utils;
    private final Database databaseService;
    private final ArrayList<Command> commands = new ArrayList<>();
    private final Map<String, List<Map<String, List<Map<String, String>>>>> languages;
    private final CommandEvents commandListener;

    public CommandManager(Utils utils, Database databaseService, CommandEvents commandListener){
        this.utils = utils;
        this.databaseService = databaseService;
        this.languages = this.getUtils().loadLanguages();
        this.commandListener = commandListener;
    }

    public ArrayList<Command> getCommands() {
        return this.commands;
    }

    public void addCommand(Command command) {
        command.setCommandManager(this);
        command.setDatabaseService(this.databaseService);
        command.setUtils(this.utils);
        this.commands.add(command);
    }

    public void addCommands(Command... commands) {
        for(Command command: commands){
            this.addCommand(command);
        }
    }

    public void removeCommand(Command command) {
        this.commands.remove(command);
    }

    public void addGlobalCheck(CommandCheck obj) {
        for(Command command: this.getCommands()){
            command.addCheck(obj);
        }
    }

    public Command getCommand(String name) {
        for(Command command: this.commands){
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
        Command command = this.getCommand(invoke);

        if (command != null) {
            List<String> args = Arrays.asList(split).subList(1, split.length);
            CommandContext ctx = new CommandContext(event, command, args);

            if(args.toArray().length > 0) {
                if (!command.getChildren().isEmpty()) {
                    for (Command child : command.getChildren()) {
                        if (child.getName().equals(args.get(0)) || child.getAliases().contains(args.get(0))) {
                            boolean checkState = this.checkCommand(command, ctx);
                            if (!checkState) {
                                this.terminateCommand(ctx, new CheckFailureException("You did not pass the checks"));
                                return;
                            }
                            child.execute(ctx);
                            this.successCommand(ctx);
                            return;
                        }
                    }
                }
            }

            boolean checkState = this.checkCommand(command, ctx);
            if (!checkState) {
                this.terminateCommand(ctx, new CheckFailureException("You did not pass the checks"));
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

    private boolean checkCommand(Command command, CommandContext ctx){
        if(!command.getBotPermissions().isEmpty()){
            for(Permission permission: command.getBotPermissions()){
                if(!ctx.getGuild().getSelfMember().hasPermission(permission)){
                    return false;
                }
            }
        }

        if(!command.getUserPermissions().isEmpty()){
            for(Permission permission: command.getUserPermissions()){
                if(!ctx.getMember().hasPermission(permission)){
                    return false;
                }
            }
        }

        if(!command.getChecks().isEmpty()){
            List<Boolean> output = new ArrayList<>();
            for(CommandCheck obj: command.getChecks()){
                output.add(obj.check(ctx));
            }
            return !output.contains(false);
        }
        return true;
    }
}
