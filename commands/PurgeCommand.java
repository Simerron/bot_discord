package Test.bot.commands;

import Test.bot.Command;
import Test.bot.MainBot;
import Test.bot.utils.SendMessage;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.MessageHistory;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import net.dv8tion.jda.core.utils.PermissionUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Simerron on 21/01/2017.
 */
public class PurgeCommand implements Command {
    private MessageHistory history;
    private final String HELP = ".purge                ->  Efface 100 messages";

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        if (event.getMember().hasPermission(event.getTextChannel(), Permission.MESSAGE_MANAGE)) {
            MainBot.log("Command Log", "[Purge] From User : " + event.getAuthor());
            TextChannel channel = event.getTextChannel();
            history = new MessageHistory(channel);
            Collection<Message> messages;
            try {
                messages = history.retrievePast(100).block();
                channel.deleteMessages(messages).queue();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error");
            }
        } else {
            new SendMessage(event.getTextChannel(), "T'a pas le droit petit fifrelin !");
        }
    }

    @Override
    public String help() {
        return HELP;
    }

    @Override
    public void execute(boolean success, MessageReceivedEvent event) {return;
    }
}
