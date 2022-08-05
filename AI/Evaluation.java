package AI;

import Game.Board;
import Game.GameState;
import Game.Util;
import Pieces.Piece;

import java.nio.file.StandardWatchEventKinds;
import java.util.ArrayList;

public class Evaluation {

    // weights
    static double MATERIAL = 100;
    static double MOBILITY = 4;
    static double CHECK = 100;

    public static double getEval(GameState gs) {
        double eval = 0;

        // Evaluate by material
        ArrayList<Piece> pieces = gs.getCurrBoard().getPieces(gs.getColor());
        pieces.addAll(gs.getCurrBoard().getPieces(!gs.getColor()));
        for (Piece p : pieces) {
            eval += MATERIAL * p.getValue() + MOBILITY * p.mobility();
        }

        // Check Handling
        if (gs.getColor() && Util.isCheck(gs.getCurrBoard(), gs.getColor())) {
            eval += -CHECK;
        } else if (!gs.getColor() && Util.isCheck(gs.getCurrBoard(), !gs.getColor())) {
            eval += CHECK;
        }

        return eval ;
    }
}
