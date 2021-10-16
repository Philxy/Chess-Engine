package Pieces;

import Game.Board;
import Game.GameState;
import Game.Util;

import java.util.ArrayList;


public class King extends Piece {

    private int[] offset = new int[]{-11, -10, -9, -1, 1, 9, 10, 11};

    public King(boolean isWhite) {
        super(isWhite);
    }

    public King(boolean isWhite, int square) {
        super(isWhite, square);
    }

    @Override
    public String toString() {
        if (this.isWhite) {
            return "wK";
        } else {
            return "bK";
        }

    }

    @Override
    public double mobility() {
        return 0;
    }


    @Override
    public int getValue() {
        if(isWhite) {
            return 999999;
        } else {
            return -999999;
        }
    }

    @Override
    public ArrayList<Integer> getPseudoLegalMoves(Board board, int startSq) {
        return Util.kingPseudoLegal(board, startSq);
    }


}
