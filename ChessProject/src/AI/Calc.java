package AI;

import Game.GameState;
import Game.Move;
import Game.Util;
import Pieces.Piece;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Calc {




    /**
     * This method finds a random piece in the board and returns one of its legal moves randomly.
     * @return
     */
    public static Move findRandomMove(GameState currGS) {
        List<Piece> piecesOfCurrColor = initCurrPieces(currGS);
        int randomPieceIndex = (new Random()).nextInt(piecesOfCurrColor.size());
        Piece randPiece = piecesOfCurrColor.get(randomPieceIndex);
        while(randPiece.getLegalMoves(currGS).size() == 0) {
            randPiece = piecesOfCurrColor.get((new Random()).nextInt(piecesOfCurrColor.size()));
        }
        int randomMoveIndex =  (new Random()).nextInt(randPiece.getLegalMoves(currGS).size());
        Move result = randPiece.getLegalMoves(currGS).get(randomMoveIndex);
        return result;
    }

    /**
     * Returns the maximum of a list consisting of Integers
     * @param list
     * @return
     */
    public static Integer findMax(List<Integer> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        List<Integer> sortedlist = new ArrayList<>(list);
        Collections.sort(sortedlist);
        return sortedlist.get(sortedlist.size() - 1);
    }

    /**
     * Finds the minimum of an Integer list.
     * @param list
     * @return
     */
    public static Integer findMin(List<Integer> list)
    {
        if (list == null || list.size() == 0) {
            return null;
        }
        List<Integer> sortedlist = new ArrayList<>(list);
        Collections.sort(sortedlist);
        return sortedlist.get(0);
    }

    /**
     * Finds all the pieces of the color which is about to make a move and adds them into a array list.
     */
    private static List<Piece> initCurrPieces(GameState currGS) {
        List<Piece> piecesOfCurrColor = new LinkedList<>();
        for(Piece p: currGS.getAllPieces()) {
            if(p.getColor() == currGS.getColor()) {
                piecesOfCurrColor.add(p);
            }
        }
        return piecesOfCurrColor;
    }


}
