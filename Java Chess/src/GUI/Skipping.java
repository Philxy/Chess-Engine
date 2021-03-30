package GUI;

import Game.Main;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Skipping implements EventHandler<KeyEvent> {

    public void handle(KeyEvent e) {
        int currPosIndex = Main.getMainGS().getPositions().indexOf(Main.getMainGS());
        System.out.println(currPosIndex);

        if(e.getCode() == KeyCode.RIGHT) {
            if(currPosIndex < Main.getMainGS().getPositions().size() -1) {
                Main.setMainState(Main.getMainGS().getPositions().get(currPosIndex+1));
                Screen.updateBoard();
            }
        }


        if(e.getCode() == KeyCode.LEFT) {
            if(currPosIndex >= 1) {
                Main.setMainState(Main.getMainGS().getPositions().get(currPosIndex-1));
                Screen.updateBoard();
            }


        }


    }
}
