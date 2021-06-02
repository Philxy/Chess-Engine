package Pieces;

import Game.GameState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Rook extends Piece{

    private final int value = 5;
    private boolean moved = false;

    public Rook(char color) {
        super(color);
    }

    public Rook(int[] pos, char color) {
        super(pos, color);
    }

    public boolean moveLegal(GameState gs, int[] startSq, int[] endSq ) {

        if(Arrays.equals(startSq, endSq)) {
            return false;
        }
        // check occupation of destination
        if(gs.getSq(endSq[0], endSq[1]) != null) {
            if(gs.getSq(endSq[0], endSq[1]).getColor() == color) {
                return false;
            }
        }


        // basic rook movement
        if( !(startSq[0] == endSq[0] || startSq[1] == endSq[1]) ) {
            return false;
        }
        if(jumpedStraight(gs, startSq, endSq)) {
            return  false;
        }
        return true;
    }

    public String toString() {
        return "R";
    }

    public int getValue() {
        if(color == 'w') {
            return value;
        } else {
            return value*(-1);
        }
    }

    public void setMoved() {
        this.moved = true;
    }

    public boolean moved() {
        return this.moved;
    }
}
