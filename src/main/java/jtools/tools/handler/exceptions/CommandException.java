package jtools.tools.handler.exceptions;

public class CommandException {
    private final String text;

    public CommandException(String text){
        this.text = text;
    }

    public String getText(){
        return this.text;
    }
}
