/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etagames.xogame;

/**
 *
 * @author a-tarek
 */
public abstract class Player {
 
   protected boolean isCross;
    boolean hasTurn;
    public Player(boolean isCross){
        this.isCross = isCross;
    }
    
    
    // playTurn takes the game board and selects a position
    public abstract void playTurn(XOGame game);
    
}
