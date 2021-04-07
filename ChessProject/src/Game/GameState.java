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

    public GameState(Board board, boolean blackNextMove, int evaluation, List<Piece> allPieces) {
        this.currentBoard = board;
        this.blackNextMove = blackNextMove;
        this.allPieces = allPieces;
        this.evaluation = evaluation;
    }


    public void setAllPieces(List<Piece> allPieces) {
        this.allPieces = allPieces;
    }

    public void setEvaluation(int evaluation) {
        this.evaluation = evaluation;
    }

    /**
     * Given a game state and a move this method will return a new game state with the executed move. Move must be legal!
     */

    public GameState execMove(Move move) {
        return null;
    }


    /**
     * Adds all the pieces of the current board to a list.
     *
     */
    public void initPieceList() {
        List<Piece> pieces = new ArrayList<>();
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                Piece p = getSq(r,c);
                if (p != null) {
                    allPieces.add(p);
                    evaluation = evaluation + p.getValue();
                }
            }
        }
    }

    public List<Piece> getAllPieces() {
        return allPieces;
    }

    /**
     * Piece placed at startSq will replace the piece at endSq.
     * If a piece has been taken it will be removed from the list containing all pieces
     * @param startSq start square
     * @param endSq end square
     */
    public void makeMove(int[] startSq, int[] endSq) {
        if(getSq(endSq[0], endSq[1]) != null) {
            allPieces.remove(getSq(endSq[0], endSq[1]));
            evaluation = evaluation - getSq(endSq[0], endSq[1]).getValue();
        }
        getSq(startSq[0], startSq[1]).setPos(endSq);
        currentBoard.setSq(endSq, getSq(startSq[0], startSq[1]));
        currentBoard.setSq(startSq, null);
    }


    public int getEval() {
        return evaluation;
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
        if(this.getSq(row, col) != null) {
            this.allPieces.remove(p);
        }
        this.currentBoard.setSq(new int[]{row, col}, p);
    }

    public Board getBoard() {
        return currentBoard;
    }

    public List<Piece> getCurrPieces() {
        List<Piece> currPieces= new LinkedList<>();
        for(Piece p: allPieces) {
            if(p.getColor() == this.getColor()) {
                currPieces.add(p);
            }
        }
        return currPieces;
    }

}

