package GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;


public class Screen extends Application {

    private static BorderPane borderPane = new BorderPane();
    private static GridPane board = new GridPane();

    public static final int size = 80;
    private static Map<String, Image> pieces = new HashMap<String, Image>();
    private static String[] allPieces = {"bR", "bN", "bB", "bQ", "bK", "bP", "wR", "wK", "wB", "wQ", "wN", "wP"};

    /**
     * Drawing the board and the pieces according to the current game state.
     *
     * @param primaryStage
     * @throws Exception
     */
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(borderPane, size * 8, size * 8);
        VBox depths = new VBox();
        borderPane.setRight(depths);
        borderPane.setLeft(board);
        board.setOnMouseClicked(new Interaction()); // mouse event handler
        scene.setOnKeyPressed(new nextMove());
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
            for (String piece : allPieces) {
                FileInputStream inputStream = new FileInputStream( "ChessProject" + File.separator+ "src" + File.separator + "Pics" + File.separator + piece + ".png");
                Image pieceImage = new Image(inputStream);
                pieces.put(piece, pieceImage);
            }
        } catch (Exception e) {
            System.out.println("Some error whilst loading images.");
        }
    }

    public static void updateBoard() {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Rectangle square = new Rectangle(size, size);
                if ((r + c) % 2 == 0) {
                    square.setFill(Color.WHITE);
                } else {
                    square.setFill(Color.BLUEVIOLET);
                }
                board.add(square, c, r);
            }
        }

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                ImageView pic = new ImageView(pieces.get(Game.Main.getBoard()[r][c]));
                pic.setFitHeight(size);
                pic.setFitWidth(size);
                board.add(pic, c, r);
            }
        }
    }


    public static ImageView getImage(String piece) {
        return new ImageView(pieces.get(piece));
    }

    public static GridPane getGridPane() {
        return board;
    }


}
