package Pieces;

import Game.GameState;
import Game.Main;

import java.util.Arrays;

public class Knight extends Piece{
    private final int value = 3;
    private final String name;


    public Knight(char color, int[] pos) {
        super(color, pos);
        this.name = color + "N";
    }

    public boolean moveLegal(GameState gs, int[] startSq, int[] endSq) {

        //basic knight movement
        if( !((Math.abs(startSq[0] - endSq[0]) == 2  &&  Math.abs(startSq[1] - endSq[1])==1) || (Math.abs(startSq[1] - endSq[1]) == 2  &&  Math.abs(startSq[0] - endSq[0]) == 1)) ) {
            return false;
        }
        // check occupation
        if(gs.getBoard()[endSq[0]][endSq[1]].charAt(0) == name.charAt(0)) {
            return false;
        }
        if(Arrays.equals(startSq, endSq)) {
            return false;
        }

        return true;
    }

    public int getValue() {
        if(color == 'w') {
            return this.value;
        } else  {
            return this.value*(-1);
        }
    }
}
