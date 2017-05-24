package jordi.mas.bot.Commands;

import jordi.mas.bot.Command;
import jordi.mas.bot.tcpServer;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.sql.SQLException;

/**
 * Created by jordimasmer on 08/05/2017.
 */
public class ActivarCommand implements Command{

    private final String HELP = "USAGE: !activate";

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) throws SQLException, ClassNotFoundException {
            String message = "SOS system activated";
            event.getTextChannel().sendMessage(message).queue();
            tcpServer.channel = event.getTextChannel();
    }

    @Override
    public String help() {
        return HELP;
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
        return;
    }
}
