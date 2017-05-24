package jordi.mas.bot;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.sql.SQLException;

/**
 * Created by jordimasmer on 27/04/2017.
 */
public interface Command {

    public boolean called(String[] args, MessageReceivedEvent event);
    public void action(String[] args, MessageReceivedEvent event) throws SQLException, ClassNotFoundException;
    public String help();
    public void executed(boolean success, MessageReceivedEvent event);
}
