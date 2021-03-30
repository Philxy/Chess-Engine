package GUI;

import AI.Calculation;
import Game.Main;
import Game.Move;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.security.Key;
import java.util.ArrayList;
import java.util.Timer;

public class nextMove implements EventHandler<KeyEvent> {



    public void handle(KeyEvent e) {

        if(e.getCode().equals(KeyCode.ENTER)) {
            long t1 = System.nanoTime();
            Calculation calc = new Calculation(Main.getMainGS());
            int[][] rand = calc.findRandomMove();
            Move move = new Move(Main.getMainGS(), rand[0], rand[1]);
            move.updateBoard();
            long t2 = System.nanoTime();
            Screen.updateBoard();
            System.out.println("Time per move in ms: "+Math.abs(t2-t1)/ Math.pow(10, 6));
        }





        if(e.getCode().equals(KeyCode.SPACE)) {
            long t1 = System.nanoTime();
            Calculation calc = new Calculation(Main.getMainGS());

            calc.initMoveTree(Main.getMainGS(), 2, 0);
            System.out.println(calc.getMoveTree().getChildren().size());
            Move move = null;
            try {
                move = calc.findBestMove();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            move.updateBoard();
            Screen.updateBoard();



            long t2 = System.nanoTime();
            Screen.updateBoard();
            System.out.println("Time per move in ms: "+Math.abs(t2-t1)/ Math.pow(10, 6));
        }


    }


}
