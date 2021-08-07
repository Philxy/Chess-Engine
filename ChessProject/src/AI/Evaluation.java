package AI;

import Game.GameState;
import Game.Util;
import Pieces.Piece;

public class Evaluation {


    public static int evaluate(GameState gs) {

        // Check mate


        if (Util.isMate(gs)) {
            if (gs.getColor() == 'b') {
                return Integer.MAX_VALUE;
            } else {
                return Integer.MIN_VALUE;
            }
        }



        int c1 = 16;
        int c2 = 1;
        int c3 = 1;

        int material = 0;
        int mobility = 0;

        for (Piece p : gs.getAllPieces()) {
            material += p.getValue();
            mobility += p.mobility();
        }
        return c1 * material + c2 * mobility;

    }


    public static double rookMobility(int[] pos, char color) {
        double W[][] = {
                {0, 0, 0, 0, 0, 0, 0, 0},
                {5, 10, 10, 10, 10, 10, 10, 5},
                {-5, 0, 0, 0, 0, 0, 0, -5},
                {-5, 0, 0, 0, 0, 0, 0, -5},
                {-5, 0, 0, 0, 0, 0, 0, -5},
                {-5, 0, 0, 0, 0, 0, 0, -5},
                {-5, 0, 0, 0, 0, 0, 0, -5},
                {0, 0, 0, 5, 5, 0, 0, 0}};

        double B[][] = {
                {0, 0, 0, 5, 5, 0, 0, 0},
                {-5, 0, 0, 0, 0, 0, 0, -5},
                {-5, 0, 0, 0, 0, 0, 0, -5},
                {-5, 0, 0, 0, 0, 0, 0, -5},
                {-5, 0, 0, 0, 0, 0, 0, -5},
                {-5, 0, 0, 0, 0, 0, 0, -5},
                {5, 10, 10, 10, 10, 10, 10, 5},
                {0, 0, 0, 0, 0, 0, 0, 0}};

        if (color == 'w') {
            return W[pos[0]][pos[1]];
        } else {
            return B[pos[0]][pos[1]];
        }
    }


    public static double pawnMobility(int[] pos, char color) {
        double W[][] = {
                {0, 0, 0, 0, 0, 0, 0, 0},
                {50, 50, 50, 50, 50, 50, 50, 50},
                {10, 10, 20, 30, 30, 20, 10, 10},
                {5, 5, 10, 25, 25, 10, 5, 5},
                {0, 0, 0, 20, 20, 0, 0, 0},
                {5, -5, -10, 0, 0, -10, -5, 5},
                {5, 10, 10, -20, -20, 10, 10, 5},
                {0, 0, 0, 0, 0, 0, 0, 0}};

        double B[][] = {
                {0, 0, 0, 0, 0, 0, 0, 0},
                {5, 10, 10, -20, -20, 10, 10, 5},
                {5, -5, -10, 0, 0, -10, -5, 5},
                {0, 0, 0, 20, 20, 0, 0, 0},
                {5, 5, 10, 25, 25, 10, 5, 5},
                {10, 10, 20, 30, 30, 20, 10, 10},
                {50, 50, 50, 50, 50, 50, 50, 50},
                {0, 0, 0, 0, 0, 0, 0, 0}};

        if (color == 'w') {
            return W[pos[0]][pos[1]];
        } else {
            return B[pos[0]][pos[1]];
        }


    }

    public static double knightMobility(int[] pos, char color) {
        double W[][] = {{-50, -40, -30, -30, -30, -30, -40, -50},
                {-40, -20, 0, 0, 0, 0, -20, -40},
                {-30, 0, 10, 15, 15, 10, 0, -30},
                {-30, 5, 15, 20, 20, 15, 5, -30},
                {-30, 0, 15, 20, 20, 15, 0, -30},
                {-30, 5, 10, 15, 15, 10, 5, -30},
                {-40, -20, 0, 5, 5, 0, -20, -40},
                {-50, -40, -30, -30, -30, -30, -40, -50}};

        double B[][] = {
                {-50, -40, -30, -30, -30, -30, -40, -50},
                {-40, -20, 0, 5, 5, 0, -20, -40},
                {-30, 5, 10, 15, 15, 10, 5, -30},
                {-30, 0, 15, 20, 20, 15, 0, -30},
                {-30, 5, 15, 20, 20, 15, 5, -30},
                {-30, 0, 10, 15, 15, 10, 0, -30},
                {-40, -20, 0, 0, 0, 0, -20, -40},
                {-50, -40, -30, -30, -30, -30, -40, -50}
        };


        if (color == 'w') {
            return W[pos[0]][pos[1]];
        } else {
            return B[pos[0]][pos[1]];
        }
    }

}
