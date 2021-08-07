package Pieces;

import Game.GameState;

import java.util.Arrays;

public class Pawn extends Piece{
    private  int value = 1;




    public Pawn(char color) {
        super(color);
    }

    public Pawn(int[] pos, char color) {
        super(pos, color);
    }

    @Override
    public int mobility() {
        int W[][] = {
                {0,  0,  0,  0,  0,  0,  0,  0},
                {50, 50, 50, 50, 50, 50, 50, 50},
                {10, 10, 20, 30, 30, 20, 10, 10},
                {5,  5, 10, 25, 25, 10,  5,  5},
                {0,  0,  0, 20, 20,  0,  0,  0},
                {5, -5,-10,  0,  0,-10, -5,  5},
                {5, 10, 10,-20,-20, 10, 10,  5},
                {0,  0,  0,  0,  0,  0,  0,  0}};

        int B[][] = {
                {0,  0,  0,  0,  0,  0,  0,  0},
                {5, 10, 10,-20,-20, 10, 10,  5},
                {5, -5,-10,  0,  0,-10, -5,  5},
                {0,  0,  0, 20, 20,  0,  0,  0},
                {5,  5, 10, 25, 25, 10,  5,  5},
                {10, 10, 20, 30, 30, 20, 10, 10},
                {50, 50, 50, 50, 50, 50, 50, 50},
                {0,  0,  0,  0,  0,  0,  0,  0}};

        if(color == 'w') {
            return W[this.getPos()[0]][this.getPos()[1]];
        } else {
            return B[this.getPos()[0]][this.getPos()[1]];
        }
    }


    public boolean moveLegal(GameState gs, int[] startSq, int[] endSq) {
        //check occupation by the same color
        if(gs.getSq(endSq[0], endSq[1]) != null) {
            if(gs.getSq(endSq[0], endSq[1]).getColor() == color) {
                return false;
            }
        }
        // prevent pawn from taking directly in front of him
        if((gs.getSq(endSq[0], endSq[1]) != null && startSq[1] == endSq[1])) {
            return false;
        }
        // basic pawn movement of 1 or 2 squares forward
        if(!(((Math.abs(endSq[0] - startSq[0])  == 1))   || (  (startSq[0] == 6 || startSq[0] == 1)   && Math.abs(endSq[0] - startSq[0]) == 2))) {
            return false;
        }
        //prevent pawn from moving backwards
        if((color == 'w' && endSq[0] > startSq[0] ) || (color == 'b'  && endSq[0] < startSq[0])) {
            return false;
        }
        if(jumpedStraight(gs, startSq,endSq)) {
            return false;
        }
        if(Arrays.equals(startSq, endSq)) {
            return false;
        }
        // taking pieces
        if (Math.abs(startSq[1] - endSq[1]) >= 1 && gs.getSq(endSq[0], endSq[1]) == null) {
            return false;
        }

        if(Math.abs(startSq[0] - endSq[0]) > 1 && Math.abs(startSq[1] - endSq[1]) >= 1) {
            return false;
        }
        //prevent moving sideways
        if(Math.abs(startSq[1] - endSq[1]) > 1) {
            return false;
        }



        return true;
    }

    public String toString() {
        return "P";
    }

    public int getValue() {
        if(color == 'w') {
            return value;
        } else {
            return value*(-1);
        }
    }

}
