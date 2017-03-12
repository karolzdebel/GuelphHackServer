/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author karol
 */
public class ServerSide {
    
    
    
    public static void main(String[] args){
        
        //Begin listening for connections
        iosConnectionListener listener = new iosConnectionListener();
        (new Thread(listener)).start();
        
    }
}
