package jtools.commands.different;

import jtools.tools.bases.Command;
import jtools.tools.handler.CommandContext;

public class PingCommand extends Command {
    public PingCommand(){
        this.name = "ping";
        this.category = "different";
    }

    @Override
    public void execute(CommandContext ctx) {
        ctx.getChannel().sendMessage("My ping is "+ctx.getJDA().getGatewayPing()).queue();
    }
}
