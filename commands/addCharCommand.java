package Test.bot.commands;

import Test.bot.Command;
import Test.bot.MainBot;
import Test.bot.utils.SendMessage;
import Test.bot.utils.jdr.feuillePersonnage;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class addCharCommand implements Command {
    private final String HELP = ".add-char [Name] -> Créer une feuille de perso";

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        if (args[0] != null)
            return true;
        else return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        MainBot.persos.db.put(args[0], new feuillePersonnage(args[0], event.getAuthor().getId()));
        new SendMessage(event.getTextChannel(), "Personnage : " + args[0] + " créé");
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
