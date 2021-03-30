package Game;

import Pieces.Pawn;
import Pieces.Piece;
import java.util.Arrays;

public class Move {


    private GameState gs;
    private int[] startSq;
    private int[] endSq;
    private String name;
    private Piece piece1;
    private Piece piece2;
    private GameState executedState;
    private String[][] initialBoard;
    private static boolean checkmate;
    private static boolean stalemate;
    private boolean legalMove;

    public Move(GameState gs, int[] startSquare, int[] endSquare) {
        this.gs = gs;
        this.startSq = startSquare;
        this.endSq = endSquare;
        this.name = gs.getBoard()[startSquare[0]][startSquare[1]];
        this.piece1 = gs.getBoardP()[startSquare[0]][startSquare[1]];
        this.piece2 = gs.getBoardP()[endSquare[0]][endSquare[1]];
        this.initialBoard = gs.getBoard();
        this.legalMove = false;

        String[][] execBoard = deepCopyBoard(initialBoard);
        execBoard[endSq[0]][endSq[1]] = execBoard[startSq[0]][startSq[1]];
        execBoard[startSq[0]][startSq[1]] = "--";
        this.executedState = new GameState(new Board(execBoard), !gs.blackNextMove());


        move();
    }

    /**
     * This method executes a move and verifies its correctness. The piece must move according to its rules and
     * any checks must be avoided.
     */
    private void move() {

        if(piece1 == null) {
            System.out.println("piece null");
            return;
        }


        // move order
        if(name.charAt(0) == 'w' && gs.blackNextMove() ) {
            return;
        } else if(name.charAt(0) == 'b' && !gs.blackNextMove()) {
            return;
        }
        //prevent illegal movement according to piece rule
        if( !piece1.moveLegal(gs, startSq, endSq)) {
            return;
        }





        // checks
        if(Piece.isCheck(executedState, 'b') && name.charAt(0) == 'b') {
            return;
        }
        if(Piece.isCheck(executedState, 'w') && name.charAt(0) == 'w') {
            return;
        }

        this.legalMove = true;
    }

    public boolean legalMove() {
        return legalMove;
    }



    public int[][] getLocations() {
        return new int[][]{startSq, endSq};
    }

    public static String[][] deepCopyBoard(String[][] board) {
        String[][] returnString = new String[8][8];
        for(int r = 0; r<8; r++) {
            for(int c = 0; c<8; c++) {
                returnString[r][c] =  board[r][c];
            }
        }
        return returnString;
    }

    /**
     * Returns the position of the a pawn which has entered the backrank. In case there is no pawn in the back rank null
     * will be returned.
     * @return
     */
    public int[] pawnInBackRank() {
        for(int c = 0; c<8;c++) {
            if(executedState.getBoardP()[7][c] instanceof Pawn) {
                    return new int[]{7,c};
            } else if(executedState.getBoardP()[0][c] instanceof Pawn) {
                    return new int[]{0,c};
            }
        }
        return null;
    }

    /**
     * The board of the main state will be changed to the executed board with the executed move.
     */
    public void updateBoard() {
        int[] pawnBackRankPos = pawnInBackRank();
        if(pawnBackRankPos != null) {
            String[][] boardWQueen = executedState.getBoard();
            if(pawnBackRankPos[0] == 0) {
                boardWQueen[pawnBackRankPos[0]][pawnBackRankPos[1]] = "wQ";
            } else {
                boardWQueen[pawnBackRankPos[0]][pawnBackRankPos[1]] = "bQ";
            }
        }

       gs.setBoard(executedState.getBoard());
       gs.blackNextMove(!gs.blackNextMove());
    }

    public GameState getExecutedState(){
        return this.executedState;
    }

}
