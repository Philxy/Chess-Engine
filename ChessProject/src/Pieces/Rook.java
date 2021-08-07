package Pieces;

import Game.GameState;

import java.util.Arrays;

public class Rook extends Piece{

    private final int value = 5;
    private boolean moved = false;

    public Rook(char color) {
        super(color);
    }

    public Rook(int[] pos, char color) {
        super(pos, color);
    }


    public int mobility() {
        int W[][] = {
                {0,  0,  0,  0,  0,  0,  0,  0},
                {5, 10, 10, 10, 10, 10, 10,  5},
                {-5,  0,  0,  0,  0,  0,  0, -5},
                {-5,  0,  0,  0,  0,  0,  0, -5},
                {-5,  0,  0,  0,  0,  0,  0, -5},
                {-5,  0,  0,  0,  0,  0,  0, -5},
                {-5,  0,  0,  0,  0,  0,  0, -5},
                {0,  0,  0,  5,  5,  0,  0,  0}};

        int B[][] = {
                {0,  0,  0,  5,  5,  0,  0,  0},
                {-5,  0,  0,  0,  0,  0,  0, -5},
                {-5,  0,  0,  0,  0,  0,  0, -5},
                {-5,  0,  0,  0,  0,  0,  0, -5},
                {-5,  0,  0,  0,  0,  0,  0, -5},
                {-5,  0,  0,  0,  0,  0,  0, -5},
                {5, 10, 10, 10, 10, 10, 10,  5},
                {0,  0,  0,  0,  0,  0,  0,  0}};

        if(color == 'w') {
            return W[this.getPos()[0]][this.getPos()[1]];
        } else {
            return B[this.getPos()[0]][this.getPos()[1]];
        }
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
