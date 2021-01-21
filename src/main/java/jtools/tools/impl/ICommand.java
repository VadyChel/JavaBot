package jtools.tools.impl;

import jtools.tools.Utils;
import jtools.tools.handler.CommandContext;
import jtools.tools.handler.CommandManager;
import java.util.List;
import java.util.Map;

public interface ICommand {
    void execute(CommandContext ctx);
    String getName();
    String getDescription(String guildId);
    List<String> getAliases();
    String getUsage(String guildId);
    String getHelp(String guildId);
    int getCooldown();
    CommandManager getCommandManager();
    Utils getUtils();
    String getCategory();
    void addAlias(String alias);
    Map<String, String> getLanguage(String guildId);
}
