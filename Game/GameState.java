package Game;

import Pieces.Piece;

import java.util.ArrayList;

public class GameState {

    private Board currentBoard;
    private boolean whiteMoving;


    public GameState() {
        this.currentBoard = new Board();
        this.whiteMoving = true;
    }

    // Copy GS
    public GameState(GameState gs) {
        this.currentBoard = Board.copyBoard(gs.getCurrBoard());
        this.whiteMoving = gs.getColor();
    }

    // Returns a list of all legal moves considering the current game state
    public ArrayList<Move> getLegalMoves() {
        ArrayList<Move> moves = new ArrayList<>();
        ArrayList<Piece> pieces = currentBoard.getPieces(whiteMoving);
        for (Piece p : pieces) {
            ArrayList<Integer> mov = p.getPseudoLegalMoves(currentBoard, p.getPos());
            for (Integer I : mov) {
                Move m = new Move(this, p, I);
                if (m.moveLegal()) {
                    moves.add(m);
                }
            }
        }
        return moves;
    }

    // Executes move and changes state of the game accordingly
    public void executeMove(int startSq, int endSq) {
        this.currentBoard.move(startSq, endSq);
        this.whiteMoving = !whiteMoving;
    }

    public boolean getColor() {
        return this.whiteMoving;
    }

    public Board getCurrBoard() {
        return this.currentBoard;
    }

}
