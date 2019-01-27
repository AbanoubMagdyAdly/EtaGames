/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author a-tarek
 */
public class GetFriendsListResponse extends Response{
    
    
    ArrayList<UserAccount> friends;
    public GetFriendsListResponse(ResponseType type,ArrayList<UserAccount> users) {
        super(type);
        friends = users;
    }
    
    public ArrayList<UserAccount> getFriends(){
        return this.friends;
    }
    
    
    
}
