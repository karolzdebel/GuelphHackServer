
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
public class Message extends UserActivity implements Serializable{
    private String content;
    private Date date;
    private String chatroom;
    
    public Message(String content, User author, Date date, String chatroom){
        super(author);
        this.content = content;
        this.date = date;
        this.chatroom = chatroom;
    }
}
