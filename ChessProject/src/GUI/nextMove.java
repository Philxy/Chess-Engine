package GUI;

import AI.Calculation;
import AI.Calcutations;
import AI.NodeTree;
import AI.Tree;
import Game.Main;
import Game.Move;
import Pieces.Piece;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.Arrays;

public class nextMove implements EventHandler<KeyEvent> {



    public void handle(KeyEvent e) {

        if(e.getCode().equals(KeyCode.ENTER)) {
            long t1 = System.nanoTime();
            Move move = Calcutations.findRandomMove(Main.getMainGS());
            move.updateBoard();
            long t2 = System.nanoTime();
            Screen.getGridPane().getChildren().clear();
            Screen.updateBoard();
            System.out.println("Time per move in ms: "+Math.abs(t2-t1)/ Math.pow(10, 6));
            System.out.println(Main.getMainGS().getEval());
            /*
            long t1 = System.nanoTime();
            Calculation calc = new Calculation(Main.getMainGS());
            Move move = calc.findRandomMove();
            move.updateBoard();
            long t2 = System.nanoTime();
            Screen.getGridPane().getChildren().clear();
            Screen.updateBoard();
            System.out.println("Time per move in ms: "+Math.abs(t2-t1)/ Math.pow(10, 6));
             */
        }


        if(e.getCode().equals(KeyCode.M)) {

        }


        if(e.getCode().equals(KeyCode.SPACE)) {
            long t1 = System.nanoTime();
            AI.Tree tree = new Tree(Main.getMainGS(), 2);
            System.out.println(tree.getSize());
            tree.bestEval().updateBoard();
            long t2 = System.nanoTime();
            Screen.getGridPane().getChildren().clear();
            Screen.updateBoard();
            System.out.println("Time per move in ms: "+Math.abs(t2-t1)/ Math.pow(10, 6));
            /*
            long t1 = System.nanoTime();
            Calculation calc = new Calculation(Main.getMainGS());

            System.out.println(calc.getMoveTree().getChildren().size());
            try {
                Move move = calc.findBestMove();
                move.updateBoard();
                System.out.println("Start square: "+Arrays.toString(move.getStartSq()));
                System.out.println("End square: "+Arrays.toString(move.getEndSq()));
            } catch (Exception exception) {
                System.out.println("error");
                exception.printStackTrace();
            }
            long t2 = System.nanoTime();
            Screen.getGridPane().getChildren().clear();
            Screen.updateBoard();
            System.out.println("Time per move in ms: "+Math.abs(t2-t1)/ Math.pow(10, 6));
             */
        }



    }


}
