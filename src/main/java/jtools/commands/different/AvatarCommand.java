package jtools.commands.different;

import jtools.tools.Utils;
import jtools.tools.bases.BaseCommand;
import jtools.tools.handler.CommandContext;
import jtools.tools.handler.CommandManager;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;

public class AvatarCommand extends BaseCommand {
    public AvatarCommand(CommandManager commandManager, Utils utils){
        super("avatar", "different", commandManager, utils);
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
