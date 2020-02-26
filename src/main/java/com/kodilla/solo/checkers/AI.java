package com.kodilla.solo.checkers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AI {
    private ChessBoard board;
    FigureColor computerFiguresColor;

    public AI(ChessBoard board, FigureColor computerFiguresColor) {
        this.board = board;
        this.computerFiguresColor = computerFiguresColor;
    }

    HashMap<Integer, Move> moveWithScore = new HashMap<Integer, Move>();
    ArrayList<Move> possibleMoves = new ArrayList<Move>();

    public void generarePossibleMoves() {
        for (int y1 = 0; y1 < 8; y1++) {
            for (int x1 = 0; x1 < 8; x1++) {
                if (board.getFigure(x1, y1).getColor().equals(computerFiguresColor)) {
                    for (int y2 = 0; y2 < 8; y2++) {
                        for (int x2 = 0; x2 < 8; x2++) {
                            if (board.isMoveValid(x1, y1, x2, y2))
                                possibleMoves.add(new Move(x1, y1, x2, y2));
                            if (board.isMoveValidWithHit(x1, y1, x2, y2))
                                possibleMoves.add(new Move(x1, y1, x2, y2));
                        }
                    }
                }
            }
        }
    }

    public void generateMoveWithScore() {
        for (Move currentMove : possibleMoves) {
            ChessBoard tempBoard = board;
            Integer tempScore = 0;
            tempBoard.move(currentMove.getX1(), currentMove.getY1(), currentMove.getX2(), currentMove.getY2());
            if (computerFiguresColor.equals(FigureColor.BLACK)) tempScore = tempBoard.calculateBoardValue();
            if (computerFiguresColor.equals(FigureColor.WHITE))tempScore = 0 - tempBoard.calculateBoardValue();
            moveWithScore.put(tempScore, currentMove);
        }
    }

    public Move selectBestMove() {
        generarePossibleMoves();
        generateMoveWithScore();
        Integer maxScore = 0;
        for (Map.Entry<Integer, Move> entry : moveWithScore.entrySet()) {
            if (maxScore < entry.getKey()) {
                maxScore = entry.getKey();
            }
        }
        return moveWithScore.get(maxScore);
    }
}