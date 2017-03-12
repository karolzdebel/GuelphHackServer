
import java.io.Serializable;
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
public class Message implements Serializable{
    private String content;
    private User author;
    private Date date;
    private String chatroom;
    
    public Message(String content, User author, Date date, String chatroom){
        this.content = content;
        this.author = author;
        this.date = date;
        this.chatroom = chatroom;
    }
}
