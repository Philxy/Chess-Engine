package AI;

import java.util.ArrayList;
import java.util.List;

public class Node<Move> {
    private Move move = null;
    private List<Node<Move>> children = new ArrayList<>();
    private Node<Move> parent = null;

    public Node(Move move) {
        this.move = move;
    }

    public Node<Move> getRoot() {
        return this;
    }

    public void setParent(Node<Move> parent) {
        this.parent = parent;
    }

    public Node<Move> getParent() {
        return this.parent;
    }

    public List<Node<Move>> getChildren() {
        return children;
    }

    public void addChildren(List<Node<Move>> children) {
        children.forEach(each -> each.setParent(this));
        this.children.addAll(children);
    }

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }

    public void addChild(Node<Move> child) {
        child.setParent(this);
        this.children.add(child);
        return;
    }

}
