package Test.bot.commands;

import Test.bot.Command;
import Test.bot.MainBot;
import Test.bot.utils.SendMessage;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/**
 * Created by Simerron on 25/01/2017.
 */
public class SayCommand implements Command {
    private final String HELP = ".say [text]         ->  Renvoie text";

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        MainBot.log("Command Log", "[Say] From User : "+event.getAuthor());
        StringBuilder builder = new StringBuilder();
        for(String s : args) {
            if ((s!= null)) {
                builder.append(s);
                builder.append(' ');
            }
        }
        event.getMessage().deleteMessage().queue();
        SendMessage message = new SendMessage(event.getTextChannel(), builder.toString());
    }

    @Override
    public String help() {
        return HELP;
    }

    @Override
    public void execute(boolean success, MessageReceivedEvent event) {
        return;
    }
}
