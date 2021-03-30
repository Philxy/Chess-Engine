package Pieces;

import Game.GameState;
import Game.Main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Queen extends Piece{

    private final int value = 10;
    private final String name;
    private List<Integer[]> legalMoves = new ArrayList<Integer[]>();


    public Queen(char color, int[] pos) {
        super(color, pos);
        this.name = color + "Q";
    }



    public boolean moveLegal(GameState gs, int[] startSq, int[] endSq) {
        // check occupation of destination
        if(gs.getBoard()[endSq[0]][endSq[1]].charAt(0) == name.charAt(0)) {
            return false;
        }
        // basic queen movement
        boolean bishop = Math.abs(startSq[1] - endSq[1]) != Math.abs(startSq[0] - endSq[0]);
        boolean rook = startSq[0] == endSq[0] || startSq[1] == endSq[1];
        if(  (bishop && !rook) ||( !bishop && rook)) {
            return false;
        }
        if(Arrays.equals(startSq, endSq)) {
            return false;
        }
        if(jumped(gs, startSq, endSq)) {
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
