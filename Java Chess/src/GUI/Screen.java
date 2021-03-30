package GUI;
import Game.GameState;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.util.*;


public class Screen extends Application {

    private static GridPane board = new GridPane();
    public static final int size = 100;
    private static Map<String, Image> pieces = new HashMap<String, Image>();
    private static String[] allPieces = {"bR", "bN", "bB", "bQ", "bK", "bP", "wR", "wK", "wB", "wQ", "wN", "wP"};

    /**
     * Drawing the board and the pieces according to the current game state.
     * @param primaryStage
     * @throws Exception
     */
    public void start(Stage primaryStage) throws Exception{
        Scene scene = new Scene(board, size*8, size*8);
        board.setOnMouseClicked(new Interaction()); // mouse event handler
        scene.setOnKeyPressed(new nextMove());

        // arrow key event handler
        //scene.setOnKeyPressed(new Skipping());

        updateBoard();

        primaryStage.setTitle("Chess");
        primaryStage.setScene(scene);
        primaryStage.show();





    }








    /**
     * Loading the images of the pieces and storing them into a hash map
     */
    public static void loadImages() {
        try {
            for(String piece: allPieces) {
                FileInputStream inputStream = new FileInputStream("src/Pics/" + piece + ".png");
                Image pieceImage = new Image(inputStream);
                pieces.put(piece, pieceImage);
            }
        } catch (Exception e){
            System.out.println("Some error whilst loading images.");
        }


    }

    public static void updateBoard() {
        for(int r = 0; r < 8; r++) {
            for(int c = 0; c < 8; c++) {
                Rectangle square = new Rectangle(size, size);
                if( (r+c) % 2 == 0) {
                    square.setFill(Color.WHITE);
                }
                else {
                    square.setFill(Color.GREY);
                }
                board.add(square, c, r);
            }
        }

        for(int r = 0; r < 8; r++) {
            for(int c = 0; c < 8; c++) {
                ImageView pic = new ImageView(pieces.get(Game.Main.getBoard()[r][c]));
                pic.setFitHeight(size);
                pic.setFitWidth(size);
                board.add(pic, c, r);

            }
        }
    }

    public static ImageView getImage(String piece){
        return new ImageView(pieces.get(piece));
    }

    public static GridPane getGridPane() {
        return board;
    }





}
