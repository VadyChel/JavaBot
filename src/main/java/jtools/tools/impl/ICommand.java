package jtools.tools.impl;

import jtools.tools.Utils;
import jtools.tools.bases.Command;
import jtools.tools.services.database.Database;
import jtools.tools.handler.CommandContext;
import jtools.tools.handler.CommandManager;
import net.dv8tion.jda.api.Permission;

import java.util.List;
import java.util.Map;

public interface ICommand {
    void execute(CommandContext ctx);
    String getName();
    String getDescription(long guildId);
    String getUsage(long guildId);
    String getHelp(long guildId);
    String getCategory();
    int getCooldown();
    CommandManager getCommandManager();
    Utils getUtils();
    Database getDatabaseService();
    List<Permission> getBotPermissions();
    List<Permission> getUserPermissions();
    List<CommandCheck> getChecks();
    List<String> getAliases();
    List<Command> getChildren();
    Map<String, String> getLanguage(long guildId);
    void setUtils(Utils utils);
    void setCommandManager(CommandManager commandManager);
    void setDatabaseService(Database databaseService);
    void addAlias(String alias);
    void setName(String name);
    void setCategory(String category);
    void addChild(Command command);
    void addChildren(Command ... commands);
    void addCheck(CommandCheck obj);
    void addChecks(CommandCheck ... objs);
    void addBotPermissions(Permission permission);
    void addUserPermissions(Permission permission);
}
