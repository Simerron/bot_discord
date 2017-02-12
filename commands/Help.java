package Test.bot.commands;

import Test.bot.Command;
import Test.bot.MainBot;
import Test.bot.utils.SendMessage;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import static Test.bot.MainBot.commands;

/**
 * Created by Simerron on 01/02/2017.
 * Last edited by Simerron on 01/02/2017
 */
public class Help implements Command {
    private final String HELP = ".help                   ->  Renvoie ce message.";

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        String res = "Ceci est la liste de toutes les commandes :\n";
        for (String cmd: commands.keySet()) {
            res +=commands.get(cmd).help() + "\n";
        }
        new SendMessage(event.getTextChannel(), res);
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
