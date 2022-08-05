package Game;

import Pieces.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Board {

    private int[] board;
    private ArrayList<Piece> blackPieces = new ArrayList<>();
    private ArrayList<Piece> whitePieces = new ArrayList<>();
    private int whiteKingPos;
    private int blackKingPos;

    // Translate squares on the board to an index of the int[] board representation
    private final static int[] mailbox = new int[]{
            26, 27, 28, 29, 30, 31, 32, 33,
            38, 39, 40, 41, 42, 43, 44, 45,
            50, 51, 52, 53, 54, 55, 56, 57,
            62, 63, 64, 65, 66, 67, 68, 69,
            74, 75, 76, 77, 78, 79, 80, 81,
            86, 87, 88, 89, 90, 91, 92, 93,
            98, 99, 100, 101, 102, 103, 104, 105,
            110, 111, 112, 113, 114, 115, 116, 117
    };


    // Construct board with default setup
    public Board() {
        this.board = new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10,
                10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10,
                10, 10, -5, -3, -4, -6, -2, -4, -3, -5, 10, 10,
                10, 10, -1, -1, -1, -1, -1, -1, -1, -1, 10, 10,
                10, 10, 0, 0, 0, 0, 0, 0, 0, 0, 10, 10,
                10, 10, 0, 0, 0, 0, 0, 0, 0, 0, 10, 10,
                10, 10, 0, 0, 0, 0, 0, 0, 0, 0, 10, 10,
                10, 10, 0, 0, 0, 0, 0, 0, 0, 0, 10, 10,
                10, 10, 1, 1, 1, 1, 1, 1, 1, 1, 10, 10,
                10, 10, 5, 3, 4, 6, 2, 4, 3, 5, 10, 10,
                10, 10, 10, 10, 10, 10, 10, 10, 10, 10,
                10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
        whiteKingPos = 60;
        blackKingPos = 4;
    }

    // Deep Copy Board
    public static Board copyBoard(Board oldBoard) {
        Board newBoard = new Board();
        newBoard.board = Arrays.copyOf(oldBoard.board, 144);
        newBoard.whitePieces.clear();
        newBoard.whitePieces.addAll(oldBoard.whitePieces);
        newBoard.blackPieces.clear();
        newBoard.blackPieces.addAll(oldBoard.blackPieces);
        newBoard.whiteKingPos = oldBoard.whiteKingPos;
        newBoard.blackKingPos = oldBoard.blackKingPos;
        return newBoard;
    }

    /**
     * Moves a piece from one place to another within the int[] board array. Also handles queen promotions.
     *
     * @param startSq
     * @param endSq
     */
    public void move(int startSq, int endSq) {
        int startID = board[mailbox[startSq]];
        // Handle Promotions
        if (Math.abs(startID) == 1) {
            if (startID > 0 && Util.mailbox[endSq] > 25 && Util.mailbox[endSq] < 34) {
                board[mailbox[startSq]] = 0;
                board[mailbox[endSq]] = 6;
                return;
            }
            if (startID < 0 && Util.mailbox[endSq] > 109 && Util.mailbox[endSq] < 118) {
                board[mailbox[startSq]] = 0;
                board[mailbox[endSq]] = -6;
                return;
            }
        }
        // update king pos
        if (Math.abs(startID) == 2) {
            if (startID > 0) {
                whiteKingPos = endSq;
            } else {
                blackKingPos = endSq;
            }
        }
        // move on int board
        board[mailbox[startSq]] = 0;
        board[mailbox[endSq]] = startID;
    }


    public int getKingPos(boolean white) {
        if (white) {
            return whiteKingPos;
        } else {
            return blackKingPos;
        }
    }


    // Get all pieces of a certain color
    public ArrayList<Piece> getPieces(boolean white) {
        ArrayList<Piece> pieces = new ArrayList<>();
        if (white) {
            for (int i = 0; i < 64; i++) {
                if (board[mailbox[i]] > 0) {
                    pieces.add(getPiece(i));
                }
            }
        } else {
            for (int i = 0; i < 64; i++) {
                if (board[mailbox[i]] < 0) {
                    pieces.add(getPiece(i));
                }
            }
        }
        return pieces;
    }

    // Get Piece on a specific square
    public Piece getPiece(int sq) {
        int ID = board[mailbox[sq]];
        boolean isWhite = ID > 0;
        return switch (Math.abs(ID)) {
            case 1 -> new Pawn(isWhite, sq);
            case 2 -> new King(isWhite, sq);
            case 3 -> new Knight(isWhite, sq);
            case 4 -> new Bishop(isWhite, sq);
            case 5 -> new Rook(isWhite, sq);
            case 6 -> new Queen(isWhite, sq);
            default -> null;
        };
    }

    public int getID(int sq) {
        return board[mailbox[sq]];
    }

    public int[] getArrayBoard() {
        return this.board;
    }

}
