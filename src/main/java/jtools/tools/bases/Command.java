package jtools.tools.bases;

import jtools.tools.Utils;
import jtools.tools.impl.CommandCheck;
import jtools.tools.services.database.Database;
import jtools.tools.handler.CommandContext;
import jtools.tools.handler.CommandManager;
import jtools.tools.impl.ICommand;
import net.dv8tion.jda.api.Permission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Command implements ICommand {
    protected String name = null;
    protected String category = null;
    private Utils utils;
    private CommandManager commandManager;
    private Database databaseService;
    private final int cooldown = 0;
    private final List<Permission> botPermissions = new ArrayList<>();
    private final List<Permission> userPermissions = new ArrayList<>();
    private final List<CommandCheck> checks = new ArrayList<>();
    private final List<Command> children = new ArrayList<>();
    private final List<String> aliases = new ArrayList<>();
    private final Map<String, String> emptyMap = new HashMap<>();

    public void execute(CommandContext ctx) {}

    public String getName() {
        return this.name;
    }

    public List<String> getAliases() {
        return this.aliases;
    }

    public List<Command> getChildren() {
        return this.children;
    }

    public String getDescription(long guildId) {
        return this.getLanguage(guildId).get("description");
    }

    public String getUsage(long guildId) {
        return this.getLanguage(guildId).get("usage");
    }

    public String getHelp(long guildId) {
        return this.getLanguage(guildId).get("help");
    }

    public void addAlias(String alias) {
        this.aliases.add(alias);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void addChild(Command command) {
        this.children.add(command);
    }

    public void addChildren(Command... commands) {
        for(Command command: commands){
            this.addChild(command);
        }
    }

    public void addCheck(CommandCheck obj) {
        this.checks.add(obj);
    }

    public void addChecks(CommandCheck ... objs) {
        for(CommandCheck obj: objs){
            this.addCheck(obj);
        }
    }

    public void addBotPermissions(Permission permission) {
        this.botPermissions.add(permission);
    }

    public void addUserPermissions(Permission permission) {
        this.userPermissions.add(permission);
    }

    public int getCooldown(){
        return this.cooldown;
    }

    public boolean canRun(CommandContext ctx) {
        if(!this.getBotPermissions().isEmpty()){
            for(Permission permission: this.getBotPermissions()){
                if(!ctx.getGuild().getSelfMember().hasPermission(permission)){
                    return false;
                }
            }
        }

        if(!this.getUserPermissions().isEmpty()){
            for(Permission permission: this.getUserPermissions()){
                if(!ctx.getMember().hasPermission(permission)){
                    return false;
                }
            }
        }

        if(!this.getChecks().isEmpty()){
            List<Boolean> output = new ArrayList<>();
            for(CommandCheck obj: this.getChecks()){
                output.add(obj.check(ctx));
            }
            return !output.contains(false);
        }
        return true;

    }

    public Utils getUtils(){
        return this.utils;
    }

    public String getCategory() {
        return this.category;
    }

    public Database getDatabaseService() {
        return this.databaseService;
    }

    public List<Permission> getBotPermissions() {
        return this.botPermissions;
    }

    public List<Permission> getUserPermissions() {
        return this.userPermissions;
    }

    public List<CommandCheck> getChecks() {
        return this.checks;
    }

    public void setUtils(Utils utils) {
        this.utils = utils;
    }

    public void setCommandManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public void setDatabaseService(Database databaseService) {
        this.databaseService = databaseService;
    }

    public CommandManager getCommandManager(){
        return this.commandManager;
    }

    public Map<String, String> getLanguage(long guildId){
        return this.getCommandManager().getLanguages().get("ru").get(0).get("commands").stream()
            .filter(item -> this.getName().equals(item.get("name")))
            .findAny()
            .orElse(this.emptyMap);
    }
}
