package Pieces;

import Game.GameState;
import Game.Main;

import java.util.Arrays;

public class King extends Piece{

    private String name;
    private final int value = 999;

    public King(char color, int[] pos) {
        super(color, pos);
        this.name = color + "K";
    }

    public int getValue() {
        if(color == 'w') {
            return this.value;
        } else  {
            return this.value*(-1);
        }
    }

    public boolean moveLegal(GameState gs, int[] startSq, int[] endSq) {
        // check occupation of squares
        if(gs.getBoard()[endSq[0]][endSq[1]].charAt(0) == name.charAt(0)) {
            return false;
        }
        // basic king movement
        if(!((Math.abs(startSq[0] - endSq[0]) <= 1 && Math.abs(startSq[1] - endSq[1]) <= 1))) {
            return false;
        }
        if(Arrays.equals(startSq, endSq)) {
            return false;
        }
        char attacker;
        if(color == 'w') {
            attacker = 'b';
        } else {
            attacker = 'w';
        }



        //prevent stepping into check

        return true;

    }





}
