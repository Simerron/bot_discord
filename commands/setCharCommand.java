package Test.bot.commands;

import Test.bot.Command;
import Test.bot.MainBot;
import Test.bot.utils.SendMessage;
import Test.bot.utils.jdr.feuillePersonnage;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import javax.swing.text.Style;

/**
 * Created by Simerron on 03/02/2017.
 * Last edited by Simerron on 03/02/2017
 */
public class setCharCommand implements Command{
    private String HELP = ".set-char [Name][-a][-c][-e][-i][-m][-r][-s][-v] <param1> <param2 opt> <param3 opt>";

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        try {
            if (args[1].startsWith("-")) {
                feuillePersonnage perso = MainBot.persos.db.get(args[0]);
                if (event.getAuthor() != perso.getPlayer()) args[1] = "";
                if (args[1].equals("-a"))
                    perso.setMoney(new int[]{Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4])});
                else if (args[1].equals("-c"))
                    perso.setComp(args[2], Integer.parseInt(args[3]));
                else if (args[1].equals("-e"))
                    perso.setExp(Long.parseLong(args[2]));
                else if (args[1].equals("-i"))
                    perso.setInv(args[2], Integer.parseInt(args[3]));
                else if (args[1].equals("-m"))
                    perso.setManaPoints(Integer.parseInt(args[2]));
                else if (args[1].equals("-r"))
                    perso.setRace(args[2]);
                else if (args[1].equals("-s"))
                    perso.setStats(args[2], Integer.parseInt(args[3]));
                else if (args[1].equals("-v"))
                    perso.setLifePoints(Integer.parseInt(args[2]));
                else
                    System.out.println(args[1] + "and do nothing");
                new SendMessage(event.getTextChannel(), args[0] + " well edited");
            }else new SendMessage(event.getTextChannel(), "What do you want to edit ?");
        } catch (Exception e) {
            e.printStackTrace();
        }
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
