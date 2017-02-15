package Test.bot.commands;

import Test.bot.Command;
import Test.bot.MainBot;
import Test.bot.utils.SendMessage;
import Test.bot.utils.jdr.feuillePersonnage;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/**
 * Created by Simerron on 03/02/2017.
 * Last edited by Simerron on 03/02/2017
 */
public class getCharCommand implements Command {
    private String HELP = ".get-char [-a] [-c] [-h] [-i] [-m] [-s] [Name] -> affiche un personnage";

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        String message = event.getAuthor().getAsMention() + "\n";
        System.out.println(args[0]);
        if (args[0].startsWith("-")) {
            feuillePersonnage perso = MainBot.persos.db.get(args[1]);
            if (args[0].startsWith("-a"))
                message += perso.getHeader() + "\n" + perso.getMoney() + "\n" + perso.getStats() + "\n" + perso.getComps() + "\n" + perso.getInv();
            else if (args[0].startsWith("-c"))
                message += perso.getComps();
            else if (args[0].startsWith("-i"))
                message += perso.getInv();
            else if (args[0].startsWith("-m"))
                message += perso.getMoney();
            else if (args[0].startsWith("-s"))
                message += perso.getStats();
            else
                message += perso.getHeader();
        } else {
            feuillePersonnage perso = MainBot.persos.db.get(args[0]);
            if (event.getAuthor().getId().equals(perso.getPlayer())) message += perso.getHeader() + "\n" + perso.getStats();
            else
                message += perso.getHeader() + "\n" + perso.getMoney() + "\n" + perso.getStats() + "\n" + perso.getComps() + "\n" + perso.getInv();
        }
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
