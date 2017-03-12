
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Hashtable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author karol
 */
public class ActivitySender implements Runnable{
    private final ArrayList<UserActivity> activityQueue;
    private final ArrayList<ObjectOutputStream> moderatorOutArr;

    public ActivitySender(){
        activityQueue = new ArrayList<>();
        moderatorOutArr = new ArrayList<>();
    }
    
    public void addActivityToQueue(UserActivity activity){
        activityQueue.add(activityQueue.size(), activity);
    }

    public void addModerator(ObjectOutputStream o){
        moderatorOutArr.add(o);
    }
    
    public void removeModerator(ObjectOutputStream o){
        moderatorOutArr.remove(o);
    }
    
    @Override
    public void run() {
        while(true){
            
            //Wait for activity to be added
            synchronized(this){
                while (activityQueue.isEmpty()){
                    try{
                        this.wait();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
                
                //Dequeue first activity
                UserActivity inActivity = activityQueue.get(0);
                activityQueue.remove(0);
                System.out.println("ChatRoomServerNetwork received activity and dequeued.");
                
                //Broadcast activity to all moderators
                for (ObjectOutputStream out: moderatorOutArr){
                    try{
                        out.writeObject(inActivity);

                    }
                        catch(Exception e){
                            System.err.print("Error broadcasting activity: "+e.getMessage());
                            e.printStackTrace();
                    }
                }
                
            }
        }
        
    }
}
