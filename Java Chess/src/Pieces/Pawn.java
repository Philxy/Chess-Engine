package Pieces;

import Game.GameState;
import Game.Main;

import java.util.Arrays;

public class Pawn extends Piece{
    private final int value = 1;
    private final String name;


    public Pawn(char color, int[] pos) {
        super(color, pos);
        this.name = color + "P";
    }




    public boolean moveLegal(GameState gs, int[] startSq, int[] endSq) {
        //check occupation by the same color
        if(gs.getBoard()[endSq[0]][endSq[1]].charAt(0) == name.charAt(0)) {
            return false;
        }
        // prevent pawn from taking directly in front of him
        if((gs.getBoard()[endSq[0]][endSq[1]] != "--" && startSq[1] == endSq[1])) {
            return false;
        }
        // basic pawn movement of 1 or 2 squares forward
        if(!(((Math.abs(endSq[0] - startSq[0])  == 1))   || (  (startSq[0] == 6 || startSq[0] == 1)   && Math.abs(endSq[0] - startSq[0]) == 2))) {
            return false;
        }
        //prevent pawn from moving backwards
        if((name.charAt(0) == 'w' && endSq[0] > startSq[0] ) || (name.charAt(0) == 'b'  && endSq[0] < startSq[0])) {
            return false;
        }
        if(jumped(gs, startSq,endSq)) {
            return false;
        }
        if(Arrays.equals(startSq, endSq)) {
            return false;
        }
        // taking pieces
        if (Math.abs(startSq[1] - endSq[1]) >= 1 && gs.getBoard()[endSq[0]][endSq[1]].equals("--")) {
            return false;
        }

        //prevent moving sideways
        if(Math.abs(startSq[1] - endSq[1]) > 1) {
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
