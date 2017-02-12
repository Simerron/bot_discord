package Test.bot;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/**
 * Created by Simerron on 19/01/2017.
 */
public interface Command {

    public boolean called(String[] args, MessageReceivedEvent event);
    public void action(String[] args, MessageReceivedEvent event);
    public String help();
    public void execute(boolean success, MessageReceivedEvent event);


}
