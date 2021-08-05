package AI;

import Game.GameState;
import Pieces.Piece;

public class Evaluation {


    public static int evaluate(GameState gs) {

        /*
        // Check mate
        if (Util.isMate(gs)) {
            if (gs.getColor() == 'b') {
                return Integer.MAX_VALUE;
            } else {
                return Integer.MIN_VALUE;
            }
        }
         */
        return material(gs);
    }

    /**
     * Sums up the material on the board.
     *
     * @return
     */
    private static int material(GameState gs) {
        int material = 0;
        for (Piece p : gs.getAllPieces()) {
            material += p.getValue();
        }
        return material;
    }

}
