package Pieces;

import Game.GameState;
import Game.Move;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece {

    public int[] pos;
    public char color;
    private List<Integer[]> legalMoves;
    private List<Move> legMoves;


    public abstract int getValue();
    public abstract String toString();
    public abstract boolean moveLegal(GameState gs, int[] startSq, int[] endSq);

    public Piece(char color) {
        this.color = color;
    }


    public Piece(int[] position, char color) {
        this.color = color;
        this.pos = position;
    }


    public void setPos(int[] position) {
        this.pos = position;
    }




    public Piece(char color, int[] pos) {
        this.color = color;
        this.pos = pos;
    }


    public Piece(Piece p, int[] pos) {
        this.color = p.getColor();
        this.pos = pos;
    }


    public int[] getPos() {
        return pos;
    }

    public char getColor() {
        if(this.color != '\u0000') {
            return this.color;
        } else {
            return 'x';
        }

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
                    if(gs.getSq(startSq[0],i) != null) {
                        return true;
                    }
                }
            }
            if(startSq[1] > endSq[1]) {
                for(int i = startSq[1]-1;  Math.abs(i - startSq[1])< Math.abs(startSq[1] - endSq[1]);i--) {
                    if(gs.getSq(startSq[0],i) != null) {
                        return true;
                    }
                }
            }
        }
        //vertical jumps
        if(startSq[1] == endSq[1]) {
            if(startSq[0] < endSq[0]) {
                for(int i = startSq[0]+1; Math.abs(i - startSq[0]) < Math.abs(startSq[0] - endSq[0]) ;i++) {
                    if(gs.getSq(i,startSq[1]) != null) {
                        return true;
                    }
                }
            }
            if(startSq[0] > endSq[0]) {
                for(int i = startSq[0]-1; Math.abs(i - startSq[0])< Math.abs(startSq[0] - endSq[0]) ;i--) {
                    if(gs.getSq(i,startSq[1]) != null) {
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
                    if(gs.getSq(startSq[0]-i,startSq[1]+i ) != null) {
                        return true;
                    }
                }
            }
            //up left
            if(endSq[1] < startSq[1] && endSq[0] < startSq[0]) {
                for(int i = 1; i < Math.abs(endSq[1]-startSq[1]); i++) {
                    if(gs.getSq(startSq[0]-i,startSq[1]-i ) != null) {
                        return true;
                    }
                }
            }
            //down right
            if(endSq[1] > startSq[1] && endSq[0] > startSq[0]) {
                for(int i = 1; i < Math.abs(endSq[1]-startSq[1]); i++) {
                    if(gs.getSq(startSq[0]+i,startSq[1]+i) != null) {
                        return true;
                    }
                }
            }
            //down left
            if(endSq[1] < startSq[1] && endSq[0] > startSq[0]) {
                for(int i = 1; i < Math.abs(endSq[1]-startSq[1]); i++) {
                    if(gs.getSq(startSq[0]+i,startSq[1]-i) != null) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    /**
     * Determines weather a pieces jumped vertically or horizontally over other pieces.
     * @param gs
     * @param startSq
     * @param endSq
     * @return
     */
    public boolean jumpedStraight(GameState gs, int[] startSq, int[] endSq) {
        // horizontal jumps
        if(startSq[0] == endSq[0]) {
            if(startSq[1] < endSq[1]) {
                for(int i = startSq[1]+1; Math.abs(i - startSq[1]) < Math.abs(startSq[1] - endSq[1]) ;i++) {
                    if(gs.getSq(startSq[0],i) != null) {
                        return true;
                    }
                }
            }
            if(startSq[1] > endSq[1]) {
                for(int i = startSq[1]-1;  Math.abs(i - startSq[1])< Math.abs(startSq[1] - endSq[1]);i--) {
                    if(gs.getSq(startSq[0],i) != null) {
                        return true;
                    }
                }
            }
        }
        //vertical jumps
        if(startSq[1] == endSq[1]) {
            if(startSq[0] < endSq[0]) {
                for(int i = startSq[0]+1; Math.abs(i - startSq[0]) < Math.abs(startSq[0] - endSq[0]) ;i++) {
                    if(gs.getSq(i,startSq[1]) != null) {
                        return true;
                    }
                }
            }
            if(startSq[0] > endSq[0]) {
                for(int i = startSq[0]-1; Math.abs(i - startSq[0])< Math.abs(startSq[0] - endSq[0]) ;i--) {
                    if(gs.getSq(i,startSq[1]) != null) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Determines weather a pieces jumped diagonally over other pieces.
     * @param gs
     * @param startSq
     * @param endSq
     * @return
     */
    public boolean jumpedDiagonal(GameState gs, int[] startSq, int[] endSq) {
        //diagonal jumps
        if(Math.abs(startSq[0] - endSq[0]) == Math.abs(startSq[1]-endSq[1])) {
            //up right
            if(endSq[1]>startSq[1] && endSq[0] < startSq[0]) {
                for(int i = 1; i < Math.abs(endSq[1]-startSq[1]); i++) {
                    if(gs.getSq(startSq[0]-i,startSq[1]+i ) != null) {
                        return true;
                    }
                }
            }
            //up left
            if(endSq[1] < startSq[1] && endSq[0] < startSq[0]) {
                for(int i = 1; i < Math.abs(endSq[1]-startSq[1]); i++) {
                    if(gs.getSq(startSq[0]-i,startSq[1]-i ) != null) {
                        return true;
                    }
                }
            }
            //down right
            if(endSq[1] > startSq[1] && endSq[0] > startSq[0]) {
                for(int i = 1; i < Math.abs(endSq[1]-startSq[1]); i++) {
                    if(gs.getSq(startSq[0]+i,startSq[1]+i) != null) {
                        return true;
                    }
                }
            }
            //down left
            if(endSq[1] < startSq[1] && endSq[0] > startSq[0]) {
                for(int i = 1; i < Math.abs(endSq[1]-startSq[1]); i++) {
                    if(gs.getSq(startSq[0]+i,startSq[1]-i) != null) {
                        return true;
                    }
                }
            }
        }
        return false;
    }



    /**
     * This method returns an Integer-List containing all the legal moves of a piece.
     * @param gs
     * @return
     */
    public List<Integer[]> legalMoves(GameState gs) {
        List<Integer[]> legalMoves = new ArrayList<Integer[]>();
        for(int r = 0; r<8; r++) {
            for (int c = 0; c < 8; c++) {
                Move move = new Move(gs, pos, new int[] {r,c});
                if (move.legalMove()) {
                    legalMoves.add(new Integer[]{r,c});
                }
            }
        }
        return legalMoves;
    }

    /**
     * Returns a list containing all the legal move of the piece
     * @param gs
     * @return
     */
    public List<Move> getLegalMoves(GameState gs) {
        List<Move> legalMoves = new ArrayList<Move>();
        for(int r = 0; r<8; r++) {
            for (int c = 0; c < 8; c++) {
                Move move = new Move(gs, this.getPos(), new int[]{r,c});
                if(move.legalMove()) {
                    legalMoves.add(move);
                }
            }
        }
        return legalMoves;
    }



}
