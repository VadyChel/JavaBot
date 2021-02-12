package jtools.commands.different;

import jtools.tools.bases.Command;
import jtools.tools.handler.CommandContext;

public class Help2Command extends Command {
    public Help2Command(){
        this.name = "help2";
    }

    @Override
    public void execute(CommandContext ctx) {
        ctx.getChannel().sendMessage("Test").queue();
    }
}
