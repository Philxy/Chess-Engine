package AI;

import Game.GameState;
import Game.Move;
import Pieces.Piece;

import java.util.*;

public class Calculation {

    private GameState currGS;
    private List<Piece> piecesOfCurrColor = new ArrayList<>();
    private List<Piece> allPiecesOnBoard = new ArrayList<>();
    private List<Move> movesOfCurrColor = new ArrayList<>();
    Node<Move> moveTree = new Node<Move>(null);

    public Calculation(GameState gs) {
        this.currGS = gs;
        initCurrPieces();
    }


    /**
     * This method finds a random piece in the board and returns one of its legal moves randomly.
     * @return
     */
    public int[][] findRandomMove() {
        int randomPieceIndex = (new Random()).nextInt(piecesOfCurrColor.size());
        Piece randPiece = piecesOfCurrColor.get(randomPieceIndex);
        while(randPiece.legalMoves(currGS).size() == 0) {
            randPiece = piecesOfCurrColor.get((new Random()).nextInt(piecesOfCurrColor.size()));
        }
        int randomMoveIndex =  (new Random()).nextInt(randPiece.legalMoves(currGS).size());
        Integer[] randomMove = randPiece.legalMoves(currGS).get(randomMoveIndex);
        int[] randMove = {randomMove[0], randomMove[1]};
        Integer[] temp = {randPiece.getPos()[0], randPiece.getPos()[1]};
        int[][] result = new int[][] {randPiece.getPos(), randMove};
        return result;
    }

    /**
     * Returns the best move after evaluating all possible positions to a certain depth
     * @return
     */
    public Move findBestMove() throws Exception{
        if(moveTree == null) {
            System.out.println("move tree empty");
        }
        List<Move> leafes = new ArrayList<>();
        for(Node<Move> node: getTreeLeaves(this.moveTree)) {
            leafes.add(node.getMove());
        }

        System.out.println(getTreeLeaves(this.moveTree).size());

        List<GameState> leafStates = new ArrayList<>();
        List<Integer> evaluations = new ArrayList<>();
        for(int i = 0; i< leafes.size();i++) {
            GameState exGS = leafes.get(i).getExecutedState();
            leafStates.add(exGS);
            evaluations.add(evaluateBoard(exGS));
        }
        int index;
        if(currGS.blackNextMove()) {
            index = evaluations.indexOf(findMin(evaluations));
        } else {
            index = evaluations.indexOf(findMax(evaluations));
        }
        return leafes.get(index);


       /*
        for(Move m: leafes) {
            leafStates.add(m.getExecutedState());
        }
        for(GameState gs: leafStates) {
            evaluations.add(evaluateBoard(gs));
        }
        if(currGS.blackNextMove()) {
            return leafes.get(evaluations.indexOf(findMin(evaluations)));
        } else {
            return leafes.get(evaluations.indexOf(findMax(evaluations)));
        }
        */
    }
    /**
     * Recursively finds the leafes of a move tree and returns a list containing them.
     *
     */
    public static List<Node<Move>> getTreeLeaves(Node<Move> tree) {
        List<Node<Move>> leafMoves = new ArrayList<>();
        if(tree.getChildren().isEmpty()) {
            leafMoves.add(tree.getRoot());
        } else {
            for(Node<Move> node: tree.getChildren()) {
                leafMoves.addAll(getTreeLeaves(node));
            }
        }
        return leafMoves;

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
            return Integer.MAX_VALUE;
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
    public  void initMoveTree(GameState gs, int depth, int repetition) {
        if(repetition != depth) {

            for(Piece p: this.getPiecesOfCurrColor()) {
                for(Move m: p.getLegalMoves(gs)) {
                    this.getMoveTree().addChild(new Node(m));
                }
            }
        }


        return;
    }

    public Node<Move> getMoveTree() {
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
                if (gs.getBoardP()[r][c] != null) {
                    evaluation = evaluation + gs.getBoardP()[r][c].getValue();
                }
            }
        }
        return evaluation;
    }


    /**
     * Finds all the pieces of the color which is about to make a move and adds them into a array list.
     */
    private void initCurrPieces() {
        for(int r = 0; r<8;r++) {
            for(int c = 0; c<8;c++) {
                if(currGS.getBoardP()[r][c] != null ) {
                    allPiecesOnBoard.add(currGS.getBoardP()[r][c]);
                    if(currGS.getBoardP()[r][c].getColor() == currGS.getColor()) {
                        piecesOfCurrColor.add(currGS.getBoardP()[r][c]);
                    }
                }
            }
        }
    }


}
