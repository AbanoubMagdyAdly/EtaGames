/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

/**
 *
 * @author abano
 */
public class SignInRequest extends Request{

    String name;
    String password;
    public SignInRequest (String name, String password){
        this.name = name;
        this.password = password;
    }
    @Override
    public Response handle() {
        System.out.println("New user registerd" + this.name);
        /*
        DBConnection db = new DBConnection();
        String password = db.getPassword(this.name);
        return (this.password == passwrod)? Reply.SUCCESS :Reply.MATCH_ERROR; 
        db.close();
        */
        return new Response(ResponseType.SUCCESS);
    }
}