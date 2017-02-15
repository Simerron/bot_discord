package Test.bot.utils;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.ArrayList;

/**
 * Created by Simerron on 19/01/2017.
 */
public class CommandParser {
    public CommandContainer parse(String rw, MessageReceivedEvent e) {
        ArrayList<String> split = new ArrayList<String>();
        String raw = rw;
        String beheaded = raw.replaceFirst(".", "");
        String[] splitBeheaded = beheaded.split(" ");
        for (String s : splitBeheaded) {
            split.add(s);
        }
        String invoke = split.remove(0);
        String[] args = new String[splitBeheaded.length - 1];
        int i = 0;
        for (String s : split) {
            args[i] = s;
            i++;
        }

        return new CommandContainer(raw, beheaded, splitBeheaded, invoke, args, e);
    }

    public class CommandContainer {
        public final String raw;
        public final String beheaded;
        public final String[] splitBeheaded;
        public final String invoke;
        public final String[] args;
        public final MessageReceivedEvent event;

        public CommandContainer(String rw, String beheaded, String[] splitBeheaded, String invoke, String[] args, MessageReceivedEvent e) {
            this.raw = rw;
            this.beheaded = beheaded;
            this.splitBeheaded = splitBeheaded;
            this.invoke = invoke;
            this.args = args;
            this.event = e;
        }
    }
}
