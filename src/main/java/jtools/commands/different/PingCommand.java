package jtools.commands.different;

import jtools.tools.bases.BaseCommand;
import jtools.tools.handler.CommandContext;

public class PingCommand extends BaseCommand {
    public PingCommand(){
        super("ping", "different");
    }

    @Override
    public void execute(CommandContext ctx) {
        ctx.getChannel().sendMessage("My ping is "+ctx.getJDA().getGatewayPing()).queue();
    }
}
