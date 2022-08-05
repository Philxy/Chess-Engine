package Game;

import GUI.Screen;
import javafx.application.Application;

public class Main {

    public static GameState mainState;

    public static void main(String[] args) {
        mainState = new GameState();
        Screen.loadImages();
        Application.launch(Screen.class);
    }
}
