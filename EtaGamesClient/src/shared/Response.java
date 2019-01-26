/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

import java.io.Serializable;

/**
 *
 * @author abano
 */
public class Response implements Serializable{

    public ResponseType responseType;
    public Response(ResponseType type){
        this.responseType = type;
    }
      public ResponseType getType(){
        return this.responseType;
    }
    
}
