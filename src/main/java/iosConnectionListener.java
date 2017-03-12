
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author karol
 */
public class iosConnectionListener implements Runnable{
    
    private ServerSocket serverSocket;
    private ActivitySender sender;
    
    public iosConnectionListener(){
        
        //Create server socket
        try{
            serverSocket = new ServerSocket(45202);
        }catch(Exception e){
            System.err.print(e.getMessage());
            serverSocket = null;
        }
        
        //Start sending acitivities in queue
        sender = new ActivitySender();
        (new Thread(sender)).start();

    }

    @Override
    public void run() {
        while(true){
            try{
                
                System.out.println("Server listening for connections");
                    
                //Listen for incoming connections
                Socket clientSocket = serverSocket.accept();

                System.out.println("Connected to client: "+clientSocket.toString());
                System.out.println("Getting in and out stream");

                //Establishing input and output stream
                ObjectOutputStream out = new ObjectOutputStream(
                clientSocket.getOutputStream());

                //get input from iOS side
                BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
                
                //Begin sending activities to client
                sender.addModerator(out);
                
                //Create thread to listen to client activity
                iosActivityListener listener = new iosActivityListener(in,sender);
                (new Thread(listener)).start();


            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    
}
