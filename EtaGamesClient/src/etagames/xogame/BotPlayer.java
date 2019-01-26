/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etagames.xogame;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

/**
 *
 * @author abano
 */
public class BotPlayer extends Player {

    public static int count = 0;
    public static int[] replayArray = new int[9];
    private final GameBoard.CellValue botShape;

    public BotPlayer(boolean isCross) {
        super(isCross);
        botShape = (isCross) ? GameBoard.CellValue.x : GameBoard.CellValue.o;
        count = 0;
    }

    @Override
    public void playTurn(XOGame game) {
        System.out.println("Array of count" + replayArray[count] + "  " + botShape);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(BotPlayer.class.getName()).log(Level.SEVERE, null, ex);
                }
                game.tick(botShape, replayArray[count++]);
            }
        });

    }

}
