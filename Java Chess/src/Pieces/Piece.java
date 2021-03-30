package Pieces;

import Game.GameState;
import Game.Main;
import Game.Move;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public abstract class Piece {
    public int value;
    public int[] pos;
    public char color;
    private List<Integer[]> legalMoves;
    private List<Move> legMoves;


    public abstract boolean moveLegal(GameState gs, int[] startSq, int[] endSq);
    public abstract int getValue();

    public Piece(char color, int[] pos) {
        this.color = color;
        this.pos = pos;
    }

    public Piece(GameState gs, char color, int[] pos) {
        this.color = color;
        this.pos = pos;
        this.legalMoves = legalMoves(gs);
    }


    public int[] getPos() {
        return pos;
    }



    public char getColor() {
        return this.color;
    }

    /**
     * Returns a list containing all the attackers of a specific square depending on the color of the attackers.
     * @param gs
     * @param attackingSquare
     * @param attackingColor
     * @return
     */
    public static List<int[]> isAttacked(GameState gs, int[] attackingSquare, char attackingColor) {
        List<int[]> attackers = new ArrayList<int[]>();
        if(gs.getBoard()[attackingSquare[0]][attackingSquare[1]] != "--") {
            for(int r = 0; r<8;r++) {
                for(int c = 0; c<8;c++) {
                    Piece piece = gs.getBoardP()[r][c];
                    if(piece != null) {
                        int[] pos = {r,c};
                        if(piece.moveLegal(gs, pos , attackingSquare) && attackingSquare != pos) {
                            attackers.add(pos);
                        }
                    }
                }
            }
            if(attackers.size() !=0) {
                return attackers;
            } else {
                return null;
            }
        } else {
            for(int r = 0; r<8;r++) {
                for(int c = 0; c<8;c++) {
                    Piece piece = gs.getBoardP()[r][c];
                    if(piece != null && piece.getColor() == attackingColor) {
                        int[] pos = {r,c};
                        if(piece.moveLegal(gs, pos , attackingSquare) && !Arrays.equals(attackingSquare, pos)) {
                            attackers.add(pos);
                        }
                    }
                }
            }
            if(attackers.size() !=0) {
                return attackers;
            } else {
                return null;
            }
        }
    }

    /**
     * Returns a list containing all the pieces attacking the king. Returns null if king isn't being attacked
     * @param gs
     * @return
     */
    public static boolean isCheck(GameState gs, char color) {
        for(int r = 0; r<8;r++) {
            for(int c = 0; c<8;c++) {
                if(gs.getBoardP()[r][c] instanceof King && gs.getBoardP()[r][c].getColor() == color) {
                    List<int[]> attackers = isAttacked(gs, new int[] {r,c}, 'x');
                    if (attackers != null) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Returns the position of the king given its color
     */
    public static int[] getKing(GameState gs, char color) {
        int[] pos = new int[2];
        for(int r = 0; r<8;r++) {
            for(int c = 0; c<8;c++) {
                if(gs.getBoardP()[r][c] instanceof King && gs.getBoard()[r][c].charAt(0) == color) {
                    pos = new int[]{r,c};
                }
            }
        }
        return pos;
    }


    /**
     * Determines weather a piece jumped over other pieces whilst moving from one square to another
     * @param gs
     * @param startSq
     * @param endSq
     * @return
     */
    public static boolean jumped(GameState gs, int[] startSq, int[] endSq) {
       // horizontal jumps
        if(startSq[0] == endSq[0]) {
            if(startSq[1] < endSq[1]) {
                for(int i = startSq[1]+1; Math.abs(i - startSq[1]) < Math.abs(startSq[1] - endSq[1]) ;i++) {
                    if(gs.getBoard()[startSq[0]][i] != "--") {
                        return true;
                    }
                }
            }
            if(startSq[1] > endSq[1]) {
                for(int i = startSq[1]-1;  Math.abs(i - startSq[1])< Math.abs(startSq[1] - endSq[1]);i--) {
                    if(gs.getBoard()[startSq[0]][i] != "--") {
                        return true;
                    }
                }
            }
        }
        //vertical jumps
        if(startSq[1] == endSq[1]) {
            if(startSq[0] < endSq[0]) {
                for(int i = startSq[0]+1; Math.abs(i - startSq[0])< Math.abs(startSq[0] - endSq[0]) ;i++) {
                    if(gs.getBoard()[i][startSq[1]] != "--") {
                        return true;
                    }
                }
            }
            if(startSq[0] > endSq[0]) {
                for(int i = startSq[0]-1; Math.abs(i - startSq[0])< Math.abs(startSq[0] - endSq[0]) ;i--) {
                    if(gs.getBoard()[i][startSq[1]] != "--") {
                        return true;
                    }
                }
            }
        }
        //diagonal jumps
        if(Math.abs(startSq[0] - endSq[0]) == Math.abs(startSq[1]-endSq[1])) {
            //up right
            if(endSq[1]>startSq[1] && endSq[0] < startSq[0]) {
                for(int i = 1; i < Math.abs(endSq[1]-startSq[1]); i++) {
                    if(gs.getBoard()[startSq[0]-i][startSq[1]+i] != "--") {
                        return true;
                    }
                }
            }
            //up left
            if(endSq[1] < startSq[1] && endSq[0] < startSq[0]) {
                for(int i = 1; i < Math.abs(endSq[1]-startSq[1]); i++) {
                    if(gs.getBoard()[startSq[0]-i][startSq[1]-i] != "--") {
                        return true;
                    }
                }
            }
            //down right
            if(endSq[1] > startSq[1] && endSq[0] > startSq[0]) {
                for(int i = 1; i < Math.abs(endSq[1]-startSq[1]); i++) {
                    if(gs.getBoard()[startSq[0]+i][startSq[1]+i] != "--") {
                        return true;
                    }
                }
            }
            //down left
            if(endSq[1] < startSq[1] && endSq[0] > startSq[0]) {
                for(int i = 1; i < Math.abs(endSq[1]-startSq[1]); i++) {
                    if(gs.getBoard()[startSq[0]+i][startSq[1]-i] != "--") {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * This method returns a List containing all the legal moves of a piece.
     * @param gs
     * @return
     */
    public List<Integer[]> legalMoves(GameState gs) {
        List<Integer[]> legalMoves = new ArrayList<>();
        for(int r = 0; r<8; r++) {
            for (int c = 0; c < 8; c++) {
                Integer[] temp1 = {r,c};
                int[] temp2 = {r,c};
                if ((new Game.Move(gs, pos, temp2)).legalMove()) {
                    legalMoves.add(temp1);
                }
            }
        }
        return legalMoves;
    }

    public List<Move> getLegalMoves(GameState gs) {
        List<Move> legalMoves = new ArrayList<Move>();
        for(int r = 0; r<8; r++) {
            for (int c = 0; c < 8; c++) {
                Move move = new Move(gs, this.pos, new int[]{r,c});
                if(move.legalMove()) {
                    legalMoves.add(move);
                }
            }
        }
        return legalMoves;
    }

}
