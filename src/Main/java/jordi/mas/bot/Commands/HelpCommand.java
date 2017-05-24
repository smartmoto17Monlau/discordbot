package jordi.mas.bot.Commands;

import jordi.mas.bot.Command;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.sql.SQLException;

/**
 * Created by jordimasmer on 02/05/2017.
 */
public class HelpCommand implements Command {

    private final String HELP = "USAGE: !lastinfo";

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) throws SQLException, ClassNotFoundException {
        String message =
                "##############################################\n" +
                        "##                                           COMMAND HELP                                           ##\n" +
                        "##############################################\n" +
                        "\n"+
                        "   · !help     -->    Show help\n"+
                        "   · !ping    -->     Ping the bot to see if its connected\n"+
                        "   · !activate  -->     Activate SOS protocol and help messages\n"+
                        "   · !who     -->    Show Bike ID and user ID\n"+
                        "   · !map     -->    Returns map with the last known position\n"+
                        "   · !lastinfo -->  Returns all data from the last know info\n";

        event.getTextChannel().sendMessage(message).queue();
        System.out.println("["+event.getMessage().getCreationTime().getHour()+":"+
                event.getMessage().getCreationTime().getMinute()+":"+
                event.getMessage().getCreationTime().getSecond()+"] !help executed");
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
