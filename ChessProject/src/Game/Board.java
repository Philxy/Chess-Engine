package Game;

import Pieces.*;

public class Board {


    private Piece[][] board;
    private String[][] boardS = new String[8][8];

    /**
     * Default constructor creating a board with the default setup
     */
    public Board() {
        Piece[][] board = {
                {new Rook('b'), new Knight('b'), new Bishop('b'), new Queen('b'), new King('b'), new Bishop('b'), new Knight('b'), new Rook('b')},
                {new Pawn('b'), new Pawn('b'), new Pawn('b'), new Pawn('b'), new Pawn('b'), new Pawn('b'), new Pawn('b'), new Pawn('b')},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {new Pawn('w'), new Pawn('w'), new Pawn('w'), new Pawn('w'), new Pawn('w'), new Pawn('w'), new Pawn('w'), new Pawn('w')},
                {new Rook('w'), new Knight('w'), new Bishop('w'), new Queen('w'), new King('w'), new Bishop('w'), new Knight('w'), new Rook('w')}
        };
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (board[r][c] != null) {
                    board[r][c].setPos(new int[]{r, c});
                }
            }
        }
        this.board = board;
    }


    /**
     * Constructor for an arbitrary position.
     */
    public Board(Piece[][] board) {
        this.board = board;
    }


    /**
     * Initializes a 2dim string board containing the name of all pieces at the corresponding position
     */
    public void initStringPieces() {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (getSq(r, c) != null) {
                    char color = board[r][c].getColor();
                    Piece p = board[r][c];
                    if (p == null) {
                        boardS[r][c] = "--";
                    } else if (p instanceof Rook) {
                        boardS[r][c] = color + "R";
                    } else if (p instanceof Queen) {
                        boardS[r][c] = color + "Q";
                    } else if (p instanceof King) {
                        boardS[r][c] = color + "K";
                    } else if (p instanceof Pawn) {
                        boardS[r][c] = color + "P";
                    } else if (p instanceof Bishop) {
                        boardS[r][c] = color + "B";
                    } else if (p instanceof Knight) {
                        boardS[r][c] = color + "N";
                    }
                }
            }
        }
    }

    public void setBoard(Piece[][] board) {
        this.board = board;
    }

    public Piece getSq(int row, int col) {
        return board[row][col];
    }

    public void setSq(int[] square, Piece piece) {
        this.board[square[0]][square[1]] = piece;
    }


    public Piece[][] getBoard() {
        return this.board;
    }

    public String[][] getBoardS() {
        initStringPieces();
        return this.boardS;
    }

}
