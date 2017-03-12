
import java.io.ObjectInputStream;

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
    }

    //Parse JSON formatted string and return data in UserActivity object
    public UserActivity strToActivity(String json){
        
        return null;
    }
    
    @Override
    public void run() {
        try{
            
            //Keep listening to object input stream for user input
            while(true){
                
                System.out.println("ActivityListener() listening for input from user");

                //blocks here till object is sent
                String msg = (String)in.readObject();
                
                System.out.println("ActivityListener() receviced message: "+msg);

                
//                synchronized(sender){
//                    sender.addActivityToQueue(inActivity);
//                    sender.notify();
//                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}