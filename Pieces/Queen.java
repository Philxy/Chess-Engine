package Pieces;

import Game.Board;
import Game.Util;
import Pieces.Piece;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Queen extends Piece {

    private int[] offset = new int[]{ -11, -10, -9, -1, 1,  9, 10, 11 };

    public Queen(boolean isWhite) {
        super(isWhite);
    }

    public Queen(boolean isWhite, int square) {
        super(isWhite, square);
    }

    @Override
    public String toString() {
        if(this.isWhite) {
            return "wQ";
        } else {
            return "bQ";
        }

    }

    @Override
    public double mobility() {
        return 0;
    }


    @Override
    public int getValue() {
        if(isWhite) {
            return 10;
        } else {
            return -10;
        }
    }

    @Override
    public ArrayList<Integer> getPseudoLegalMoves(Board board, int startSq) {
        ArrayList<Integer> moves = new ArrayList<>();
        moves.addAll(Util.diagPseudoLegal(board,startSq));
        moves.addAll(Util.horizPseudoLegal(board,startSq));
        return moves;
    }


}
