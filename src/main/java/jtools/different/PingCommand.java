package jtools.different;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class PingCommand extends Command {
    public PingCommand(){
        this.name = "ping";
        this.help = "Sends a my API ping";
    }

    @Override
    protected void execute(CommandEvent commandEvent) {
        commandEvent.reply("Hello" + commandEvent.getAuthor().getName() + ", my ping is " + commandEvent.getJDA().getGatewayPing());
    }
}
