package Pieces;


import Game.Board;

import java.util.ArrayList;

public abstract class Piece {
    
    protected boolean isWhite;
    private int square;

    public Piece(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public Piece(boolean isWhite, int square) {
        this.isWhite = isWhite;
        this.square = square;
    }

    public abstract String toString();

    public abstract double mobility();

    public abstract int getValue();

    public abstract ArrayList<Integer> getPseudoLegalMoves(Board board, int startSq);

    public void setPos(int square) {
        this.square = square;
    }

    public int getPos() {
        return this.square;
    }

    public boolean getColor() {
        return this.isWhite;
    }


}


