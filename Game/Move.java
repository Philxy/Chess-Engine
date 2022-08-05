package Game;

import Pieces.Piece;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Move {

    private GameState prevGS;
    private GameState newGS;
    private int startSq;
    private int endSq;
    private Piece piece;
    private boolean movingColor;

    public Move(GameState gs, Piece piece, int endSq) {
        this.endSq = endSq;
        this.startSq = piece.getPos();
        this.piece = piece;
        this.prevGS = gs;
        this.newGS = new GameState(gs);
        this.newGS.executeMove(startSq, endSq);
        this.movingColor = gs.getColor();
    }

    // Determines weather a move is legal according to the rules of chess.
    public boolean moveLegal() {
        ArrayList<Integer> moves = piece.getPseudoLegalMoves(prevGS.getCurrBoard(), startSq);
        if (piece.getColor() != prevGS.getColor()) {
            return false;
        }
        if (!moves.contains(endSq)) {
            return false;
        }
        return !Util.isCheck(newGS.getCurrBoard(), movingColor);
    }


    public GameState getExecState() {
        return this.newGS;
    }

    // executes a move and updates the board accordingly
    public void updateMainBoard() {
        this.prevGS.executeMove(startSq, endSq);
    }
}
