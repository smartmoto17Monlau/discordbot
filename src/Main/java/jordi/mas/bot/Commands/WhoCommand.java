package jordi.mas.bot.Commands;

import jordi.mas.bot.Command;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/**
 * Created by jordimasmer on 02/05/2017.
 */
public class WhoCommand implements Command {

    private final String HELP = "USAGE: !who";

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        String who = "##############################################\n" +
                "##         I'm bike 001        #######       User: 001 using the bike          ##\n" +
                "##############################################";

        event.getTextChannel().sendMessage(who).queue();
        System.out.println("["+event.getMessage().getCreationTime().getHour()+":"+
                event.getMessage().getCreationTime().getMinute()+":"+
                event.getMessage().getCreationTime().getSecond()+"] !who executed");
    }

    @Override
    public String help() {
        return help();
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }
}
