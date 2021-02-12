package jtools.commands.different;

import jtools.checks.MainCheck;
import jtools.tools.bases.Command;
import jtools.tools.handler.CommandContext;
import net.dv8tion.jda.api.EmbedBuilder;

public class HelpCommand extends Command {
    public HelpCommand(){
        this.name = "help";
        this.category = "different";
        this.addChild(new Help2Command());
        this.addCheck(new MainCheck());
    }

    @Override
    public void execute(CommandContext ctx) {
        long guildId = ctx.getGuild().getIdLong();
        StringBuilder description = new StringBuilder();
        for(Command command: ctx.getCommand().getCommandManager().getCommands()){
            description.append(String.format("j!%s - %s\n", command.getUsage(guildId), command.getDescription(guildId)));
        }
        EmbedBuilder embed = new EmbedBuilder()
                .setColor(ctx.getMember().getRoles().get(0).getColor())
                .setTitle(ctx.getLanguage().get("body"))
                .setDescription(description)
                .setAuthor(ctx.getAuthor().getName(), ctx.getAuthor().getAvatarUrl())
                .setFooter(ctx.getSelfUser().getName(), ctx.getSelfUser().getAvatarUrl());
        ctx.getChannel().sendMessage(embed.build()).queue();
    }
}
