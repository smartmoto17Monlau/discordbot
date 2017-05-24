package jordi.mas.bot.utils;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import sun.plugin2.message.Message;

import java.util.ArrayList;

/**
 * Created by jordimasmer on 27/04/2017.
 */
public class CommandParser {

    public CommandContainer parse(String rw, MessageReceivedEvent e){
        ArrayList<String> split = new ArrayList<>();
        String raw = rw;
        String beheaded =  raw.replaceFirst("!", "");
        String[] splitBeheaded = beheaded.split(" ");
        for(String s : splitBeheaded){
            split.add(s);
        }
        String invoke = split.get(0);
        String[] args = new String[split.size()-1];
        split.subList(1, split.size()).toArray(args);

        return new CommandContainer(raw, beheaded, splitBeheaded, invoke, args, e);
    }

    public class CommandContainer{
        public final String raw;
        public final String beheaded;
        public final String[] splitBeheaded;
        public final String invoke;
        public final String[] args;
        public final MessageReceivedEvent event;

        public CommandContainer(String rw, String beheaded, String[] splitBeheaded, String invoke, String[] args, MessageReceivedEvent e){
            this.raw = rw;
            this.beheaded = beheaded;
            this.splitBeheaded = splitBeheaded;
            this.invoke = invoke;
            this.args = args;
            this.event = e;
        }
    }
}
