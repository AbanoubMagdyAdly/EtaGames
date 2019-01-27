/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etagames.xogame;

import etagames.xogame.XOGame.GameState;

/**
 *
 * @author a-tarek
 */
public class GameBoard {

    public enum CellValue {
        x,
        o,
        none
    }

    private CellValue[] cells = new CellValue[9];

    public GameBoard() {
        System.out.println("etagames.xogame.GameBoard.<init>()");
        for (int i = 0; i < 9; i++) {
            cells[i] = CellValue.none;
        }
    }

    // updates the logical and physical board at new selected position with new cellvalue  
    public void setCellOnBoard(CellValue v, int position) {
        System.out.println("etagames.xogame.GameBoard.setCellOnBoard()");
        cells[position] = v;
        String vCellValue = (v == CellValue.x) ? "X" : "O";
    }

    // returns the gui of the board

    // returns a new gameState 
    public GameState checkGameBoard(CellValue cv) {

        System.out.println("etagames.xogame.GameBoard.checkGameBoard()");
        GameState gState = GameState.playable;

        for (int i = 0; i < 9; i=i+3) {
            if (cells[i] == cells[i + 1] && cells[i] == cells[i + 2] && cells[i] == cv) {
                gState = (cv == CellValue.x) ? GameState.player1_win : GameState.player2_win;
            }
        }

        for (int i = 0; i < 3; ++i) {
            if (cells[i] == cells[i + 3] && cells[i] == cells[i + 6] && cells[i] == cv) {
                gState = (cv == CellValue.x) ? GameState.player1_win : GameState.player2_win;
            }
        }

        if (cells[0] == cells[4] && cells[0] == cells[8] && cells[0] == cv) {
            gState = (cv == CellValue.x) ? GameState.player1_win : GameState.player2_win;
        }

        if (cells[2] == cells[4] && cells[2] == cells[6] && cells[2] == cv) {
            gState = (cv == CellValue.x) ? GameState.player1_win : GameState.player2_win;
        }

        return gState;
    }

    CellValue[] getCells() {
        return cells;
    }
}
