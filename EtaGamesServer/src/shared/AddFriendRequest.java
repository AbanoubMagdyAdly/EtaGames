/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

import shared.ResponseType;

/**
 *
 * @author abano
 */
public class AddFriendRequest extends Request{

    private String userName1;
    private String userName2;
    
    public AddFriendRequest(String name1, String name2){
        this.userName1 = name1;
        this.userName2 = name2;

    }
    @Override
    public Response handle() {
        DBConnection db = new DBConnection();
        db.addFriend(this.userName1, this.userName2);
        return new Response(ResponseType.SUCCESS);
    }
    
}
