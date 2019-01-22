
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package etagames.xogame;

import etagames.xogame.GameBoard.CellValue;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author a-tarek
 */
public class AIPlayer extends Player {

    private final CellValue aiShape;

    public enum Level {
        EASY,
        MEDIUM,
        HARD
    }
    Level level;

    public AIPlayer(boolean isCross, Level level) {
        super(isCross);
        aiShape = (isCross) ? CellValue.x : CellValue.o;
        this.level = level;
    }

    @Override
    public void playTurn(XOGame game) {
//         for (CellValue c : game.getGameBoard().getCells())
//            System.out.print(c);

        int position = 0;
        CellValue cells[] = game.getGameBoard().getCells();
        switch (level) {
            case EASY:
                position = playEasy(cells);
                break;
            case MEDIUM:
                position = playMedium(cells);

                break;

            case HARD:
                position = playHard(cells);
                break;
        }
        game.tick(aiShape, position);
    }

//    enum cells { x, o , none}
//    aishape 
    public int playEasy(CellValue[] cells) {
        int position = 0;
        ArrayList<Integer> availableMoves = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            if (cells[i] == CellValue._) {
                availableMoves.add(i);
//                System.out.print(i);
            }
        }

        int numAvailableMoves = availableMoves.size();
        Random rand = new Random();

        position = rand.nextInt(numAvailableMoves);

        System.err.println(position);
        // insert ai logic for easy 
        return availableMoves.get(position);
    }

    public int playMedium(CellValue[] cells) {
        int position = 0;
        int numAvailableMoves;
        ArrayList<Integer> availableMoves = new ArrayList<>();
        ArrayList<Integer> winMoves = getWinMoves(cells);
//
        for (int i = 0; i < 9; i++) {
            if (cells[i] == CellValue._) {
                availableMoves.add(i);
            }
        }
        if (!winMoves.isEmpty()) {
            return winMoves.get(0);
        }
        numAvailableMoves = availableMoves.size();
        Random rand = new Random();

        position = rand.nextInt(numAvailableMoves);

        return availableMoves.get(position);
    }

    public int playHard(CellValue[] cells) {
        int position = 0;
        // insert ai logic for hard 
        return position;
    }

    private ArrayList<Integer> getWinMoves(CellValue[] cells) {
        ArrayList<Integer> winMoves = new ArrayList<>();

        if (cells[0] == cells[1] && cells[2] == CellValue._ && cells[0] != CellValue._ && cells[0] != CellValue._) {
            winMoves.add(2);
        } else if (cells[1] == cells[2] && cells[0] == CellValue._ && cells[1] != CellValue._) {
            winMoves.add(0);
        } else if (cells[2] == cells[0] && cells[1] == CellValue._ && cells[2] != CellValue._) {
            winMoves.add(1);
        } else if (cells[2] == cells[4] && cells[6] == CellValue._ && cells[2] != CellValue._) {
            winMoves.add(6);
        } else if (cells[6] == cells[4] && cells[2] == CellValue._ && cells[6] != CellValue._) {
            winMoves.add(2);
        } else if (cells[2] == cells[6] && cells[4] == CellValue._ && cells[2] != CellValue._) {
            winMoves.add(4);
        } else if (cells[0] == cells[4] && cells[8] == CellValue._ && cells[0] != CellValue._) {
            winMoves.add(8);
        } else if (cells[8] == cells[4] && cells[0] == CellValue._ && cells[8] != CellValue._) {
            winMoves.add(0);
        } else if (cells[8] == cells[0] && cells[4] == CellValue._ && cells[8] != CellValue._) {
            winMoves.add(4);
        } else if (cells[3] == cells[4] && cells[5] == CellValue._ && cells[3] != CellValue._) {
            winMoves.add(5);
        } else if (cells[3] == cells[5] && cells[4] == CellValue._ && cells[3] != CellValue._) {
            winMoves.add(4);
        } else if (cells[4] == cells[5] && cells[3] == CellValue._ && cells[4] != CellValue._) {
            winMoves.add(3);
        } else if (cells[6] == cells[7] && cells[8] == CellValue._ && cells[6] != CellValue._) {
            winMoves.add(8);
        } else if (cells[6] == cells[8] && cells[7] == CellValue._ && cells[6] != CellValue._) {
            winMoves.add(7);
        } else if (cells[7] == cells[8] && cells[6] == CellValue._ && cells[7] != CellValue._) {
            winMoves.add(6);
        } else if (cells[0] == cells[3] && cells[6] == CellValue._ && cells[0] != CellValue._) {
            winMoves.add(6);
        } else if (cells[6] == cells[0] && cells[3] == CellValue._ && cells[6] != CellValue._) {
            winMoves.add(3);
        } else if (cells[6] == cells[3] && cells[0] == CellValue._ && cells[6] != CellValue._) {
            winMoves.add(0);
        } else if (cells[1] == cells[4] && cells[7] == CellValue._ && cells[1] != CellValue._) {
            winMoves.add(7);
        } else if (cells[4] == cells[7] && cells[1] == CellValue._ && cells[4] != CellValue._) {
            winMoves.add(1);
        } else if (cells[7] == cells[1] && cells[4] == CellValue._ && cells[7] != CellValue._) {
            winMoves.add(4);
        } else if (cells[2] == cells[5] && cells[8] == CellValue._ && cells[2] != CellValue._) {
            winMoves.add(8);
        } else if (cells[5] == cells[8] && cells[2] == CellValue._ && cells[5] != CellValue._) {
            winMoves.add(2);
        } else if (cells[2] == cells[8] && cells[5] == CellValue._ && cells[2] != CellValue._) {
            winMoves.add(5);
        }
        return winMoves;
    }

}
