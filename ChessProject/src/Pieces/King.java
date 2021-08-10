package Pieces;

import Game.GameState;

import java.util.Arrays;

public class King extends Piece{


    private final int value = Integer.MAX_VALUE;
    private boolean moved  = false;



    public King(char color) {
        super(color);
    }

    public King(int[] pos, char color) {
        super(pos, color);
    }

    @Override
    public int mobility() {
        return 0;
    }

    public boolean moveLegal(GameState gs, int[] startSq, int[] endSq) {
        // check occupation of squares
        if(gs.getSq(endSq[0], endSq[1]) != null) {
            if(gs.getSq(endSq[0], endSq[1]).getColor() == color) {
                return false;
            }
        }
        // basic king movement
        if(!((Math.abs(startSq[0] - endSq[0]) <= 1 && Math.abs(startSq[1] - endSq[1]) <= 1))) {
            return false;
        }
        if(Arrays.equals(startSq, endSq)) {
            return false;
        }

        return true;
    }

    public String toString() {
        return "K";
    }

    public int getValue() {
        if(color == 'w') {
            return value;
        } else {
            return value*(-1);
        }
    }

    public boolean moved() {
        return this.moved;
    }

    public void setMoved() {
        this.moved = true;
    }
}
