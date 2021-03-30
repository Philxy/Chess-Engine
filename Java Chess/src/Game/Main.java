package Game;


import GUI.Screen;
import Pieces.Piece;
import Pieces.Rook;
import javafx.application.Application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main  {

    private static GameState mainState;


    public static void main(String[] args) {


        Screen.loadImages(); // load Images one time
        mainState = new GameState(new Board());    // state of main game
        Application.launch(GUI.Screen.class);
    }

    public static String[][] getBoard() {
        return mainState.getBoard();
    }

    public static void setBoard(String[][] newBoard) {
        mainState.setBoard(newBoard);

    }

    public static Piece[][] getBoardP() {
        return mainState.getBoardP();
    }

    public static GameState getMainGS() {
        return mainState;
    }

    public static void setMainState(GameState newGS) {
        mainState = new GameState(newGS.getBoardObject(), newGS.blackNextMove());
    }

}
