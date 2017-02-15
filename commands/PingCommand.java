package Test.bot.commands;

import Test.bot.Command;
import Test.bot.MainBot;
import Test.bot.utils.SendMessage;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.exceptions.PermissionException;

/**
 * Created by Simerron on 19/01/2017.
 */
public class PingCommand implements Command {
    private final String HELP = ".ping                   ->  Renvoie PONG!";
    private SendMessage Message;

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        MainBot.log("Command Log", "[Ping] From User : " + event.getAuthor());
        Message = new SendMessage(event.getTextChannel(), "PONG!");
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
