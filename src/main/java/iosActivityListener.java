
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author karol
 */
public class iosActivityListener implements Runnable{
    
    private BufferedReader in;
    private final ActivitySender sender;
    
    public iosActivityListener(BufferedReader in, ActivitySender sender){
        this.sender = sender;
    }
    
    private class GsonMessage{
        String channel_name;
        String user_id;
        String message;
        String flag;
        String time;
    }
    
    //Parse JSON formatted string and return data in UserActivity object
    public UserActivity strToActivity(String json){
        
        Gson gson = new Gson();
        
        GsonMessage gsonMessage;
        gsonMessage = gson.fromJson(json, GsonMessage.class);
        
        if (gsonMessage.flag.equals("true")){
            Flag flag = new Flag(new User(gsonMessage.user_id,gsonMessage.channel_name));
            return flag;
            
        }
        else{
            Date date = new Date(gsonMessage.time);
            System.out.println("Date: "+date.toString());
            Message message = new Message(gsonMessage.message
                    , new User(gsonMessage.user_id,gsonMessage.channel_name)
                    , date, gsonMessage.channel_name);
            return message;
        }
        
    }
    
    @Override
    public void run() {
        try{
            
            //Keep listening to object input stream for user input
            while(true){
                
                System.out.println("ActivityListener() listening for input from user");
            
                String msg = null;
                //Block till input is sent
                if (in.ready()){
                    msg = (String)in.readLine();
                }
                System.out.println("ActivityListener() receviced message: "+msg);

                
                synchronized(sender){
                    sender.addActivityToQueue(strToActivity(msg));
                    sender.notify();
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
