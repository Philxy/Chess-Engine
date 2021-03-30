package Pieces;

import Game.GameState;
import Game.Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Rook extends Piece{

    private final int value = 5;
    private final String name;
    private List<Integer[]> legalMoves = new ArrayList<Integer[]>();

    public Rook(char color, int[] pos) {
        super(color, pos);
        this.name = color + "R";
    }


    public boolean moveLegal(GameState gs, int[] startSq, int[] endSq ) {

        if(Arrays.equals(startSq, endSq)) {
            return false;
        }
        // check occupation of destination
        if(gs.getBoard()[endSq[0]][endSq[1]].charAt(0) == name.charAt(0)) {
            return false;
        }
        // basic rook movement
        if( !(startSq[0] == endSq[0] || startSq[1] == endSq[1]) ) {
            return false;
        }
        if(jumped(gs, startSq, endSq)) {
            return  false;
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
