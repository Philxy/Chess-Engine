package Pieces;

import Game.GameState;

import java.util.Arrays;

public class Bishop extends Piece{
   private final int value = 3;

    public Bishop(char color) {
        super(color);
    }

    public Bishop(int[] pos, char color) {
        super(pos, color);
    }





    public boolean moveLegal(GameState gs, int[] startSq, int[] endSq) {
        // check occupation of destination
        if(gs.getSq(endSq[0], endSq[1]) != null) {
            if(gs.getSq(endSq[0], endSq[1]).getColor() == color) {
                return false;
            }
        }
        //basic diagonal movement
        if( Math.abs(startSq[1] - endSq[1]) != Math.abs(startSq[0] - endSq[0])) {
            return  false;
        }
        if(Arrays.equals(startSq, endSq)) {
            return false;
        }

        if(jumpedDiagonal(gs, startSq, endSq)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "B";
    }

    public int getValue() {
        if(color == 'w') {
            return value;
        } else {
            return value*(-1);
        }
    }
}
