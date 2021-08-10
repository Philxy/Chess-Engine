package AI;

import Game.GameState;
import Game.Move;
import Pieces.Piece;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Calcutations {


    public static final int depth = 4;


    /**
     * This method finds a random piece in the board and returns one of its legal moves randomly.
     *
     * @return
     */
    public static Move findRandomMove(GameState gs) {
        List<Piece> piecesOfCurrColor = initCurrPieces(gs);
        int randomPieceIndex = (new Random()).nextInt(piecesOfCurrColor.size());
        Piece randPiece = piecesOfCurrColor.get(randomPieceIndex);
        while (randPiece.getLegalMoves(gs).size() == 0) {
            randPiece = piecesOfCurrColor.get((new Random()).nextInt(piecesOfCurrColor.size()));
        }
        int randomMoveIndex = (new Random()).nextInt(randPiece.getLegalMoves(gs).size());
        Move result = randPiece.getLegalMoves(gs).get(randomMoveIndex);
        return result;
    }



    /**
     * Returns the maximum of a list consisting of Integers
     *
     * @param list
     * @return
     */
    public static int findMax(List<Integer> list) {
        if (list == null || list.size() == 0) {
            return Integer.MIN_VALUE;
        }
        List<Integer> sortedlist = new ArrayList<>(list);
        Collections.sort(sortedlist);
        return sortedlist.get(sortedlist.size() - 1);
    }

    /**
     * Finds the minimum of an Integer list.
     *
     * @param list
     * @return
     */
    public static Integer findMin(List<Integer> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        List<Integer> sortedlist = new ArrayList<>(list);
        Collections.sort(sortedlist);
        return sortedlist.get(0);
    }


    /**
     * Finds all the pieces of the color which is about to make a move and adds them into an array list.
     */
    private static List<Piece> initCurrPieces(GameState gs) {
        List<Piece> piecesOfCurrColor = new ArrayList<>();
        for (Piece p : gs.getAllPieces()) {
            if (p.getColor() == gs.getColor()) {
                piecesOfCurrColor.add(p);
            }
        }
        return piecesOfCurrColor;
    }

}
