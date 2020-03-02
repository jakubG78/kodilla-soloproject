package com.kodilla.solo.checkers;

import java.util.ArrayList;

public class AI {
    private ChessBoard board;
    FigureColor computerFiguresColor;
    ArrayList<MoveWithScore> moveWithScore = new ArrayList<MoveWithScore>();
    ArrayList<Move> possibleMoves = new ArrayList<Move>();

    public AI(ChessBoard board, FigureColor computerFiguresColor) {
        this.board = board;
        this.computerFiguresColor = computerFiguresColor;
    }

    public Move selectBestMove() {
        generatePossibleMoves();
        generateMoveWithScore();
        Integer maxScore = 0;
        Integer indexMaxScore=0;
        for (int i = 0; i < moveWithScore.size(); i++) {
            if (maxScore < moveWithScore.get(i).getScore()) {
                maxScore = moveWithScore.get(i).getScore();
                indexMaxScore = i;
            }
        }
        return moveWithScore.get(indexMaxScore).getMove();
    }

    private void generatePossibleMoves() {
        possibleMoves.clear();
        for (int y1 = 0; y1 < 8; y1++) {
            for (int x1 = 0; x1 < 8; x1++) {
                if (board.getFigure(x1, y1).getColor() == computerFiguresColor) {
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

    private void generateMoveWithScore() {
        moveWithScore.clear();
        for (Move currentMove : possibleMoves) {
            ChessBoard tempBoard = new ChessBoard(board);
            Integer tempScore = 0;
            tempBoard.move(currentMove.getX1(), currentMove.getY1(), currentMove.getX2(), currentMove.getY2());
            if (computerFiguresColor.equals(FigureColor.BLACK)) tempScore = tempBoard.calculateBoardValue();
            if (computerFiguresColor.equals(FigureColor.WHITE)) tempScore = 0 - tempBoard.calculateBoardValue();
            moveWithScore.add(new MoveWithScore(currentMove, tempScore));
        }
    }
}