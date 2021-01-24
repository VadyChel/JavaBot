package jtools.listeners;

import jtools.tools.handler.CommandContext;
import jtools.tools.handler.CommandEvents;
import jtools.tools.handler.exceptions.CommandException;

public class CommandListener implements CommandEvents {
    public void onCommandException(CommandContext ctx, CommandException commandException){
        System.out.println("Error was occurred in "+ctx.getCommand().getName()+" command");
    }

    public void onCommand(CommandContext ctx){
        System.out.println("Success command - "+ctx.getCommand().getName());
    }
}
