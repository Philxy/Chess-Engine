package Pieces;

import Game.GameState;

import java.util.Arrays;

public class Knight extends Piece{
    private final int value = 3;


    public Knight(char color) {
        super(color);
    }

    public Knight(int[] pos, char color) {
        super(pos, color);
    }

    public boolean moveLegal(GameState gs, int[] startSq, int[] endSq) {

        //basic knight movement
        if( !((Math.abs(startSq[0] - endSq[0]) == 2  &&  Math.abs(startSq[1] - endSq[1])==1) || (Math.abs(startSq[1] - endSq[1]) == 2  &&  Math.abs(startSq[0] - endSq[0]) == 1)) ) {
            return false;
        }
        // check occupation
        if(gs.getSq(endSq[0], endSq[1]) != null) {
            if(gs.getSq(endSq[0], endSq[1]).getColor() == color) {
                return false;
            }
        }
        if(Arrays.equals(startSq, endSq)) {
            return false;
        }

        return true;
    }
    public String toString() {
        return "N";
    }

    public int getValue() {
        if(color == 'w') {
            return value;
        } else {
            return value*(-1);
        }
    }
}
