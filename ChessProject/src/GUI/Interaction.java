package GUI;

import Game.Main;
import Game.Move;
import Game.Util;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Interaction  implements EventHandler<MouseEvent> {

    private static List<Integer[]> selectedSquares = new ArrayList<Integer[]>();


    public void handle(MouseEvent click) {
        Integer[] square = new Integer[2];
        square[1] =  Math.floorDiv((int) click.getX(), Screen.size);
        square[0] =  Math.floorDiv((int) click.getY(), Screen.size);

        System.out.println(Util.sqName(new int[]{square[0], square[1]}));

        String selectedPiece;

        Rectangle r = new Rectangle(Screen.size, Screen.size);
        r.setFill(Color.LIGHTBLUE);
        ImageView pic = Screen.getImage(Main.getBoard()[square[0]][square[1]]);
        pic.setFitWidth(Screen.size);
        pic.setFitHeight(Screen.size);

        if(!selectedSquares.contains(square)) {
            selectedSquares.add(square);
            if(selectedSquares.size() == 1) {
                Screen.getGridPane().add(r, square[1], square[0]);
                Screen.getGridPane().add(pic, square[1], square[0]);

            }
            if(selectedSquares.size() == 2) {
                int[] sq1 = new int[2];
                sq1[0] = selectedSquares.get(0)[0].intValue();
                sq1[1] = selectedSquares.get(0)[1].intValue();
                int[] sq2 = new int[2];
                sq2[0] = selectedSquares.get(1)[0].intValue();
                sq2[1] = selectedSquares.get(1)[1].intValue();
                selectedPiece = Main.getBoard()[sq1[0]][sq1[1]];

                if(selectedPiece == "--") {
                    selectedSquares.clear();
                    Screen.updateBoard();
                }  else {
                    Move move = new Move(Main.getMainGS(), sq1, sq2);

                    if(move.legalMove()) {
                        move.updateBoard();
                    }
                    Screen.getGridPane().getChildren().clear();
                    Screen.updateBoard();
                }
                selectedSquares.clear();
            }
        } else {
            selectedSquares.clear();
        }



    }




}
