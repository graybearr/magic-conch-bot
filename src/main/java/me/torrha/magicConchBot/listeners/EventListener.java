package me.torrha.magicConchBot.listeners;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class EventListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        User user = event.getAuthor();
        User bot = event.getJDA().getSelfUser();
        Message message = event.getMessage();
        if (user.isBot()) return;
        boolean isMentioned = message.getMentions().getUsers().stream().anyMatch(m -> m.getIdLong() == bot.getIdLong());

        if (isMentioned) {
            String content = message.getContentRaw().replaceAll(bot.getAsMention(), "");
            String[] answers = {"안돼.", "그래.", "다시 한 번 물어봐.", "둘 다 먹지마.", "굶어.", "그것도 안돼.", "가만히 있어."};
            Random rand = new Random();

            if (content.length() > 0) {
                event.getChannel().sendTyping().queue();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                event.getChannel().sendMessage(answers[rand.nextInt(7)]).setMessageReference(message).queue();
            } else return;
        }
    }
}
