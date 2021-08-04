package Game;


import Pieces.Piece;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GameState {


    private Board currentBoard;
    private boolean blackNextMove = false;
    private int evaluation;
    private List<Piece> allPieces = new ArrayList<>();

    public GameState(Board board) {
        this.currentBoard = board;
        initPieceList();
    }

    public GameState(Board board, boolean blackNextMove) {
        this.currentBoard = board;
        this.blackNextMove = blackNextMove;
        initPieceList();
    }

    public GameState(Board board, boolean blackNextMove, List<Piece> allPieces) {
        this.currentBoard = board;
        this.blackNextMove = blackNextMove;
        this.allPieces = allPieces;
    }


    public void setAllPieces(List<Piece> allPieces) {
        this.allPieces = allPieces;
    }

    public void setEvaluation(int evaluation) {
        this.evaluation = evaluation;
    }


    /**
     * Evaluates the current board and adds all pieces to a list. The results will be set as the class
     * variables allPieces and evaluation
     */
    public void initPieceList() {
        List<Piece> pieces = new ArrayList<>();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece p = getSq(r, c);
                if (p != null) {
                    pieces.add(p);
                }
            }
        }
        allPieces = pieces;
    }

    /**
     * Getter for all  pieces on the in the current game state
     *
     * @return
     */
    public List<Piece> getAllPieces() {
        return allPieces;
    }

    /**
     * Piece placed at startSq will replace the piece at endSq.
     * If a piece has been taken it will be removed from the list containing all pieces
     *
     * @param startSq start square
     * @param endSq   end square
     */
    public void makeMove(int[] startSq, int[] endSq) {
        if (getSq(endSq[0], endSq[1]) != null) {
            allPieces.remove(getSq(endSq[0], endSq[1]));
        }
        getSq(startSq[0], startSq[1]).setPos(endSq);
        currentBoard.setSq(endSq, getSq(startSq[0], startSq[1]));
        currentBoard.setSq(startSq, null);
    }


    public int getEval() {
        int eval = 0;
        for (Piece p : allPieces) {
            eval += p.getValue();
        }
        return eval;
    }


    public void setBoard(Board newBoard) {
        currentBoard = newBoard;
        initPieceList();
    }


    public void blackNextMove(boolean blackNextMove) {
        this.blackNextMove = blackNextMove;
    }

    public boolean blackNextMove() {
        return blackNextMove;
    }

    public char getAttacker() {
        if (blackNextMove) {
            return 'w';
        } else {
            return 'b';
        }
    }


    public char getColor() {
        if (blackNextMove) {
            return 'b';
        } else {
            return 'w';
        }
    }

    public Piece getSq(int row, int col) {
        return this.currentBoard.getSq(row, col);
    }


    public void setSq(Piece p, int row, int col) {
        if (this.getSq(row, col) != null) {
            this.allPieces.remove(p);
        }
        this.currentBoard.setSq(new int[]{row, col}, p);
    }

    public Board getBoard() {
        return currentBoard;
    }


    /**
     * Returns a list containing all the pieces of the current color.
     *
     * @return
     */
    public List<Piece> getCurrPieces() {
        List<Piece> currPieces = new LinkedList<>();
        for (Piece p : allPieces) {
            if (p.getColor() == this.getColor()) {
                currPieces.add(p);
            }
        }
        return currPieces;
    }

    /**
     * Returns a list containing all the leagal moves of the current game state.
     *
     * @return
     */
    public ArrayList<Move> getAllMoves() {
        ArrayList<Move> moves = new ArrayList<>();
        for (Piece p : allPieces) {
            if (p.getColor() == this.getColor()) {
                moves.addAll(p.getLegalMoves(this));
            }
        }
        return moves;
    }

}

