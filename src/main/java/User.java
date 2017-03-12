
import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author karol
 */
public class User implements Serializable{
    private final String userName;
    private final String chatRoom;
    
    public User(String userName, String chatRoom){
        this.userName = userName;
        this.chatRoom = chatRoom;
    }
    
    public String getUserName(){
        return userName;
    }
    
    public String getChatRoom(){
        return chatRoom;
    }
    
}
