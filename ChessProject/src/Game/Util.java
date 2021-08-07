package Game;

import Pieces.King;
import Pieces.Piece;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Util {


    /**
     * Determines weather a square is being attacked by a specific color.
     *
     * @param gs
     * @param attackingSquare
     * @param attackingColor
     * @return
     */
    public static boolean isAttacked(GameState gs, int[] attackingSquare, char attackingColor) {
        if (gs.getSq(attackingSquare[0], attackingSquare[1]) != null) {
            for (Piece p : gs.getAllPieces()) {
                if (p.moveLegal(gs, p.getPos(), attackingSquare)) {
                    return true;
                }
            }
        } else {
            for (Piece p : gs.getAllPieces()) {
                if (p.getColor() == attackingColor) {
                    if (p.moveLegal(gs, p.getPos(), attackingSquare) && !Arrays.equals(attackingSquare, p.getPos())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Returns a list containing all the pieces attacking the king. Returns null if king isn't being attacked
     *
     * @param gs
     * @return
     */
    public static boolean isCheck(GameState gs, char color) {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                if (gs.getSq(r, c) instanceof King && gs.getSq(r, c).getColor() == color) {
                    if (isAttacked(gs, new int[]{r, c}, 'x')) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public static boolean isMate(GameState gs) {
        return gs.getAllMoves().size() == 0;
    }

    /**
     * Given a square of the chess board this method returns the formal name of the square using proper  chess notation
     *
     * @param sq
     * @return
     */
    public static String sqName(int[] sq) {
        String s = "";
        switch (sq[1]) {
            case 0:
                s = "a";
                break;
            case 1:
                s = "b";
                break;
            case 2:
                s = "c";
                break;
            case 3:
                s = "d";
                break;
            case 4:
                s = "e";
                break;
            case 5:
                s = "f";
                break;
            case 6:
                s = "g";
                break;
            case 7:
                s = "h";
                break;
            default:
                s = "";
                break;
        }
        switch (sq[0]) {
            case 0:
                s = s + 8;
                break;
            case 1:
                s = s + 7;
                break;
            case 2:
                s = s + 6;
                break;
            case 3:
                s = s + 5;
                break;
            case 4:
                s = s + 4;
                break;
            case 5:
                s = s + 3;
                break;
            case 6:
                s = s + 2;
                break;
            case 7:
                s = s + 1;
                break;
            default:
                s = "";
                break;
        }
        return s;
    }

    /**
     * Splits a list into a partitioned sublist
     *
     * @param list
     * @param s    number of sublists
     * @param <T>
     * @return
     */
    public static <T> ArrayList[] splitList(ArrayList<T> list, int s) {
        int size = list.size();
        ArrayList[] result = new ArrayList[s];
        //System.out.println(list.size());
        for (int i = 0; i < s - 1; i++) {
            result[i] = new ArrayList<>(list.subList(i * (size + 1) / s, (i + 1) * (size + 1) / s));
        }
        result[s - 1] = new ArrayList<>(list.subList((s - 1) * (size + 1) / s, size));

        return result;
    }


    /**
     * Given a list of comparable objects this method returns a list containing all the objects having the same
     * value as the highes or lowest value of the initial list.
     *
     * @param list
     * @param comp
     * @param max
     * @param <T>
     * @return
     */
    public static <T> ArrayList<T> getEquals(ArrayList<T> list, Comparator<T> comp, boolean max) {
        if (!list.isEmpty()) {
            list.sort(comp);
            ArrayList<T> result = new ArrayList<>();
            if (max) {
                result.add(list.get(list.size() - 1));
                for (int i = list.size() - 1; i > 0; i--) {
                    if (list.get(i) == list.get(i - 1)) {
                        result.add(list.get(i - 1));
                    }
                }
            } else {
                result.add(list.get(0));
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i) == list.get(i + 1)) {
                        result.add(list.get(i + 1));
                    }
                }
            }
        }
        return null;
    }
}
