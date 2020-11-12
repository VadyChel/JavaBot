package jtools.different;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class PingCommand extends Command {
    public PingCommand(){
        this.name = "ping";
    }

    @Override
    protected void execute(CommandEvent commandEvent) {
        System.out.println(commandEvent.getJDA().getGatewayPing());
        commandEvent.reply("Hello, my ping is " + commandEvent.getJDA().getGatewayPing());
    }
}
