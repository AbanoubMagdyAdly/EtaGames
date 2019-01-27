package shared;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;

/**
 *
 * @author a-tarek
 */
public class OldRequest implements Serializable {

    public enum RequestType {
        SIGNIN,
        SIGNUP,
        XOGAME,
        ADDFRIEND,
        REMOVEFRIEND,
        GETFRIENDS
    }
    private RequestType type;
    private String param1;
    private String param2;

    public OldRequest(RequestType t, String p1, String p2) {
        this.type = t;
        this.param1 = p1;
        this.param2 = p2;
    }
    
    public RequestType getRequestType(){
        return type;
    }
    
    public String getParam1(){
        return param1;
    }
    
    public String getParam2(){
        return param2;
    }
}
