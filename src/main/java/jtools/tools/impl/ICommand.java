package jtools.tools.impl;

import jtools.tools.Utils;
import jtools.tools.services.database.Database;
import jtools.tools.handler.CommandContext;
import jtools.tools.handler.CommandManager;
import java.util.List;
import java.util.Map;

public interface ICommand {
    void execute(CommandContext ctx);
    String getName();
    String getDescription(long guildId);
    List<String> getAliases();
    String getUsage(long guildId);
    String getHelp(long guildId);
    List<Long> getIgnoreRoles(long guildId);
    List<Long> getIgnoreChannels(long guildId);
    List<Long> getTargetRoles(long guildId);
    List<Long> getTargetChannels(long guildId);
    int getCooldown();
    CommandManager getCommandManager();
    Utils getUtils();
    String getCategory();
    Database getDatabaseService();
    void setUtils(Utils utils);
    void setCommandManager(CommandManager commandManager);
    void setDatabaseService(Database databaseService);
    void addAlias(String alias);
    Map<String, String> getLanguage(long guildId);
}
