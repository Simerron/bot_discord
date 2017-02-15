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
public class setCharCommand implements Command {
    private String HELP = ".set-char [Name][-a][-c][-e][-i][-m][-r][-s][-v] <param1> <param2 opt> <param3 opt>";

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        for (int i = 1, next = 1; i < args.length; i++) {
            if (i == next) {
                try {
                    System.out.println(i + " " + next + " " + args.length);
                    if (args[i].startsWith("-")) {
                        feuillePersonnage perso = MainBot.persos.db.get(args[0]);
                        if (args[i].startsWith("-a")) {
                            perso.setMoney(new int[]{Integer.parseInt(args[i + 1]), Integer.parseInt(args[i + 2]), Integer.parseInt(args[i + 3])});
                            next += 4;
                        } else if (args[i].startsWith("-c")) {
                            perso.setComp(args[i + 1], Integer.parseInt(args[i + 2]));
                            next += 3;
                        } else if (args[i].startsWith("-e")) {
                            perso.setExp(Long.parseLong(args[i + 1]));
                            next += 2;
                        } else if (args[i].startsWith("-i")) {
                            perso.setInv(args[i + 1], Integer.parseInt(args[i + 2]));
                            next += 3;
                        } else if (args[i].startsWith("-m")) {
                            perso.setManaPoints(Integer.parseInt(args[i + 1]));
                            next += 2;
                        } else if (args[i].startsWith("-r")) {
                            perso.setRace(args[i + 1]);
                            next += 2;
                        } else if (args[i].startsWith("-s")) {
                            perso.setStats(args[i + 1], Integer.parseInt(args[i + 2]));
                            next += 3;
                        } else if (args[i].startsWith("-v")) {
                            perso.setLifePoints(Integer.parseInt(args[i + 1]));
                            next += 2;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        new SendMessage(event.getTextChannel(), args[0] + " well edited");
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
