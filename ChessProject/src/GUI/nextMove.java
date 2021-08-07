package GUI;

import AI.Calcutations;
import AI.Tree;
import Game.Main;
import Game.Move;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class nextMove implements EventHandler<KeyEvent> {


    public void handle(KeyEvent e) {

        if (e.getCode().equals(KeyCode.ENTER)) {
            long t1 = System.nanoTime();
            Move move = Calcutations.findRandomMove(Main.getMainGS());
            move.updateBoard();
            long t2 = System.nanoTime();
            Screen.getGridPane().getChildren().clear();
            Screen.updateBoard();
            //System.out.println("Time per move in ms: " + Math.abs(t2 - t1) / Math.pow(10, 6));
        }


        if (e.getCode().equals(KeyCode.SPACE)) {
            long t1 = System.nanoTime();
            AI.Tree tree = new Tree(Main.getMainGS(), Calcutations.depth);
            //System.out.println(tree.bestEval());
            tree.alphaBetaPruningMove().updateBoard();
            long t2 = System.nanoTime();
            Screen.getGridPane().getChildren().clear();
            Screen.updateBoard();
            System.out.println("Time elapsed in ms: " + Math.abs(t2 - t1) / Math.pow(10, 6));
        }


    }


}
