package Pieces;

import Game.Board;
import Game.GameState;
import Game.Util;

import java.util.ArrayList;
import java.util.Arrays;

public class Bishop extends Piece {

    private int[] offset = new int[]{ -11,  -9,  9, 11};

    public Bishop(boolean isWhite) {
        super(isWhite);
    }

    public Bishop(boolean isWhite, int square) {
        super(isWhite, square);
    }

    @Override
    public String toString() {
        if(this.isWhite) {
            return "wB";
        } else {
            return "bB";
        }

    }

    @Override
    public double mobility() {
        return 0;
    }



    @Override
    public int getValue() {
        if(isWhite) {
            return 3;
        } else {
            return -3;
        }
    }

    @Override
    public ArrayList<Integer> getPseudoLegalMoves(Board board, int startSq) {
        return Util.diagPseudoLegal(board, startSq);
    }



}
