package jtools.different;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;

public class AvatarCommand extends Command {
    public AvatarCommand(){
        this.name = "avatar";
        this.help = "Sends your avatar";
    }

    @Override
    protected void execute(CommandEvent commandEvent) {
        User author = commandEvent.getAuthor();
        EmbedBuilder embed = new EmbedBuilder()
                .setColor(commandEvent.getMember().getRoles().get(0).getColor())
                .setTitle("Avatar of " + author.getName())
                .setImage(author.getAvatarUrl())
                .setAuthor(author.getName(), author.getAvatarUrl())
                .setFooter(commandEvent.getSelfUser().getAsTag(), commandEvent.getSelfUser().getAvatarUrl());
        commandEvent.reply(embed.build());
    }
}
