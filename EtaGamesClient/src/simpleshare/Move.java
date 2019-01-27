/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpleshare;

import java.io.Serializable;

/**
 *
 * @author abano
 */
public class Move implements Serializable {

    String position;

    public Move(String p) {
        this.position = p;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String p) {
        this.position = p;
    }
}
