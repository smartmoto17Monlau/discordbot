package jordi.mas.bot;

import jordi.mas.bot.Commands.LastCommand;
import jordi.mas.bot.utils.CommandParser;
import net.dv8tion.jda.core.entities.MessageChannel;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by jordimasmer on 08/05/2017.
 */
public class tcpServer extends Thread {

    int clientSentence;
    ServerSocket welcomeSocket; //= new ServerSocket(6);
    Socket connectionSocket;
    public static MessageChannel channel;
    int[] port = new int[]{4, 6, 8, 10, 12};
    int i = 0;
    public tcpServer() throws IOException {

    }

    public void run(){
        while (true) {
            try{
                welcomeSocket = new ServerSocket(9009/*port[i]*/);
                connectionSocket = welcomeSocket.accept();
                //BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                DataInputStream dIn = new DataInputStream(connectionSocket.getInputStream());
                clientSentence = dIn.readInt();
                System.out.println("[SOS] system activated. Type: " + clientSentence);
                String sosmsg =
                        "##############################################\n" +
                        "##                                                 [SOS]: Crash                                               ##\n" +
                        "##############################################\n"+
                        "\n"+
                        " · An SOS help message was recieved from User001 \n" +
                        " · Its possible that User001 has suffered an accident \n" +
                        " · For additional info, use !help command or contact with the user."+
                        "\n";

                String batmsg =
                        "##############################################\n" +
                                "##                                                 [SOS]: Battery                                             ##\n" +
                                "##############################################\n"+
                                "\n"+
                                " · An SOS help message was recieved from User001 \n" +
                                " · The Battery level in User001's bike is less than 10% \n" +
                                " · For additional info, use !help command or contact with the user."+
                                "\n";
                String custommsg =
                                "##############################################\n" +
                                "##                                                 [SOS]: General                                             ##\n" +
                                "##############################################\n"+
                                "\n"+
                                " · A User triggered SOS help message was recieved from User001 \n" +
                                //" ·  \n" +
                                " · For additional info, use !help command or contact with the user."+
                                "\n";

                if(clientSentence == 0){
                    try{
                        channel.sendMessage(sosmsg).queue();
                    }catch(Exception e){
                        System.out.println("You need to activate the SOS system to use this functionality.");
                    }
                }else if(clientSentence == 1){
                    try{
                        channel.sendMessage(batmsg).queue();
                    }catch(Exception e){
                        System.out.println("You need to activate the SOS system to use this functionality.");
                    }
                }else if(clientSentence == 2){
                    try{
                        channel.sendMessage(custommsg).queue();
                    }catch(Exception e){
                        System.out.println("You need to activate the SOS system to use this functionality.");
                    }
                }
                welcomeSocket.close();
                i++;
                if(i == 4 ){
                    i = 0;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
