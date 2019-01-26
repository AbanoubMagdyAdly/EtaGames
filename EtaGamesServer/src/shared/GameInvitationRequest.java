/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

/**
 *
 * @author a-tarek
 */
public class GameInvitationRequest extends Request{

 String opponentName;
    public GameInvitationRequest(String name) {
        this.opponentName = name;
    }

    @Override
    public Response handle() {
        // get ip from database in database
        
        // callAgameInvitationrequest to the ip 
      System.out.println("New Invitation Came! to "+ this.opponentName);
        return new Response(ResponseType.SUCCESS);
    }
    
}
