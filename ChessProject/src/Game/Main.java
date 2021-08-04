package Game;


import GUI.Screen;
import javafx.application.Application;

public class Main  {

    private static GameState mainState;
    private static String[][] mainBoardS;


    public static void main(String[] args) {
        Board mainBoard = new Board();
        mainState = new GameState(mainBoard);       // state of main game
        Screen.loadImages();                        // load Images one time
        Application.launch(Screen.class);
    }

    public static String[][] getBoard() {
        mainBoardS = mainState.getBoard().getBoardS();
        return mainBoardS;
    }

    public static void setBoard(Board board) {
        mainState.setBoard(board);
    }

    public static GameState getMainGS() {
        return mainState;
    }



}
