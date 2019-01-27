/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etagames.xogame;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import simpleshare.Move;

/**
 *
 * @author abano
 */
public class LocalOnlinePlayer extends Player{
     PrintStream ps;
//     ObjectOutputStream oos;

    public LocalOnlinePlayer(boolean isCross, Socket myClientSocket) {
        super(isCross);
        System.out.println("etagames.xogame.OfflinePlayer.<init>()" + this.isCross);
         try {
//             oos = new ObjectOutputStream(myClientSocket.getOutputStream());
             
             ps = new PrintStream(myClientSocket.getOutputStream());
         } catch (IOException ex) {
             Logger.getLogger(LocalOnlinePlayer.class.getName()).log(Level.SEVERE, null, ex);
         }
    }

    @Override
    public void playTurn(XOGame game) {
        for (VisualCell cell : game.getVisualGameBoard().getVisualCells()) {
            cell.setOnAction(e -> {
                System.out.println(cell.getId());
                GameBoard.CellValue cv = (this.isCross) ? GameBoard.CellValue.x : GameBoard.CellValue.o;
                ps.println(cell.getId());
                //                    oos.writeObject(new Move(cell.getId()));
                game.tick(cv, Integer.parseInt(cell.getId()));
            });
        }
    }

}
