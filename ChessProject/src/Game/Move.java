package Game;

import Pieces.*;

import java.util.Arrays;

public class Move {


    private GameState gs;
    private int[] startSq;
    private int[] endSq;
    private Piece piece;
    private GameState execState;

    public Move(GameState gs, int[] startSq, int[] endSq) {
        this.gs = gs;
        this.startSq = startSq;
        this.endSq = endSq;
        this.piece = gs.getSq(startSq[0], startSq[1]);
    }


    /**
     * Returns the correctness of a move and initializes the executed board
     */
    public boolean legalMove() {

        // move order
        if (piece.getColor() == 'w' && gs.blackNextMove()) {
            return false;
        } else if (piece.getColor() == 'b' && !gs.blackNextMove()) {
            return false;
        }
        //prevent illegal movement according to piece rule
        if (!piece.moveLegal(gs, startSq, endSq)) {
            return false;
        }

        //execute the move on a new board
        Board execBoard = new Board(deepCopyBoard(gs.getBoard().getBoard()));
        this.execState = new GameState(execBoard, !gs.blackNextMove());
        execState.makeMove(startSq, endSq);

        // handle checks
        if (Util.isCheck1(execState, 'b') && gs.getColor() == 'b') {
            return false;
        }
        if (Util.isCheck1(execState, 'w') && gs.getColor() == 'w') {
            return false;
        }

        // replace pawn with queen in case a pawn reached the back ranks
        if (piece instanceof Pawn && (endSq[0] == 0 || endSq[0] == 7)) {
            execState.getBoard().setSq(endSq, new Queen(endSq, gs.getColor()));
        }

        return true;
    }

    public String toString() {
        return piece.toString() + Util.sqName(startSq);
    }


    /**
     * Returns a deep copy of a given board consisting of a 2 dim array of pieces
     */
    public static Piece[][] deepCopyBoard(Piece[][] board) {
        Piece[][] newBoard = new Piece[8][8];
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (board[r][c] != null) {
                    char color = board[r][c].getColor();
                    Piece p = board[r][c];
                    if (p == null) {
                        newBoard[r][c] = null;
                    } else if (p instanceof Rook) {
                        newBoard[r][c] = new Rook(new int[]{r, c}, color);
                    } else if (p instanceof Queen) {
                        newBoard[r][c] = new Queen(new int[]{r, c}, color);
                    } else if (p instanceof King) {
                        newBoard[r][c] = new King(new int[]{r, c}, color);
                    } else if (p instanceof Pawn) {
                        newBoard[r][c] = new Pawn(new int[]{r, c}, color);
                    } else if (p instanceof Bishop) {
                        newBoard[r][c] = new Bishop(new int[]{r, c}, color);
                    } else if (p instanceof Knight) {
                        newBoard[r][c] = new Knight(new int[]{r, c}, color);
                    }
                }
            }
        }
        return newBoard;
    }


    /**
     * Returns the executed game state after a move has been executed.
     *
     * @return
     */
    public GameState getExecState() {
        return this.execState;
    }


    /**
     * The board of the main state will be changed to the executed board with the executed move.
     */
    public void updateBoard() {
        gs.setBoard(execState.getBoard());
        gs.blackNextMove(!gs.blackNextMove());
        gs.setAllPieces(execState.getAllPieces());
    }

    public GameState getExecutedState() {
        return this.execState;
    }


    /**
     * Returns true and castles on the executed board if
     * ... square between king and rook aren't occupied
     * ...
     *
     * @return
     */
    private boolean castling() {
        Board execBoard = new Board(deepCopyBoard(gs.getBoard().getBoard()));
        if (gs.blackNextMove()) {

            if (Arrays.equals(startSq, new int[]{0, 4}) && Arrays.equals(endSq, new int[]{0, 7}) && execBoard.getSq(0, 5) == null && execBoard.getSq(0, 6) == null) {

                System.out.println("bS");
                execBoard.setSq(new int[]{0, 6}, new King(new int[]{0, 6}, 'b'));
                execBoard.setSq(new int[]{0, 7}, null);
                execBoard.setSq(new int[]{0, 5}, new Rook(new int[]{0, 5}, 'b'));
                execBoard.setSq(new int[]{0, 4}, null);
                this.execState = new GameState(execBoard);
                return true;

            } else if (Arrays.equals(startSq, new int[]{0, 4}) && Arrays.equals(endSq, new int[]{0, 0}) && execBoard.getSq(0, 1) == null && execBoard.getSq(0, 2) == null && execBoard.getSq(0, 3) == null) {

                System.out.println("bL");
                execBoard.setSq(new int[]{0, 2}, new King(new int[]{0, 2}, 'b'));
                execBoard.setSq(new int[]{0, 0}, null);
                execBoard.setSq(new int[]{0, 3}, new Rook(new int[]{0, 3}, 'b'));
                execBoard.setSq(new int[]{0, 4}, null);
                this.execState = new GameState(execBoard);
                return true;

            }
        } else {


            if (Arrays.equals(startSq, new int[]{7, 4}) && Arrays.equals(endSq, new int[]{7, 0}) && execBoard.getSq(7, 1) == null && execBoard.getSq(7, 2) == null && execBoard.getSq(7, 3) == null) {

                System.out.println("wL");
                execBoard.setSq(new int[]{7, 2}, new King(new int[]{7, 2}, 'w'));
                execBoard.setSq(new int[]{7, 0}, null);
                execBoard.setSq(new int[]{7, 3}, new Rook(new int[]{7, 3}, 'w'));
                execBoard.setSq(new int[]{7, 4}, null);
                this.execState = new GameState(execBoard);
                return true;

            } else if (Arrays.equals(startSq, new int[]{7, 4}) && Arrays.equals(endSq, new int[]{7, 7}) && execBoard.getSq(7, 5) == null && execBoard.getSq(7, 6) == null) {

                System.out.println("wS");
                execBoard.setSq(new int[]{7, 6}, new King(new int[]{7, 6}, 'w'));
                execBoard.setSq(new int[]{7, 7}, null);
                execBoard.setSq(new int[]{7, 5}, new Rook(new int[]{7, 5}, 'w'));
                execBoard.setSq(new int[]{7, 4}, null);
                this.execState = new GameState(execBoard);
                return true;

            }
        }
        return false;
    }
}
