package Test.bot;

import Test.bot.commands.*;
import Test.bot.utils.CommandParser;
import Test.bot.commands.Help;
import Test.bot.utils.jdr.dataBase;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.exceptions.RateLimitedException;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;


/**
 * Created by Simerron on 19/01/2017.
 * Last edited by Simerron on 19/01/2017
 */
public class MainBot {
    private JDA jda;

    private boolean stop = false;
    public  static final CommandParser parser = new CommandParser();

    public static HashMap<String, Command> commands = new HashMap<String, Command>();
    public static dataBase persos;

    MainBot(String token) {
        try {
            jda = new JDABuilder(AccountType.BOT).setToken(token).addListener(new BotListener()).setGame(Game.of("type .help for help")).setBulkDeleteSplittingEnabled(true).buildBlocking();
            jda.setAutoReconnect(true);
            persos = new dataBase(); //créer une base de donnée Hashmap pour sauvegarder les personnages
            persos.retrieveData(); //récupère les feuilles de personnage serialisé
        } catch (LoginException | RateLimitedException | InterruptedException e) {
            e.printStackTrace();
            System.out.println("Une erreur est survenue veuilliez verifier le token ou votre connection internet");
            return;
        }
        //Listing Commands
        commands.put("help", new Help());
        commands.put("ping", new PingCommand());
        commands.put("pong", new PongCommand());
        commands.put("purge", new PurgeCommand());
        commands.put("say", new SayCommand());
        commands.put("r", new RollCommand());
        commands.put("add-char", new addCharCommand());
        commands.put("get-char", new getCharCommand());
        commands.put("set-char", new setCharCommand());

        //Start Logs
        int i;
        System.out.println("Connecte avec: " + jda.getSelfUser());
        System.out.println("Le bot est autorisé sur " + (i = jda.getGuilds().size()) + " serveur" + (i > 1 ? "s" : ""));

        //Wait for stop loop
        while (!stop) {
            Scanner scanner = new Scanner(System.in);
            String cmd = scanner.next();
            if (cmd.equalsIgnoreCase("stop")) {
                jda.shutdown(true);
                stop = true;
            }
        }
    }
    public static AudioPlayerManager playerManager;

    public static void handleCommand(CommandParser.CommandContainer cmd) {
        if (commands.containsKey(cmd.invoke.toLowerCase())) {
            boolean safe = commands.get(cmd.invoke.toLowerCase()).called(cmd.args, cmd.event);

            if (safe) {
                commands.get(cmd.invoke).action(cmd.args, cmd.event);
                commands.get(cmd.invoke).execute(safe, cmd.event);
            }
        }
    }

    public static void log(String evnt, String who){
        FileOutputStream logOutput = null;
        try {
            logOutput = new FileOutputStream(new File("Log.txt"));
            String log = "["+ LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))+ " " +LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss"))+"]"+" ["+evnt+" log  ] "+who;
            logOutput.write(log.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                logOutput.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Veulliez indiquer le token du bot");
        }
        new MainBot(args[0]);
    }

}
