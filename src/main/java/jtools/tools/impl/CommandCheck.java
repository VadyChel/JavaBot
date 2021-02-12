package jtools.tools.impl;

import jtools.tools.handler.CommandContext;

public interface CommandCheck {
    default boolean check(CommandContext ctx) {
        return true;
    }
}
