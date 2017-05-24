package jordi.mas.bot.Commands;

import jordi.mas.bot.Command;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/**
 * Created by jordimasmer on 27/04/2017.
 */
public class PingCommand  implements Command{

    private final String HELP = "USAGE: !ping";

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) {
        event.getTextChannel().sendMessage("Pong").queue();
        System.out.println("["+event.getMessage().getCreationTime().getHour()+":"+
                event.getMessage().getCreationTime().getMinute()+":"+
                event.getMessage().getCreationTime().getSecond()+"] !ping executed");
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
