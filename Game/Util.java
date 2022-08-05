package Game;

import Pieces.Piece;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

// useful utility
public class Util {

    private final static int[] diagOffset = new int[]{-11, -13, 11, 13};
    private final static int[] horizOffset = new int[]{-1, 1, 12, -12};
    private final static int[] horseOffset = new int[]{-23, -25, 23, 25, -10, 14, -14, 10};
    private final static int[] kingOffset = new int[]{-1, 1, 12, -12, 13, -13, -11, 11};

    public final static int[] mailbox = new int[]{
            26, 27, 28, 29, 30, 31, 32, 33,
            38, 39, 40, 41, 42, 43, 44, 45,
            50, 51, 52, 53, 54, 55, 56, 57,
            62, 63, 64, 65, 66, 67, 68, 69,
            74, 75, 76, 77, 78, 79, 80, 81,
            86, 87, 88, 89, 90, 91, 92, 93,
            98, 99, 100, 101, 102, 103, 104, 105,
            110, 111, 112, 113, 114, 115, 116, 117
    };

    public final static int[] mailToBoard = new int[]{
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, -1, -1, -1, -1, 8, 9, 10, 11, 12, 13, 14, 15, -1, -1, -1, -1, 16, 17, 18, 19, 20, 21, 22, 23, -1, -1, -1, -1, 24, 25, 26, 27, 28, 29, 30, 31, -1, -1, -1, -1, 32, 33, 34, 35, 36, 37, 38, 39, -1, -1, -1, -1, 40, 41, 42, 43, 44, 45, 46, 47, -1, -1, -1, -1, 48, 49, 50, 51, 52, 53, 54, 55, -1, -1, -1, -1, 56, 57, 58, 59, 60, 61, 62, 63, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1
    };


    /**
     * Returns a list of all pseudo legal moves given an offset array of a specific piece.
     *
     * @param board current position
     * @param sq initial square
     * @param offsets offsets representing the pieces movement
     * @return pseudo legal moves
     */
    public static ArrayList<Integer> pseudoLegal(Board board, int sq, int[] offsets) {
        ArrayList<Integer> moves = new ArrayList<>();
        boolean white = board.getID(sq) > 0;
        int[] arrayBoard = board.getArrayBoard();
        for (int o : offsets) {
            int sqB = mailbox[sq];         // square in board coordinates
            sqB += o;
            while (sqB > 25 && sqB < 118 && arrayBoard[sqB] != 10) {
                if (arrayBoard[sqB] == 0) {
                    moves.add(mailToBoard[sqB]);
                } else {
                    if (white) {
                        if (arrayBoard[sqB] < 0) {
                            moves.add(mailToBoard[sqB]);
                        }
                    } else {
                        if (arrayBoard[sqB] > 0) {
                            moves.add(mailToBoard[sqB]);
                        }
                    }
                    break;
                }
                sqB += o;
            }
        }
        return moves;
    }

    /**
     * Returns list of all pseudo legal knight moves.
     *
     * @param board current position
     * @param sq initial square
     * @return pseudo legal knight moves
     */
    public static ArrayList<Integer> specialPseudoLegal(Board board, int sq, int[] offset) {
        ArrayList<Integer> moves = new ArrayList<>();
        boolean white = board.getID(sq) > 0;
        int[] arrayBoard = board.getArrayBoard();
        int sqB;
        for (int o : offset) {
            sqB = mailbox[sq] + o;         // square in board coordinates
            if (sqB > 25 && sqB < 118 && arrayBoard[sqB] != 10) {
                if (arrayBoard[sqB] == 0) {
                    moves.add(mailToBoard[sqB]);
                } else {
                    if (white) {
                        if (arrayBoard[sqB] < 0) {
                            moves.add(mailToBoard[sqB]);
                        }
                    } else {
                        if (arrayBoard[sqB] > 0) {
                            moves.add(mailToBoard[sqB]);
                        }
                    }
                }
            }
        }
        return moves;
    }

    // Returns all pseudo legal horizontal moves.
    public static ArrayList<Integer> diagPseudoLegal(Board board, int sq) {
        return pseudoLegal(board, sq, diagOffset);
    }

    // Returns all pseudo legal horizontal moves.
    public static ArrayList<Integer> horizPseudoLegal(Board board, int sq) {
        return pseudoLegal(board, sq, horizOffset);
    }

    // Returns all pseudo legal king moves.
    public static ArrayList<Integer> kingPseudoLegal(Board board, int sq) {
        return specialPseudoLegal(board, sq, kingOffset);

    }

    // Returns all pseudo legal knight moves.
    public static ArrayList<Integer> knightPseudoLegal(Board board, int sq) {
        return specialPseudoLegal(board, sq, horseOffset);
    }

    // Returns all pseudo legal moves of a given board and moving color
    public static ArrayList<Integer> getAllPseudoLegalMoves(Board board, boolean white) {
        ArrayList<Integer> moves = new ArrayList<>();
        for (Piece p : board.getPieces(white)) {
            moves.addAll(p.getPseudoLegalMoves(board, p.getPos()));
        }
        return moves;
    }

    // Determines weather the king is in check
    public static boolean isCheck(Board board, boolean white) {
        int kingPos = board.getKingPos(white);
        ArrayList<Integer> legalMoves = new ArrayList<>(getAllPseudoLegalMoves(board, !white));
        return legalMoves.contains(kingPos);
    }
}
