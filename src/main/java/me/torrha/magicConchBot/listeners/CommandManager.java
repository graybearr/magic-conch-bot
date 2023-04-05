package me.torrha.magicConchBot.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CommandManager extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        String command = event.getName();
        if (command.equals("도움말")) {
            EmbedBuilder helpEmbed = new EmbedBuilder();
            helpEmbed.setTitle("마법의 소라고둥 봇");
            helpEmbed.addField("/도움말", "마법의 소라고둥님을 사용하는 방법을 배웁니다.", false);
            helpEmbed.addField("/질문 [내용]", "마법의 소라고둥님에게 질문을 합니다.", false);
            helpEmbed.addField("@마법의 소라고둥", "마법의 소라고둥님을 멘션하며 메시지를 보내도 됩니다.", false);

            event.replyEmbeds(helpEmbed.build()).queue();
        }
        else if (command.equals("질문")) {
            String[] answers = {"안돼.", "그래.", "다시 한 번 물어봐.", "둘 다 먹지마.", "굶어.", "그것도 안돼.", "가만히 있어."};
            Random rand = new Random();
            event.reply(answers[rand.nextInt(7)]).queue();
        }
    }

    @Override
    public void onGuildReady(GuildReadyEvent event) {
        List<CommandData> commandData = new ArrayList<>();
        commandData.add(Commands.slash("도움말", "마법의 소라고둥님을 사용하는 방법을 배웁니다."));

        OptionData askOption = new OptionData(OptionType.STRING, "내용", "마법의 소라고둥님에게 질문할 내용을 입력해주세요.", true);
        commandData.add(Commands.slash("질문", "마법의 소라고둥님에게 질문을 합니다.").addOptions(askOption));

        event.getGuild().updateCommands().addCommands(commandData).queue();
    }
}
