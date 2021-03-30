package Game;


import Pieces.Piece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameState {


    private Board currentBoard;
    private boolean blackHasNextMove = false;
    private static List<GameState> positions = new ArrayList<GameState>();
    private static List<Move> legalMovesAllPieces = new ArrayList<Move>();
    private static List<Move> legalMovesCurrPieces = new ArrayList<Move>();


    public GameState(Board board) {
        this.currentBoard = board;
        initLegalMoves();
    }

    private void initLegalMoves() {
        for(int r = 0; r<8; r++) {
            for(int c = 0; c<8; c++) {

                if(this.getBoardP()[r][c] != null ) {
                    for(int R = 0; R<8; R++) {
                        for(int C = 0; C<8; C++) {
                            Move move = new Move(this, new int[]{r,c}, new int[]{R,C});
                            if(move.legalMove()) {
                                legalMovesAllPieces.add(move);
                                if(this.getColor() == this.getBoardP()[r][c].getColor()) {
                                    legalMovesCurrPieces.add(move);
                                }
                            }
                        }
                    }
                }

            }
        }
    }

    /**
     * Returns weather a move is legal considering the current game state.
     * @param move
     * @return
     */
    public boolean isMoveLegal(Move move) {
        for(Move m: legalMovesCurrPieces) {
            if(move.getLocations()[0][0] == m.getLocations()[0][0] && move.getLocations()[0][1] == m.getLocations()[0][1]
            && move.getLocations()[1][1] == m.getLocations()[1][1] && move.getLocations()[1][0] == m.getLocations()[1][0]) {
                return true;
            }
        }
        return false;
    }

    public GameState(Board board, boolean blackNextMove) {
        this.currentBoard = board;
        this.blackHasNextMove = blackNextMove;
        this.positions.add(this);
    }


    /**
     * Given a game state and a move this method will return a new game state with the executed move. Move must be legal!
     * @param gs
     * @param move
     * @return
     */
    public static GameState execMove(GameState gs, Move move) {
        return null;
    }


    /**
     * Get the all the positions leading up to a game state
     * @return
     */
    public List<GameState> getPositions() {
        return this.positions;
    }

    /**
     * Add a new position to the previous game states
     * @param newState
     */
    public  void updatePositions(GameState newState) {
        this.positions.add(newState);
    }


    public static boolean isMate(GameState gs) {

        for(int r = 0; r<8; r++) {
            for(int c = 0; c<8; c++) {
                if(gs.getBoard()[r][c] != "--" && gs.getBoardP()[r][c].legalMoves(gs).size() != 0) {
                    return false;
                }
            }
        }
        if (Piece.isCheck(gs, gs.getColor())) {
            System.out.println("CHECKMATE");
        } else {
            System.out.println("STALEMATE");
        }
        return true;
    }


    public Board getBoardObject() {
        return this.currentBoard;
    }

    public  void setBoard(String [][] newBoard) {
        currentBoard = new Board(newBoard);

    }

    public void blackNextMove(boolean blackHasNextMove) {
        this.blackHasNextMove = blackHasNextMove;
    }

    public boolean blackNextMove() {
        return blackHasNextMove;
    }

    public char getAttacker() {
        if(blackHasNextMove) {
            return 'w';
        } else  {
            return 'b';
        }
    }


    public char getColor(){
        if(blackHasNextMove) {
            return 'b';
        } else  {
            return 'w';
        }
    }

    public  String[][] getBoard() {
        String[][] returnBoard = currentBoard.getBoard();
        return returnBoard;
    }

    public Piece[][] getBoardP() {
        return currentBoard.getBoardP();
    }
}
