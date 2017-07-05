package jordi.mas.bot.Commands;

import jordi.mas.bot.Command;
import jordi.mas.bot.bbdd;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.managers.ChannelManager;
import net.dv8tion.jda.core.managers.ChannelManagerUpdatable;
import net.dv8tion.jda.core.requests.RestAction;
import net.dv8tion.jda.core.requests.restaction.InviteAction;
import net.dv8tion.jda.core.requests.restaction.PermissionOverrideAction;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 * Created by jordimasmer on 02/05/2017.
 */
public class LastCommand implements Command {

    private final String HELP = "USAGE: !lastinfo";

    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) throws SQLException, ClassNotFoundException {
        String message;
        bbdd db = new bbdd();
        String[] data = db.selectLastFila();
        message = "##############################################\n" +
                "##                                         LAST KNOWN INFO                                         ##\n" +
                "##############################################\n" +
                " · Moto ID: "+data[0]+"\n" +
                " · Speed: "+data[1]+"\n" +
                " · Max Speed: "+data[2]+"\n" +
                " · AVG Speed: "+data[3]+"\n" +
                " · Battery: "+data[4]+"\n" +
                " · Motor Temp: "+data[5]+"\n" +
                " · Battery Temp: "+data[6]+"\n" +
                " · Latitude: "+data[7]+"\n" +
                " · Longitude: "+data[8]+"\n" +
                " · FrontaL Rotation: "+data[9]+"\n" +
                " · Side Rotation: "+data[10]+"\n" +
                " · GLP: "+data[11]+"\n" +
                " · Propane: "+data[12]+"\n" +
                " · Natural Gas: "+data[13]+"\n" +
                " · CO: "+data[14]+"\n" +
                " · Butane: "+data[15]+"\n" +
                " · Hydrogen: "+data[16]+"\n" +
                " · Methane: "+data[17]+"\n" +
                " · CO2: "+data[18]+"\n" +
                " · RAD: "+data[20]+"\n" +
                " · Temperature: "+data[21]+"\n" +
                " · Humidity: "+data[22]+"\n" +
                " · Height: "+data[23]+"\n" +
                " · Atmosferic Pressure: "+data[24]+"\n" +
                " · Timestamp: "+data[25]+"\n" +
                " · Row ID: "+data[26];

        event.getTextChannel().sendMessage(message).queue();
        System.out.println("["+event.getMessage().getCreationTime().getHour()+":"+
                event.getMessage().getCreationTime().getMinute()+":"+
                event.getMessage().getCreationTime().getSecond()+"] !lastinfo executed");

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
