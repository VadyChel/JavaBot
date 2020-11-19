package jtools.different;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;

public class HelpCommand extends Command {
    public HelpCommand(){
        this.name = "help";
        this.help = "Sends a help of my commands";
    }

    @Override
    protected void execute(CommandEvent commandEvent) {
        EmbedBuilder embed = new EmbedBuilder()
                .setColor(commandEvent.getMember().getRoles().get(0).getColor())
                .setTitle("Available commands:")
                .setDescription("ping, avatar")
                .setAuthor(commandEvent.getAuthor().getName(), commandEvent.getAuthor().getAvatarUrl())
                .setFooter(commandEvent.getSelfUser().getAsMention(), commandEvent.getSelfUser().getAvatarUrl());
        commandEvent.reply(embed.build());
    }
}
