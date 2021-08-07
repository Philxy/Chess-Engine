package AI;

import Game.GameState;
import Game.Move;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Tree {

    private int depth;
    private ArrayList<Node> possibleMoves = new ArrayList<>();
    private int size = 0;


    /**
     * Constructs a  tree of all the possible position given a game state. The depth of the tree
     * is being controlled by a parameter.
     *
     * @param gs
     * @param depth
     */
    public Tree(GameState gs, int depth) {
        long t1 = System.nanoTime();

        this.depth = depth;
        for (Move m : gs.getAllMoves()) {
            possibleMoves.add(new Node(m));
        }

        /*
        for (Node n : possibleMoves) {
            n.findMoves(depth);
        }
         */


        long t2 = System.nanoTime();
        //System.out.println("Needed " + Math.abs(t2 - t1) / Math.pow(10, 6) + " to find and evaluate " + size + " moves");
    }

    public int getSize() {
        return size;
    }


    private class NodeComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return Integer.compare(o1.getEval(), o2.getEval());
        }
    }


    /**
     * Finds the best move of the move tree by applying the alpha beta puring algorithm. If multiple moves of the same
     * evaluation are being found, a random one will be returned. The preciseness of the move is heavily determined
     * by the strength of the evaluation function.
     *
     * @return
     */
    public Move bestEval() {
        long t1 = System.nanoTime();

        ArrayList<Integer> evals = new ArrayList<>();
        for (Node n : possibleMoves) {
            evals.add(n.alphaBetaPuring(n));
        }
        // print moves with evals
        ArrayList<String> s = new ArrayList<>();
        for (int i = 0; i < possibleMoves.size(); i++) {
            s.add(possibleMoves.get(i).getMove().toString() + " " + evals.get(i));
        }
        System.out.println(s.toString());

        ArrayList<Node> temp = new ArrayList<>();
        if (possibleMoves.size() != 0) {
            if (possibleMoves.get(0).getMove().getExecState().getColor() == 'w') {
                int minEval = Calcutations.findMin(evals);
                for (int i = 0; i < evals.size(); i++) {
                    if (Integer.valueOf(evals.get(i)) == minEval) {
                        temp.add(possibleMoves.get(i));
                    }
                }
                int randIndex = (new Random()).nextInt(temp.size());
                return temp.get(randIndex).getMove();
            } else {
                int maxEval = Calcutations.findMax(evals);
                for (int i = 0; i < evals.size(); i++) {
                    if (Integer.valueOf(evals.get(i)) == maxEval) {
                        temp.add(possibleMoves.get(i));
                    }
                }
                int randIndex = (new Random()).nextInt(temp.size());
                long t2 = System.nanoTime();
                System.out.println("Time needed to decide which move to pick in ms: " + Math.abs(t2 - t1) / Math.pow(10, 6));
                return temp.get(randIndex).getMove();
            }
        }
        return null;
    }


    /**
     * Represents a node of the tree containing all the possibles moves/games.
     */
    private class Node {

        private Move move;
        private ArrayList<Node> children = new ArrayList<>();
        private int eval;

        Node(Move move) {
            this.move = move;
            size++;
        }

        public void addChild(Node n) {
            this.children.add(n);
        }

        public Move getMove() {
            return this.move;
        }


        /**
         * Given a game state this method will construct a node tree containing all possible positions of a certain depth
         *
         * @param depth
         */
        public void findMoves(int depth) {
            if (depth == 0) {
                return;
            }
            ArrayList<Move> possMoves = this.getMove().getExecState().getAllMoves();
            for (Move m : possMoves) {
                Node newN = new Node(m);
                this.addChild(newN);
                newN.findMoves(depth - 1);
            }
        }

        /**
         * Implements the alpha beta puring algorithm. It recursively checks all the positions of a node tree beginning
         * at the bottom and returns the most favourable evaluation of all the positions considering the opponent responds
         * perfectly (according to the evaluation method) each move.
         *
         * @param n
         * @return
         */
        public int alphaBetaPuring(Node n) {
            if (n.getChildren().size() == 0) {
                return n.getEval();
            } else {
                List<Integer> I = new ArrayList<>();
                for (Node child : n.getChildren()) {
                    I.add(alphaBetaPuring(child));
                }
                if (this.getMove().getExecState().blackNextMove()) {
                    return Calcutations.findMin(I);
                } else {
                    return Calcutations.findMax(I);
                }
            }
        }


        /**
         * @param depth
         * @param alpha -inf initially
         * @param beta  +inf initially
         * @param max
         * @return
         */
        public int minimax(int depth, int alpha, int beta, boolean max) {
            if (depth == 0 || this.getChildren().isEmpty()) {
                return this.getEval();
            }
            //System.out.println("I am dumb");
            if (max) {
                int maxEva = Integer.MIN_VALUE;
                for (Node child : this.getChildren()) {
                    int eva = child.minimax(depth - 1, alpha, beta, false);
                    maxEva = Math.max(maxEva, eva);
                    alpha = Math.max(alpha, maxEva);
                    if (beta <= alpha) {
                        break;
                    }
                }
                return maxEva;
            } else {
                int minEva = Integer.MAX_VALUE;
                for (Node child : this.getChildren()) {
                    int eva = child.minimax(depth - 1, alpha, beta, true);
                    minEva = Math.min(minEva, eva);
                    beta = Math.min(beta, eva);
                    if (beta <= alpha) {
                        break;
                    }
                }
                return minEva;
            }
        }

        private ArrayList<Node> getChildren() {
            ArrayList<Node> result = new ArrayList<>();
            for (Move m : this.getMove().getExecState().getAllMoves()) {
                result.add(new Node(m));
            }
            return result;
        }


        private int getEval() {
            this.eval = Evaluation.evaluate(move.getExecutedState());
            return this.eval;
        }
    }

    public Move alphaBetaPruningMove() {
        ArrayList<Integer> evals = new ArrayList<>();
        for (Node n : possibleMoves) {
            evals.add(n.minimax(depth, Integer.MIN_VALUE, Integer.MAX_VALUE, !n.getMove().getExecutedState().blackNextMove()));
        }

        ArrayList<Node> temp = new ArrayList<>();
        if (possibleMoves.size() != 0) {
            if (possibleMoves.get(0).getMove().getExecState().getColor() == 'w') {
                int minEval = Calcutations.findMin(evals);
                for (int i = 0; i < evals.size(); i++) {
                    if (Integer.valueOf(evals.get(i)) == minEval) {
                        temp.add(possibleMoves.get(i));
                    }
                }
                int randIndex = (new Random()).nextInt(temp.size());
                return temp.get(randIndex).getMove();
            } else {
                int maxEval = Calcutations.findMax(evals);
                for (int i = 0; i < evals.size(); i++) {
                    if (Integer.valueOf(evals.get(i)) == maxEval) {
                        temp.add(possibleMoves.get(i));
                    }
                }
                int randIndex = (new Random()).nextInt(temp.size());
                long t2 = System.nanoTime();
                return temp.get(randIndex).getMove();
            }
        }
        return null;
    }


}
