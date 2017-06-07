package jordi.mas.bot;

import jordi.mas.bot.Commands.*;
import jordi.mas.bot.utils.CommandParser;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created by jordimasmer on 27/04/2017.
 */
public class Bot {

    private static JDA jda;

    public static HashMap<String, Command> commands =new HashMap<>();

    public static final CommandParser parser = new CommandParser();

    public static void main(String[] args) throws IOException {

        try{
            jda = new JDABuilder(AccountType.BOT).addEventListener(new BotListener()).setToken("Mjk4NzEzMDg4OTAxNzc1MzYw.C8TVwg.yUh7FvmEGrVXuQ0fvTCaPtS8W8M").buildBlocking();
            bbdd db = new bbdd();
            jda.setAutoReconnect(true);

        }catch(Exception e){
            e.printStackTrace();
        }

        commands.put("ping", new PingCommand());
        commands.put("who", new WhoCommand());
        commands.put("lastinfo", new LastCommand());
        commands.put("help", new HelpCommand());
        commands.put("map", new MapCommand());
        commands.put("activate", new ActivarCommand());
        System.out.println("");

        tcpServer tcp = new tcpServer();
        tcp.start();


    }

    public static void handleCommand(CommandParser.CommandContainer cmd) throws SQLException, ClassNotFoundException {
        if(commands.containsKey(cmd.invoke)){
            boolean safe = commands.get(cmd.invoke).called(cmd.args, cmd.event);

            if(safe){
                commands.get(cmd.invoke).action(cmd.args, cmd.event);
                commands.get(cmd.invoke).executed(safe, cmd.event);
            }else{
                commands.get(cmd.invoke).executed(safe, cmd.event);
            }
        }
    }
}
