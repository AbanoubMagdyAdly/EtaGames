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
    //CellValue[] cells = new CellValue[9];

    public enum Level {
        EASY,
        MEDIUM,
        HARD
    }
    Level level;
    int scale;

    public AIPlayer(boolean isCross ,Level level) {
        super(isCross);
        aiShape = (isCross) ? CellValue.x : CellValue.o;
        this.level = level;
        //GameBoard gb=new GameBoard();
        //gb.getCells();
    }

    @Override
    public void playTurn(XOGame game) {
        int position = 0;
        CellValue cells[] = game.getGameBoard().getCells();
        switch (level) {
            case EASY:
                scale=3;
                System.out.println("easy----------------->");
                position = getMove(cells,scale);
                break;
            case MEDIUM:
                scale=6;
                position = getMove(cells,scale);
                break;

            case HARD:
                scale=9;
                position = getMove(cells,scale);
                break;
        }
        game.tick(aiShape, position);
    }
    
    /*public boolean winnerExist(CellValue[] cells)
    {
        CellValue mark=CellValue.x ;
        return ((cells[0] == mark && cells[1] == mark && cells[2] == mark) || //Across Top
                (cells[3] == mark && cells[4] == mark && cells[5] == mark) || //Across Mid
                (cells[6] == mark && cells[7] == mark && cells[8] == mark) || //Across Bottom
                (cells[0] == mark && cells[3] == mark && cells[6] == mark) || //Down Left
                (cells[1] == mark && cells[4] == mark && cells[7] == mark) || //Down Mid
                (cells[2] == mark && cells[5] == mark && cells[8] == mark) || //Down Right
                (cells[0] == mark && cells[4] == mark && cells[8] == mark) || //Diag TL -> BR
                (cells[2] == mark && cells[4] == mark && cells[6] == mark)    //Diag TR -> BL
                );
    }*/

    public int getMove(CellValue[] cells,int scale)
    {
        CellValue[] temp_cells = new CellValue[9];
        for(int i=0;i<9;i++)
        {
            temp_cells[i]=cells[i];
            //System.out.println(temp_cells[i]);
        }
        /*for(int i=0;i<9;i++)
        {
            System.out.println(temp_cells[i]);
        }*/
        int move=0;
        
        int[] corners = new int[] {0, 2, 6, 8};
        int[] sides = new int[] {1, 3, 5, 7};
        
        for(move=0; move<scale; move++)
        {
            if(temp_cells[move]==CellValue._)
            {
                if(isWinnerWith(aiShape ,move,cells))
                {
                    return move;
                }
            }
        }
        //check if player can win
        for(move=0; move<scale; move++)
        {
            if(temp_cells[move]==CellValue._)
            {
                 if(isWinnerWith(CellValue.x ,move,cells))
                {
                    return move;
                }
            }
        }
        
        //Test if any corners are open (in general they are better than center or sides)
        /*for(int i=0;i<9;i++)
        {
            temp_cells[i]=cells[i];
            System.out.println(temp_cells[i]);
        }*/
        move = randomFromArray(corners,temp_cells); //Pick a random corner
        if(move != -1)
        {
            return move;
        }
        
        if(cells[4]==CellValue._)
        {
            move = 4; //I know I could just return 4, but I'd rather use move
            return move;
        }
        
        move = randomFromArray(sides,temp_cells); //Pick a random side
        if(move != -1)
        {
            return move;
        }
        return -1;
    }
    
    public static boolean isWinnerWith(CellValue mark,int slot,CellValue[] cells)
    {
        CellValue[] temp_cells = new CellValue[9];
        for(int i=0;i<9;i++)
        {
            temp_cells[i]=cells[i];
        }
        temp_cells[slot]=mark;
        
        boolean win = isWinner(mark,temp_cells);
        System.out.println("win"+win);
        return win;
    }
    
    public static boolean isWinner(CellValue mark, CellValue[] temp_cells)
    {
	//check if this move let my the winner
        //System.out.println("mark"+mark);
        return ((temp_cells[0] == mark && temp_cells[1] == mark && temp_cells[2] == mark) || //Across Top
                (temp_cells[3] == mark && temp_cells[4] == mark && temp_cells[5] == mark) || //Across Mid
                (temp_cells[6] == mark && temp_cells[7] == mark && temp_cells[8] == mark) || //Across Bottom
                (temp_cells[0] == mark && temp_cells[3] == mark && temp_cells[6] == mark) || //Down Left
                (temp_cells[1] == mark && temp_cells[4] == mark && temp_cells[7] == mark) || //Down Mid
                (temp_cells[2] == mark && temp_cells[5] == mark && temp_cells[8] == mark) || //Down Right
                (temp_cells[0] == mark && temp_cells[4] == mark && temp_cells[8] == mark) || //Diag TL -> BR
                (temp_cells[2] == mark && temp_cells[4] == mark && temp_cells[6] == mark)    //Diag TR -> BL
                );
    }
    
    
    
    private int randomFromArray(int[] list,CellValue[] temp_cells)
    {   
        int[] okMoves = new int[4]; //List of moves that are not already filled
        int move = -1;
        Random choose = new Random(); //Create a new random number generator

        //Run through each member of the list and test it to see if it is usable.
        //If it is, put it into the list of ok moves. If it is not, put -1 in its place (NOK)
        for(int i=0; i<4; i++)
        {
            //Test if it's free
            if(isFree(list[i],temp_cells))
            {
                //It is, put it in the ok list
                okMoves[i] = list[i];
            }
            //It is not, put -1 in its place
            else
                okMoves[i] = -1;
        }

        //Brute force check to make sure there is at least one valid value
        if(okMoves[0] == -1 && okMoves[1] == -1 && okMoves[2] == -1 && okMoves[3] == -1)
        {
            return -1; //If there isn't, return a bad status
        //If there is at least one valid value
        }
        else 
        {
            //Brute force until we get a choice that's not -1.
            do
            {
                move = choose.nextInt(okMoves.length);
                System.out.println("which corner?"+move);
            } 
            while(okMoves[move] == -1);
            return okMoves[move]; //Return the valid move
        }
    }
    
    
        
        
        
        
    public static boolean isFree(int move, CellValue[] temp_cells)
    {
        //CellValue[] temp_cells = new CellValue[9];
        //If the space is empty, return true
        if(temp_cells[move]==CellValue._)
        {
            System.out.println("free");
            return true;
        }
        else
        {
            System.out.println("not free");
            return false;
        }
    }
//    enum cells { x, o , none}
//    aishape 
    /*public int playEasy(CellValue[] cells) {
        int position = 0;
        ArrayList<Integer> availableMoves = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            if (cells[i] == CellValue.none) {
                availableMoves.add(i);
//                System.out.print(i);
            }
        }

        int numAvailableMoves = availableMoves.size();
        Random rand = new Random();

        position = rand.nextInt(numAvailableMoves );
        
        for(int i=0;i<9;i++)
            System.out.println(cells[i]);

        //System.err.println(position);
        // insert ai logic for easy 
        return availableMoves.get(position);
    }

    public int playMedium(CellValue[] cells) {
        int position = 0;
        // insert ai logic for medium 
        return position;
    }

    public int playHard(CellValue[] cells) {
        int position = 0;
        // insert ai logic for hard 
        
        for(int i=0;i<9;i++)
            System.out.println(cells[i]);
        
        return position;
    }*/
}