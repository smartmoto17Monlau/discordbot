package jordi.mas.bot.Commands;

import jordi.mas.bot.Command;
import jordi.mas.bot.bbdd;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.sql.SQLException;

/**
 * Created by jordimasmer on 02/05/2017.
 */
public class MapCommand implements Command {
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) throws SQLException, ClassNotFoundException {
        String message;
        bbdd db = new bbdd();
        String[] data = db.selectLastFila();
        String lat = data[7];
        String lng = data[8];

        double[] glat = decToG(lat);
        double[] glng = decToG(lng);

         message =
                "##############################################\n" +
                "##                                    LAST KNOWN POSITION                                     ##\n" +
                "##############################################\n" +
                "\n" +
                "https://www.google.com/maps/place/" + glat[0] + "%C2%B0" + glat[1] + "'" + glat[2] + "%22N+" + glng[0] + "%C2%B0" + glng[1] + "'" + glng[2] + "%22E/@" + transformComma(lat) + "," + transformComma(lng) + ",507m/data=!3m1!1e3!4m5!3m4!1s0x0:0x0!8m2!3d" + transformComma(lat) + "!4d" + transformComma(lng);
                event.getTextChannel().sendMessage(message).queue();

         System.out.println("["+event.getMessage().getCreationTime().getHour()+":"+
                event.getMessage().getCreationTime().getMinute()+":"+
                event.getMessage().getCreationTime().getSecond()+"] !map executed");
    }

    @Override
    public String help() {
        return null;
    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {

    }

    private String transformComma(String sin)
    {
        char[] array = sin.toCharArray();
        char[] arrayNew = new char[array.length];
        String finalString;

        for(int i = 0; i < array.length; i++)
        {
            if(array[i] == ','){
                arrayNew[i] = '.';
            }else{
                arrayNew[i] = array[i];
            }
        }

        finalString = new String(arrayNew);

        return finalString;
    }

    private double[] decToG(String decim)
    {
        double decimal_degrees = Double.parseDouble(decim);

        // set decimal_degrees value here
        double minutes = (decimal_degrees - Math.floor(decimal_degrees)) * 60.0;
        double seconds = (minutes - Math.floor(minutes)) * 60.0;

        // get rid of fractional part
        double degrees = Math.abs(decimal_degrees);
        minutes = Math.floor(minutes);
        seconds = Math.floor(seconds);

        double[] grados = { degrees , minutes, seconds };

        return grados;
    }
}
