package Test.bot.commands;

import Test.bot.Command;
import Test.bot.MainBot;
import Test.bot.utils.SendMessage;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Random;

/**
 * Created by Simerron on 01/02/2017.
 * Last edited by Simerron on 01/02/2017
 */
public class RollCommand implements Command {
    private static Random ran = new Random();
    private final String HELP = ".r [n]d[f]    ->  Renvoie Resultat de n dé à f faces";

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        event.getMessage().deleteMessage().queue();
        MainBot.log("Roll", event.getAuthor().getName());
        ran.setSeed(ran.nextLong());
        event.getMessage().deleteMessage().queue();
        int nb, nbDice, faces, die;
        long result = 0;
        String message = event.getMember().getAsMention() + " rolled " + args[0] + "[";
        String[] dice = args[0].split("d");
        if (dice.length == 0) {
            System.out.println("Error args");
            nbDice = 0;
            faces = 0;
        } else if (dice[0].length() == 0) {
            nbDice = 1;
        } else {
            nbDice = Integer.parseInt(dice[0]);
        }
        faces = Integer.parseInt(dice[1]);
        for (nb = 0; nb < nbDice; nb++) {
            die = ran.nextInt(faces) + 1;
            result += die;
            message = message + die + ", ";
        }
        message = message.substring(0, message.length() - 2);
        message += "] = " + result;
        new SendMessage(event.getTextChannel(), message);
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
