package Pieces;

import Game.Board;
import Game.GameState;
import Game.Util;

import java.util.ArrayList;
import java.util.Arrays;

public class Rook extends Piece {

    private int[] offset = new int[]{-10, -1, 1, 10, 0, 0, 0, 0};

    public Rook(boolean isWhite) {
        super(isWhite);
    }

    public Rook(boolean isWhite, int square) {
        super(isWhite, square);
    }

    @Override
    public String toString() {
        if (this.isWhite) {
            return "wR";
        } else {
            return "bR";
        }

    }

    @Override
    public double mobility() {
        return 0;
    }



    @Override
    public int getValue() {
        if(isWhite) {
            return 5;
        } else {
            return -5;
        }
    }

    @Override
    public ArrayList<Integer> getPseudoLegalMoves(Board board, int startSq) {
        return Util.horizPseudoLegal(board, startSq);
    }


}
