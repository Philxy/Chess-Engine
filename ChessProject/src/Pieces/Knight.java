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

    @Override
    public int mobility() {
        int W[][] = { {-50,-40,-30,-30,-30,-30,-40,-50},
                {-40,-20,  0,  0,  0,  0,-20,-40},
                {-30,  0, 10, 15, 15, 10,  0,-30},
                { -30,  5, 15, 20, 20, 15,  5,-30},
                { -30,  0, 15, 20, 20, 15,  0,-30},
                {-30,  5, 10, 15, 15, 10,  5,-30},
                { -40,-20,  0,  5,  5,  0,-20,-40},
                { -50,-40,-30,-30,-30,-30,-40,-50}};

        int B[][] = {
                { -50,-40,-30,-30,-30,-30,-40,-50},
                { -40,-20,  0,  5,  5,  0,-20,-40},
                {-30,  5, 10, 15, 15, 10,  5,-30},
                { -30,  0, 15, 20, 20, 15,  0,-30},
                { -30,  5, 15, 20, 20, 15,  5,-30},
                {-30,  0, 10, 15, 15, 10,  0,-30},
                {-40,-20,  0,  0,  0,  0,-20,-40},
                {-50,-40,-30,-30,-30,-30,-40,-50}
        };
        if(color == 'w') {
            return W[this.getPos()[0]][this.getPos()[1]];
        } else {
            return B[this.getPos()[0]][this.getPos()[1]];
        }
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
