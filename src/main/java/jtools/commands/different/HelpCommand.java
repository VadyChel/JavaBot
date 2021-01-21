package jtools.commands.different;

import jtools.tools.Utils;
import jtools.tools.bases.BaseCommand;
import jtools.tools.handler.CommandContext;
import jtools.tools.handler.CommandManager;
import net.dv8tion.jda.api.EmbedBuilder;

public class HelpCommand extends BaseCommand {
    public HelpCommand(CommandManager commandManager, Utils utils){
        super("help", "different", commandManager, utils);
    }

    @Override
    public void execute(CommandContext ctx) {
        String guildId = ctx.getGuild().getId();
        StringBuilder description = new StringBuilder();
        for(BaseCommand command: ctx.getCommand().getCommandManager().getCommands()){
            description.append(String.format("j!%s - %s\n", command.getUsage(guildId), command.getDescription(guildId)));
        }
        EmbedBuilder embed = new EmbedBuilder()
                .setColor(ctx.getMember().getRoles().get(0).getColor())
                .setTitle("Available commands:")
                .setDescription(description)
                .setAuthor(ctx.getAuthor().getName(), ctx.getAuthor().getAvatarUrl())
                .setFooter(ctx.getSelfUser().getName(), ctx.getSelfUser().getAvatarUrl());
        ctx.getChannel().sendMessage(embed.build()).queue();
    }
}
