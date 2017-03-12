
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
public class UserActivity implements Serializable{
    
    private User user;
    
    public UserActivity(User user){
        this.user = user;
    }
    
    public User getUser(){
        return user;
    }
}
