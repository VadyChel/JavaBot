package jtools.tools.handler;

import jtools.tools.handler.exceptions.CommandException;

public interface CommandEvents {
    default void onCommandException(CommandContext ctx, CommandException commandException){}
    default void onCommand(CommandContext ctx){}
}
