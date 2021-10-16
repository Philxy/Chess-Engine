package AI;

import Game.GameState;
import Game.Move;

import java.util.*;

// This class contains functions which are meant to find the best possible move
public class MoveGeneration {

    public static int DEPTH = 3;
    public static int nodeCount = 0;

    // Find a random legal move
    public static Move randomMove(GameState gs) {
        ArrayList<Move> moves = gs.getLegalMoves();
        return moves.get((new Random()).nextInt(moves.size()));
    }


    // Heuristically finds the best possible move.
    public static Move findBestMove(GameState gs) {
        Node root = new Node(null);
        for (Move m : gs.getLegalMoves()) {
            root.addChild(new Node(m));
        }
        for (Node n : root.children) {
            n.depthFirstTree(DEPTH);
        }
        System.out.println(getMaxEval(root.children).eval);
        System.out.println(getMinEval(root.children).eval);

        Collections.shuffle(root.children);
        if (gs.getColor()) {
            return getMaxEval(root.children).getMove();
        } else {
            return getMinEval(root.children).getMove();
        }
    }


    // Nodes used to create a move tree consisting off all reachable positions.
    private static class Node {

        Move move;
        Double eval = null;
        ArrayList<Node> children = new ArrayList<>();

        Node(Move move) {
            this.move = move;
            nodeCount++;
        }

        public void addChild(Node n) {
            this.children.add(n);
        }

        public void addChildren(ArrayList<Node> nodes) {
            children.addAll(nodes);
        }

        public Move getMove() {
            return this.move;
        }


        // Creates a depth first search in order to find all possible moves to a certain depth. The
        // alpha beta pruning algorithm is used to give each node the best possible evaluation which can be reached
        // under the assumption that the enemy responds perfectly each time.
        public void depthFirstTree(int depth) {
            if (depth == 0) {
                this.eval = Evaluation.getEval(this.move.getExecState());
            } else {
                ArrayList<Move> legalMoves = this.getMove().getExecState().getLegalMoves();
                // Skip calculations if a checkmate has been found
                if (legalMoves.isEmpty()) {
                    if (this.getMove().getExecState().getColor()) {
                        this.eval = -999999d;
                    } else {
                        this.eval = 999999d;
                    }
                } else {
                    for (Move m : legalMoves) {
                        this.addChild(new Node(m));
                    }
                    for (Node n : children) {
                        n.depthFirstTree(depth - 1);
                    }
                    this.eval = alphaBetaPruning(this);
                }
                this.children = null;
            }
        }


        // Uses  the AlphaBetaPruning algorithm in order to return the child representing the best possible
        // evaluation given the moving color.
        public double alphaBetaPruning(Node n) {
            if (this.children.isEmpty()) {
                return Objects.requireNonNullElseGet(n.eval, () -> n.eval = Evaluation.getEval(n.move.getExecState()));
            } else {
                if (n.getMove().getExecState().getColor()) {
                    return getMaxEval(n.children).eval;
                } else {
                    return getMinEval(n.children).eval;
                }
            }
        }
    }


    // Given a Node, this method finds the child with the lowest evaluation
    public static Node getMinEval(ArrayList<Node> nodes) {
        int minIndex = -1;
        double min = Double.POSITIVE_INFINITY;
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).eval < min) {
                min = nodes.get(i).eval;
                minIndex = i;
            }
        }
        return nodes.get(minIndex);
    }


    // Given a Node, this method finds the child with the highest evaluation
    public static Node getMaxEval(ArrayList<Node> nodes) {
        int maxIndex = -1;
        double max = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).eval > max) {
                max = nodes.get(i).eval;
                maxIndex = i;
            }
        }
        return nodes.get(maxIndex);
    }

}
