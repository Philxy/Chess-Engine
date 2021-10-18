package AI;

import Game.GameState;
import Game.Move;
import jdk.jshell.execution.JdiExecutionControlProvider;

import java.util.*;

// This class contains functions which are meant to find the best possible move
public class MoveGeneration {

    public static int DEPTH = 2;
    public static int nodeCount = 0;
    public static double minusInf = -999999d;
    public static double plusInf = 999999d;
    private static double diff = 0.01;


    // Find a random legal move
    public static Move randomMove(GameState gs) {
        ArrayList<Move> moves = gs.getLegalMoves();
        return moves.get((new Random()).nextInt(moves.size()));
    }


    // Heuristically finds the best possible move.
    public static Move findBestMove(GameState gs) {
        Node root = new Node(null);
        for (Move m : gs.getLegalMoves()) {
            Node n = new Node(m);
            n.eval = Evaluation.getEval(n.getMove().getExecState());
            root.addChild(n);
        }
        // Start with best eval first
        Collections.sort(root.children);
        if (gs.getColor()) {
            Collections.reverse(root.children);
        }
        // Begin the search
        for (Node n : root.children) {
            n.eval = n.minimaxBestFirst(DEPTH, minusInf, plusInf, n.getMove().getExecState().getColor());
        }
        // Chose best move after search is complete
        if (gs.getColor()) {
            return getMaxEval(root.children).getMove();
        } else {
            return getMinEval(root.children).getMove();
        }
    }


    // Nodes used to create a move tree consisting off all reachable positions.
    private static class Node implements Comparable<Node> {

        Move move;
        Double eval;
        ArrayList<Node> children = new ArrayList<>();

        Node(Move move) {
            this.move = move;
            if (move != null) {
                if (move.getExecState().getColor()) {
                    this.eval = minusInf;
                } else {
                    this.eval = plusInf;
                }
            }
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
        // mini-max algorithm is used to give each node the best possible evaluation which can be reached
        // under the assumption that the enemy responds perfectly each time. Alpha-Beta-Pruning prevents visiting
        // unnecessary nodes, therefore drastically reducing the computation time. The order in which the nodes
        // are being visited depends on the static evaluation. Choosing the best eval first further reduces computation
        // time.
        public double minimaxBestFirst(int depth, double alpha, double beta, boolean maximizing) {
            if (depth == 0) {
                return Evaluation.getEval(this.move.getExecState());
            }
            ArrayList<Move> legalMoves = this.getMove().getExecState().getLegalMoves();

            if (legalMoves.isEmpty()) {             // Skip further calculations if a checkmate has been found
                if (this.getMove().getExecState().getColor()) {
                    return minusInf;
                } else {
                    return plusInf;
                }
            }
            for (Move m : legalMoves) {
                Node n = new Node(m);
                this.addChild(n);
            }
            Collections.sort(this.children); // Sort in order to search best nodes first. Drastically reduces visited nodes
            if (maximizing) {
                Collections.reverse(this.children);
                double maxEval = minusInf;
                for (Node n : children) {
                    this.eval = n.minimaxBestFirst(depth - 1, alpha, beta, false);
                    maxEval = Double.max(maxEval, this.eval);
                    alpha = Double.max(alpha, this.eval);
                    if (beta - diff < alpha) {
                        break;
                    }
                }
                return maxEval;
            } else {
                double minEval = plusInf;
                for (Node n : children) {
                    this.eval = n.minimaxBestFirst(depth - 1, alpha, beta, true);
                    minEval = Double.min(minEval, this.eval);
                    beta = Double.min(beta, this.eval);
                    if (beta - diff < alpha) {
                        break;
                    }
                }
                return minEval;
            }
        }


        public int compareTo(Node o) {
            return Double.compare(this.eval, o.eval);
        }


        public String toString() {
            return Double.toString(this.eval);
        }
    }


    // Given a list of Nodes, this method finds the children with the lowest evaluation and returns a random one
    public static Node getMinEval(ArrayList<Node> nodes) {
        Collections.sort(nodes);
        ArrayList<Node> result = new ArrayList<>();
        for (Node n : nodes) {
            if (Math.abs(n.eval - nodes.get(0).eval) < diff) {
                result.add(n);
            }
        }
        return result.get((new Random()).nextInt(result.size()));
    }


    // Given a list of Nodes, this method finds the children with the highest evaluation and returns a random one
    public static Node getMaxEval(ArrayList<Node> nodes) {
        Collections.sort(nodes);
        ArrayList<Node> result = new ArrayList<>();
        for (Node n : nodes) {
            if (Math.abs(n.eval - nodes.get(nodes.size() - 1).eval) < diff) {
                result.add(n);
            }
        }
        return result.get((new Random()).nextInt(result.size()));

    }

}
