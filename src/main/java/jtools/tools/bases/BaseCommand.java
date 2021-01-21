package jtools.tools.bases;

import jtools.tools.Utils;
import jtools.tools.handler.CommandContext;
import jtools.tools.handler.CommandManager;
import jtools.tools.impl.ICommand;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseCommand implements ICommand {
    private final Utils utils;
    private final String name;
    private final List<String> aliases;
    private final CommandManager commandManager;
    private final String category;
    private final int cooldown = 0;
    private final Map<String, String> emptyMap = new HashMap<>();


    public BaseCommand(String name, String category, CommandManager commandManager, Utils utils) {
        this.name = name;
        this.aliases = new ArrayList<>();
        this.utils = utils;
        this.category = category;
        this.commandManager = commandManager;
    }

    public void execute(CommandContext ctx) {}

    public String getName() {
        return this.name;
    }

    public List<String> getAliases() {
        return this.aliases;
    }

    public String getDescription(String guildId) {
        return this.getLanguage(guildId).get("description");
    }

    public String getUsage(String guildId) {
        return this.getLanguage(guildId).get("usage");
    }

    public String getHelp(String guildId) {
        return this.getLanguage(guildId).get("help");
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

    public CommandManager getCommandManager(){
        return this.commandManager;
    }

    public Map<String, String> getLanguage(String guildId){
        return this.getCommandManager().getLanguages().get("ru").get(0).get("commands").stream()
            .filter(item -> this.getName().equals(item.get("name")))
            .findAny()
            .orElse(this.emptyMap);
    }
}
