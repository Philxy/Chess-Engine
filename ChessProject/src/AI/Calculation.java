package AI;

import Game.GameState;
import Game.Move;
import Game.Util;
import Pieces.Piece;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Calculation {


    private final int depth = 4;
    private GameState currGS;
    private List<Piece> piecesOfCurrColor = new ArrayList<>();
    NodeTree<Move> moveTree = new NodeTree<Move>(null);

    public Calculation(GameState gs) {
        this.currGS = gs;
        initCurrPieces();
        moveTree.initializeMoveTree(currGS, depth, 0);
    }


    /**
     * This method finds a random piece in the board and returns one of its legal moves randomly.
     * @return
     */
    public Move findRandomMove() {
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
     * Returns the best move after evaluating all possible positions to a certain depth. If multiple moves are
     * leading to the same evaluation a random move will be selected.
     * @return
     */
    public Move findBestMove() throws Exception{
        if(moveTree == null) {
            System.out.println("move tree empty");
        }
        List<NodeTree<Move>> leafNodes = moveTree.getAllLeafNodes();

        List<GameState> leafStates = new ArrayList<>();
        List<Integer> evaluations = new ArrayList<>();

        for(int i = 0; i<leafNodes.size();i++) {
            leafStates.add(leafNodes.get(i).getMove().getExecutedState());
            evaluations.add(leafNodes.get(i).getMove().getExecutedState().getEval());
        }
        int result;
        List<Integer> indexes = new ArrayList<>();
        if(currGS.blackNextMove()) {
            result = findMin(evaluations);
        } else {
            result = findMax(evaluations);
        }
        for(int i = 0; i<evaluations.size();i++) {
            if(evaluations.get(i).intValue() == result) {
                indexes.add(i);
            }
        }
        int randomNum = ThreadLocalRandom.current().nextInt(0, indexes.size());
        return leafNodes.get(indexes.get(randomNum)).getRootChildOfLeaf().getMove();

    }




    /**
     * Returns the maximum of a list consisting of Integers
     * @param list
     * @return
     */
    public int findMax(List<Integer> list) {
        if (list == null || list.size() == 0) {
            return Integer.MIN_VALUE;
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
     * This function recursively initializes a tree containing all the possible positions which can be reached
     * within a certain depth.
     * @param gs
     * @param depth
     * @param repetition
     */
    public void initMoveTree(GameState gs, int depth, int repetition) {
        if(repetition != depth && this.getMoveTree().getChildren().isEmpty()) {
            for(Piece p: this.getPiecesOfCurrColor()) {
                for(Move m: p.getLegalMoves(gs)) {
                    NodeTree<Move> node = new NodeTree(m);
                    this.getMoveTree().addChild(node);
                }
            }

        }
        return;
    }

    public NodeTree<Move> getMoveTree() {
        return this.moveTree;
    }


    private List<Piece> getPiecesOfCurrColor() {
        return this.piecesOfCurrColor;
    }

    /**
     * Returns the evaluation of the pieces values in the current position
     * @param gs
     * @return
     */
    private static int evaluateBoard(GameState gs) {
        int evaluation = 0;
        for(int r = 0; r<8;r++) {
            for (int c = 0; c < 8; c++) {
                if (gs.getSq(r,c) != null) {
                    evaluation = evaluation + gs.getSq(r,c).getValue();
                }
            }
        }
        return evaluation;
    }


    /**
     * Finds all the pieces of the color which is about to make a move and adds them into a array list.
     */
    private void initCurrPieces() {
        for(Piece p: currGS.getAllPieces()) {
            if(p.getColor() == currGS.getColor()) {
                piecesOfCurrColor.add(p);
            }
        }
    }


}
