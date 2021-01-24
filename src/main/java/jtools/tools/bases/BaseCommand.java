package jtools.tools.bases;

import jtools.tools.Utils;
import jtools.tools.services.database.Database;
import jtools.tools.handler.CommandContext;
import jtools.tools.handler.CommandManager;
import jtools.tools.impl.ICommand;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseCommand implements ICommand {
    private Utils utils;
    private final String name;
    private final List<String> aliases = new ArrayList<>();
    private CommandManager commandManager;
    private Database databaseService;
    private final String category;
    private final int cooldown = 0;
    private final Map<String, String> emptyMap = new HashMap<>();

    public BaseCommand(String name, String category) {
        this.name = name;
        this.category = category;
    }

    public void execute(CommandContext ctx) {}

    public String getName() {
        return this.name;
    }

    public List<String> getAliases() {
        return this.aliases;
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

    public List<Long> getIgnoreRoles(long guildId){
        return new ArrayList<>();
    }

    public List<Long> getIgnoreChannels(long guildId) {
        return new ArrayList<>();
    }

    public List<Long> getTargetRoles(long guildId) {
        return new ArrayList<>();
    }

    public List<Long> getTargetChannels(long guildId) {
        return new ArrayList<>();
    }

    public void addAlias(String alias) {
        this.aliases.add(alias);
    }

    public int getCooldown(){
        return this.cooldown;
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
