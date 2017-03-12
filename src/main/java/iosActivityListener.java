
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
    
    private ObjectInputStream in;
    private final ActivitySender sender;
    
    public iosActivityListener(ObjectInputStream in, ActivitySender sender){
        this.sender = sender;
        this.in = in;
    }
    
    @Override
    public void run() {
        try{
            
            //Keep listening to object input stream for user input
            while(true){
                
                System.out.println("ActivityListener() listening for input from user");
            
                String msg = (String)in.readObject();
                //Block till input is sent
                
                System.out.println("ActivityListener() receviced message: "+msg);

                synchronized(sender){
                    sender.addActivityToQueue(msg);
                    sender.notify();
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
