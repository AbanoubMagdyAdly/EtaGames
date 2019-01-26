/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etagames.xogame;

import etagames.xogame.GameBoard.CellValue;

/**
 *
 * @author a-tarek
 */
public class OfflinePlayer extends Player {


    public OfflinePlayer(boolean isCross) {
        super(isCross);
        System.out.println("etagames.xogame.OfflinePlayer.<init>()" + this.isCross);
    }

    @Override
    public void playTurn(XOGame game) {
        for (VisualCell cell : game.getVisualGameBoard().getVisualCells()) {
            cell.setOnAction(e -> {
                System.out.println(cell.getId());
                
                    CellValue cv = (this.isCross) ? CellValue.x : CellValue.o;
                    game.tick(cv, Integer.parseInt(cell.getId()));
            });
        }
    }

}
