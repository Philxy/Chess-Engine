package Pieces;

import Game.GameState;
import Game.Main;

import java.util.Arrays;

public class Bishop extends Piece{
   private final int value = 3;
   private final String name;

    public Bishop(char color, int[] pos) {
        super(color, pos);
        this.name = color + "B";
    }

    public int getValue() {
        if(color == 'w') {
            return this.value;
        } else  {
            return this.value*(-1);
        }
    }

    public boolean moveLegal(GameState gs, int[] startSq, int[] endSq) {
        // check occupation of destination
        if(gs.getBoard()[endSq[0]][endSq[1]].charAt(0) == name.charAt(0)) {
            return false;
        }
        //basic diagonal movement
        if( Math.abs(startSq[1] - endSq[1]) != Math.abs(startSq[0] - endSq[0])) {
            return  false;
        }
        if(Arrays.equals(startSq, endSq)) {
            return false;
        }

        if(Piece.jumped(gs, startSq, endSq)) {
            return false;
        }
        return true;
    }
}
