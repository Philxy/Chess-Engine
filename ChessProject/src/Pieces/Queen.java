package Pieces;

import Game.GameState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Queen extends Piece{

    private final int value = 10;
    private List<Integer[]> legalMoves = new ArrayList<Integer[]>();


    public Queen(char color) {
        super(color);
    }

    public Queen(int[] pos, char color) {
        super(pos, color);
    }

    public boolean moveLegal(GameState gs, int[] startSq, int[] endSq) {
        // check occupation of destination
        if(gs.getSq(endSq[0], endSq[1]) != null) {
            if(gs.getSq(endSq[0], endSq[1]).getColor() == color) {
                return false;
            }
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
        if(jumpedStraight(gs, startSq, endSq) || jumpedDiagonal(gs, startSq, endSq)) {
            return false;
        }
        return true;
    }
    public String toString() {
        return "Q" ;
    }

    public int getValue() {
        if(color == 'w') {
            return value;
        } else {
            return value*(-1);
        }
    }

}
