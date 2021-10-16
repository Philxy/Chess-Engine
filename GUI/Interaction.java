package GUI;

import Game.Main;
import Game.Move;
import Pieces.Piece;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Interaction implements EventHandler<MouseEvent> {

    private static List<Integer[]> selectedSquares = new ArrayList<Integer[]>();


    public void handle(MouseEvent click) {
        Integer[] square = new Integer[2];
        square[1] = Math.floorDiv((int) click.getX(), Screen.size);
        square[0] = Math.floorDiv((int) click.getY(), Screen.size);
        Rectangle r = new Rectangle(Screen.size, Screen.size);
        r.setFill(Color.LIGHTBLUE);
        ImageView pic = null;
        if (Main.mainState.getCurrBoard().getPiece(square[0] * 8 + square[1]) != null) {
            pic = Screen.getImage(Main.mainState.getCurrBoard().getPiece(square[0] * 8 + square[1]).toString());
            pic.setFitWidth(Screen.size);
            pic.setFitHeight(Screen.size);
        }
        if (!selectedSquares.contains(square)) {
            selectedSquares.add(square);
            if (selectedSquares.size() == 1) {
                Screen.getGridPane().add(r, square[1], square[0]);
                if (pic != null) {
                    Screen.getGridPane().add(pic, square[1], square[0]);
                }
            }
            if (selectedSquares.size() == 2) {
                int[] sq1 = new int[2];
                sq1[0] = selectedSquares.get(0)[0];
                sq1[1] = selectedSquares.get(0)[1];
                int[] sq2 = new int[2];
                sq2[0] = selectedSquares.get(1)[0];
                sq2[1] = selectedSquares.get(1)[1];
                Piece selPiece = Main.mainState.getCurrBoard().getPiece(sq1[0] * 8 + sq1[1]);
                if (selPiece == null) {
                    selectedSquares.clear();
                } else {
                    Move move = new Move(Main.mainState, Main.mainState.getCurrBoard().getPiece(sq1[0] * 8 + sq1[1]), sq2[0] * 8 + sq2[1]);
                    if (move.moveLegal()) {
                        move.updateMainBoard();
                    }
                    Screen.getGridPane().getChildren().clear();
                }
                Screen.updateBoard();
                selectedSquares.clear();
            }
        } else {
            selectedSquares.clear();
        }


    }


}
