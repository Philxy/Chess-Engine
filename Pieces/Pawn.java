package Pieces;

import Game.Board;
import Game.GameState;
import Game.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Pawn extends Piece {


    public Pawn(boolean isWhite) {
        super(isWhite);
    }

    public Pawn(boolean isWhite, int square) {
        super(isWhite, square);
    }

    @Override
    public String toString() {
        if (this.isWhite) {
            return "wP";
        } else {
            return "bP";
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
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public ArrayList<Integer> getPseudoLegalMoves(Board board, int startSq) {
        ArrayList<Integer> list = new ArrayList<>();
        int[] arrayBoard = board.getArrayBoard();
        int startSqBoard = Util.mailbox[startSq];
        if (this.isWhite) {
            if (Util.mailbox[startSq] - 12 > 26) {
                if (arrayBoard[startSqBoard - 12] == 0) {
                    list.add(Util.mailToBoard[startSqBoard - 12]);
                }
                if (startSqBoard > 97 && startSqBoard < 106 && arrayBoard[startSqBoard - 24] == 0 && arrayBoard[startSqBoard - 12] == 0) {
                    list.add(Util.mailToBoard[startSqBoard - 24]);
                }
                if (arrayBoard[startSqBoard - 13] < 0 && arrayBoard[startSqBoard - 13] != 10) {
                    list.add(Util.mailToBoard[startSqBoard - 13]);
                }
                if (arrayBoard[startSqBoard - 11] < 0 && arrayBoard[startSqBoard - 11] != 10) {
                    list.add(Util.mailToBoard[startSqBoard - 11]);
                }
            }
        } else {
            if (Util.mailbox[startSq] + 12 < 118) {
                if (arrayBoard[startSqBoard + 12] == 0) {
                    list.add(Util.mailToBoard[startSqBoard + 12]);
                }
                if (startSqBoard > 37 && startSqBoard < 46 && arrayBoard[startSqBoard + 24] == 0 && arrayBoard[startSqBoard + 12] == 0) {
                    list.add(Util.mailToBoard[startSqBoard + 24]);
                }
                if (arrayBoard[startSqBoard + 13] > 0 && arrayBoard[startSqBoard + 13] != 10) {
                    list.add(Util.mailToBoard[startSqBoard + 13]);
                }
                if (arrayBoard[startSqBoard + 11] > 0 && arrayBoard[startSqBoard + 11] != 10) {
                    list.add(Util.mailToBoard[startSqBoard + 11]);
                }
            }
        }
        return list;
    }


    final static double[] W = new double[]{
            0, 0, 0, 0, 0, 0, 0, 0,
            50, 50, 50, 50, 50, 50, 50, 50,
            10, 10, 20, 30, 30, 20, 10, 10,
            5, 5, 10, 25, 25, 10, 5, 5,
            0, 0, 0, 20, 20, 0, 0, 0,
            5, -5, -10, 0, 0, -10, -5, 5,
            5, 10, 10, -20, -20, 10, 10, 5,
            0, 0, 0, 0, 0, 0, 0, 0};

    final static double[] B = new double[]{
            0, 0, 0, 0, 0, 0, 0, 0,
            5, 10, 10, -20, -20, 10, 10, 5,
            5, -5, -10, 0, 0, -10, -5, 5,
            0, 0, 0, 20, 20, 0, 0, 0,
            5, 5, 10, 25, 25, 10, 5, 5,
            10, 10, 20, 30, 30, 20, 10, 10,
            50, 50, 50, 50, 50, 50, 50, 50,
            0, 0, 0, 0, 0, 0, 0, 0};


}
