package jtools.commands.different;

import jtools.tools.Utils;
import jtools.tools.bases.BaseCommand;
import jtools.tools.handler.CommandContext;
import jtools.tools.handler.CommandManager;

public class PingCommand extends BaseCommand {
    public PingCommand(CommandManager commandManager, Utils utils){
        super("ping", "different", commandManager, utils);
    }

    @Override
    public void execute(CommandContext ctx) {
        ctx.getChannel().sendMessage("My ping is "+ctx.getJDA().getGatewayPing()).queue();
    }
}
