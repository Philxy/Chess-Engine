package Pieces;

import Game.Board;
import Game.GameState;
import Game.Util;

import java.util.ArrayList;
import java.util.Arrays;

public class Knight extends Piece {

    private int[] offset = new int[]{-21, -19, -12, -8, 8, 12, 19, 21};

    public Knight(boolean isWhite) {
        super(isWhite);
    }

    public Knight(boolean isWhite, int square) {
        super(isWhite, square);
    }

    @Override
    public String toString() {
        if (this.isWhite) {
            return "wN";
        } else {
            return "bN";
        }

    }

    @Override
    public double mobility() {
        if (isWhite) {
            return W[this.getPos()];
        } else {
            return B[this.getPos()];
        }
    }


    @Override
    public int getValue() {
        if (isWhite) {
            return 3;
        } else {
            return -3;
        }
    }

    @Override
    public ArrayList<Integer> getPseudoLegalMoves(Board board, int startSq) {
        return Util.knightPseudoLegal(board, startSq);
    }


    final static double[] W = {-50, -40, -30, -30, -30, -30, -40, -50,
            -40, -20, 0, 0, 0, 0, -20, -40,
            -30, 0, 10, 15, 15, 10, 0, -30,
            -30, 5, 15, 20, 20, 15, 5, -30,
            -30, 0, 15, 20, 20, 15, 0, -30,
            -30, 5, 10, 15, 15, 10, 5, -30,
            -40, -20, 0, 5, 5, 0, -20, -40,
            -50, -40, -30, -30, -30, -30, -40, -50};

    final static double[] B = {
            -50, -40, -30, -30, -30, -30, -40, -50,
            -40, -20, 0, 5, 5, 0, -20, -40,
            -30, 5, 10, 15, 15, 10, 5, -30,
            -30, 0, 15, 20, 20, 15, 0, -30,
            -30, 5, 15, 20, 20, 15, 5, -30,
            -30, 0, 10, 15, 15, 10, 0, -30,
            -40, -20, 0, 0, 0, 0, -20, -40,
            -50, -40, -30, -30, -30, -30, -40, -50
    };

}
