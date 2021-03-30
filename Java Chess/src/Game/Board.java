package Game;

import Pieces.*;

import java.util.Arrays;

public class Board {


    private String[][] board;
    private Pieces.Piece[][] boardP;

    /**
     * Constructor creating an entirely new board with the default setup
     */
    public Board() {

        String[][] board = {
                {"bR", "bN", "bB", "bQ", "bK", "bB", "bN", "bR"},
                {"bP", "bP", "bP", "bP", "bP", "bP", "bP", "bP"},
                {"--", "--", "--", "--", "--", "--", "--", "--"},
                {"--", "--", "--", "--", "--", "--", "--", "--"},
                {"--", "--", "--", "--", "--", "--", "--", "--"},
                {"--", "--", "--", "--", "--", "--", "--", "--"},
                {"wP", "wP", "wP", "wP", "wP", "wP", "wP", "wP"},
                {"wR", "wN", "wB", "wQ", "wK", "wB", "wN", "wR"}
        };
        this.board = board;
        this.boardP = new Pieces.Piece[8][8];
        initPieces();
    }



    /**
     * Create a board with a specific constellation
     */
    public Board(String[][] currentBoard) {
        this.board = currentBoard;
        this.boardP = new Pieces.Piece[8][8];
        initPieces();
    }




    /**
     * Creates a board conataining Pieces according to the string board
     */
    public void initPieces() {
        for(int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {

                String s = board[r][c];
                int[] pos = {r,c};

                switch(s.charAt(1)) {
                    case 'R':
                        boardP[r][c] = new Rook(s.charAt(0), pos);
                        break;
                    case 'Q':
                        boardP[r][c] = new Queen(s.charAt(0), pos);
                        break;
                    case 'K':
                        boardP[r][c] = new King(s.charAt(0), pos);
                        break;
                    case 'P':
                        boardP[r][c] = new Pawn(s.charAt(0), pos);
                        break;
                    case 'B':
                        boardP[r][c] = new Bishop(s.charAt(0), pos);
                        break;
                    case 'N':
                        boardP[r][c] = new Knight(s.charAt(0), pos);
                        break;
                    default:
                        boardP[r][c] = null;
                }
            }
        }
    }

    public void setBoard(String[][] newBoard) {
        this.board = newBoard;
        initPieces();
    }

    /**
     * This method places a piece (with string name) to a desired position
     * @param square
     * @param pieceName
     */
    public void setSquare(int[] square, String pieceName) {
        Board copy = new Board(this.board);
        String[][] copyBoard;
        copyBoard = copy.getBoard();
        copyBoard[square[0]][square[1]] = pieceName;
        this.board = copyBoard;
        initPieces();
    }





    public String[][] getBoard() {
        return this.board;
    }

    public Piece[][] getBoardP() {
        return this.boardP;
    }

}
