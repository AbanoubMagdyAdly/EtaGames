/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

import java.util.ArrayList;

/**
 *
 * @author a-tarek
 */
public class GetFriendsListRequest extends Request {

    private ArrayList<UserAccount> friends;
    private String username;
    public GetFriendsListRequest(String username) {

        this.username = username;
    }

    @Override
    public Response handle() {

        // Do stuff in database
        friends = new ArrayList<>();
        for (Integer i = 0; i < 4; i++) {
            friends.add(new UserAccount("friend #" + i.toString(), UserAccount.UserStatus.ONLINE));
        }
        for (UserAccount friend : this.friends) {
            System.out.println(friend.toString());
        }
        return new GetFriendsListResponse(ResponseType.Content, friends);

    }

}
