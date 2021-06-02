package AI;

import Game.GameState;
import Game.Move;
import Pieces.Piece;

import java.util.*;

public class NodeTree<Move> {
    private Move move = null;
    private List<NodeTree<Move>> children = new ArrayList<>();
    private NodeTree<Move> parent;

    public NodeTree(Move move) {
        this.move = move;
    }

    public NodeTree<Move> getRoot() {
        return this;
    }

    public void setParent(NodeTree<Move> parent) {
        this.parent = parent;
    }

    public NodeTree<Move> getParent() {
        return this.parent;
    }

    public List<NodeTree<Move>> getChildren() {
        return children;
    }

    public void addChildren(List<NodeTree<Move>> children) {
        children.forEach(each -> each.setParent(this));
        this.children.addAll(children);
    }

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }

    public void addChild(NodeTree<Move> child) {
        child.setParent(this);
        this.children.add(child);
    }

    /**
     * Initializes NodeTree containing all possible moves and responses
     * @param gs
     * @param depth
     * @param repetition
     * @return
     */
    public void initializeMoveTree(GameState gs, int depth, int repetition) {
        if(repetition != depth ) {
            for(Piece p: gs.getCurrPieces()) {
                for(Game.Move m: p.getLegalMoves(gs)) {
                    NodeTree<Move> node = new NodeTree(m);
                    this.addChild(node);
                    this.initializeMoveTree(m.getExecutedState(), depth, repetition+1);
                }
            }
        }
    }


    public List<NodeTree<Move>> getAllLeafNodes() {
        List<NodeTree<Move>> leafNodes = new LinkedList<>();
        if (this.children.isEmpty()) {
            leafNodes.add(this);
        } else {
            for (NodeTree<Move> child : this.children) {
                leafNodes.addAll(child.getAllLeafNodes());
            }
        }
        return leafNodes;
    }

    /**
     * Given a Leaf node of a node tree this method returns the child of the root which is the "ancestor" of the leaf.
     * @return
     */
    public NodeTree<Move> getRootChildOfLeaf() {
        if(this.getParent().getMove() == null) {
            return this;
        } else {
            this.getRootChildOfLeaf();
        }
        return null;
    }


    /**
     * This function recursively returns a tree containing all the possible positions which can be reached
     * within a certain depth.
     * @param gs
     * @param depth
     */
    public NodeTree<Move> getMoveTree(GameState gs, int depth) {
        if(depth != 0 && this.getChildren().isEmpty()) {
            for(Piece p: gs.getCurrPieces()) {
                for(Game.Move m: p.getLegalMoves(gs)) {
                    NodeTree<Move> node = new NodeTree(m);
                    this.addChild(node);
                    node.addChild(getMoveTree(m.getExecutedState(), depth-1));
                }
            }
        }
        return this;
    }



}
