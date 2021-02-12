package jtools.commands.different;

import jtools.tools.bases.Command;
import jtools.tools.handler.CommandContext;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;

public class AvatarCommand extends Command {
    public AvatarCommand(){
        this.name = "avatar";
        this.category = "different";
    }

    @Override
    public void execute(CommandContext ctx) {
        User author = ctx.getAuthor();
        EmbedBuilder embed = new EmbedBuilder()
                .setColor(ctx.getMember().getRoles().get(0).getColor())
                .setTitle("Avatar of " + author.getName())
                .setImage(this.getUtils().getAvatar(author))
                .setFooter(ctx.getSelfUser().getName(), ctx.getSelfUser().getAvatarUrl());
        ctx.getChannel().sendMessage(embed.build()).queue();
    }
}
