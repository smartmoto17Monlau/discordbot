package jordi.mas.bot;

import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.sql.SQLException;

/**
 * Created by jordimasmer on 27/04/2017.
 */
public class BotListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event ){
        if(event.getMessage().getContent().startsWith("!") && event.getMessage().getAuthor().getId() != event.getJDA().getSelfUser().getId()){
            try {
                Bot.handleCommand(Bot.parser.parse(event.getMessage().getContent().toLowerCase(), event));
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onReady(ReadyEvent event){
        //Bot.log("status", "Logged in as: " + event.getJDA().getSelfUser().getName());

    }
}
