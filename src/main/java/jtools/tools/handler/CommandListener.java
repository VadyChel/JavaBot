package jtools.tools.handler;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import javax.annotation.Nonnull;

public class CommandListener extends ListenerAdapter {
    private final CommandManager commandManager;
    public CommandListener(CommandManager commandManager){
        this.commandManager = commandManager;
    }

    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent event){
        User user = event.getAuthor();

        if(user.isBot()){
            return;
        }

        String prefix = "j!";
        if(event.getMessage().getContentRaw().startsWith(prefix)){
            this.getCommandManager().process(event, prefix);
        }
    }

    public CommandManager getCommandManager(){
        return this.commandManager;
    }
}
