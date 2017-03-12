
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
            serverSocket = new ServerSocket(45201);
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
//                ObjectOutputStream out = new ObjectOutputStream(
//                clientSocket.getOutputStream());
//                BufferedReader in =
//                    new BufferedReader(
//                    new InputStreamReader(clientSocket.getInputStream()));
//                ObjectInputStream in = new ObjectInputStream(
//                    clientSocket.getInputStream());   
                BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
                
                while(true){
                    String str = in.readLine();
                }
                System.out.println("Established input and output streams successfully.");
                System.out.print("Received string "+str);
                
                
                
//
//                //Begin sending activities to client
//                sender.addModerator(out);
//                
//                //Create thread to listen to client activity
//                iosActivityListener listener = new iosActivityListener(in,sender);
//                (new Thread(listener)).start();


            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    
    
}
