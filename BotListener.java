package Test.bot;

import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.MessageUpdateEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

/**
 * Created by Simerron on 19/01/2017.
 */
public class BotListener extends ListenerAdapter {
    private void scanMessage(MessageReceivedEvent event) {
        if (event.getMessage().getContent().startsWith(".") && event.getMessage().getAuthor().getId() != event.getJDA().getSelfUser().getId()) {
            MainBot.handleCommand(MainBot.parser.parse(event.getMessage().getRawContent(), event));
        }
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        scanMessage(event);
    }

    @Override
    public void onMessageUpdate(MessageUpdateEvent event) {
        MessageReceivedEvent e = new MessageReceivedEvent(event.getJDA(), event.getResponseNumber(), event.getMessage());
        scanMessage(e);
    }


    @Override
    public void onReady(ReadyEvent event) {
        MainBot.log("Status Log", "[Booted] Bot : " + event.getJDA().getSelfUser());
    }

}
