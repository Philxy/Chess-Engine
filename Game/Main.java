package Game;


import GUI.Screen;
import javafx.application.Application;

import java.util.Arrays;
import java.util.Collections;

public class Main {

    public static GameState mainState = new GameState();

    public static void main(String[] args) {
        Screen.loadImages();
        Application.launch(Screen.class);
    }


}
