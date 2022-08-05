package GUI;


import AI.Evaluation;
import AI.MoveGeneration;
import Game.Main;
import Game.Move;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class nextMove implements EventHandler<KeyEvent> {


    public void handle(KeyEvent e) {

        if (e.getCode().equals(KeyCode.ENTER)) {
            long t1 = System.nanoTime();
            Move m = MoveGeneration.randomMove(Main.mainState);

            long t2 = System.nanoTime();
            m.updateMainBoard();
            System.out.println("Time needed to find random move ms: " + Math.abs(t2 - t1) / Math.pow(10, 6));
            Screen.getGridPane().getChildren().clear();
            Screen.updateBoard();

        }


        if (e.getCode().equals(KeyCode.SPACE)) {

            long t1 = System.nanoTime();
            Move m = MoveGeneration.bestMoveIterativeDeepening(Main.mainState);
            long t2 = System.nanoTime();
            System.out.println("Checked " + MoveGeneration.nodeCount + " positions with depth "+ (int) ( MoveGeneration.DEPTH+ 1) + " in: " +  Math.abs(t2 - t1) / Math.pow(10, 9) + " s" );
            MoveGeneration.nodeCount = 0;

            m.updateMainBoard();
            Screen.getGridPane().getChildren().clear();
            Screen.updateBoard();
        }

        if (e.getCode().equals(KeyCode.A)) {
            System.out.println(Evaluation.getEval(Main.mainState));

        }

    }


}
