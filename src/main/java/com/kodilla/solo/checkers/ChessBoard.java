package com.kodilla.solo.checkers;

import java.util.ArrayList;

import static java.lang.Math.abs;

public class ChessBoard {
    private ArrayList<BoardRow> theBoard = new ArrayList<>();
    private FigureColor whoseMoveIsIt = FigureColor.WHITE;

    public ChessBoard() {
        for (int n = 0; n < 8; n++) {
            theBoard.add(new BoardRow());
        }
    }

    public ChessBoard(ChessBoard board) {
        for (int n = 0; n < 8; n++) {
            theBoard.add(new BoardRow());
        }
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                Figure tempFigure = board.getFigure(x, y);
                this.setFigure(x, y, tempFigure);
            }
        }
    }

    public FigureColor getWhoseMoveIsIt() {
        return whoseMoveIsIt;
    }

    public void initBoard() {
        for (int n = 0; n < 8; n++) {
            if (n % 2 != 0)
                setFigure(n, 0, new PawnFigure(FigureColor.BLACK));
        }
        for (int n = 0; n < 8; n++) {
            if (n % 2 == 0)
                setFigure(n, 1, new PawnFigure(FigureColor.BLACK));
        }
        for (int n = 0; n < 8; n++) {
            if (n % 2 != 0)
                setFigure(n, 2, new PawnFigure(FigureColor.BLACK));
        }
        for (int n = 0; n < 8; n++) {
            if (n % 2 == 0)
                setFigure(n, 5, new PawnFigure(FigureColor.WHITE));
        }
        for (int n = 0; n < 8; n++) {
            if (n % 2 != 0)
                setFigure(n, 6, new PawnFigure(FigureColor.WHITE));
        }
        for (int n = 0; n < 8; n++) {
            if (n % 2 == 0)
                setFigure(n, 7, new PawnFigure(FigureColor.WHITE));
        }
    }

    Figure getFigure(int col, int row) {
        return theBoard.get(row).getTheRow().get(col);
    }

    void setFigure(int col, int row, Figure figure) {
        theBoard.get(row).getTheRow().set(col, figure);
    }

    @Override
    public String toString() {
        String result = "";
        for (int y = 0; y < 8; y++) {
            String s = "|";
            for (int x = 0; x < 8; x++) {
                Figure figure = getFigure(x, y);
                if (figure.getColor() == FigureColor.BLACK)
                    s += "b";
                else if (figure.getColor() == FigureColor.WHITE)
                    s += "w";
                else
                    s += " ";
                if (figure instanceof PawnFigure)
                    s += "P";
                else if (figure instanceof QueenFigure)
                    s += "Q";
                else
                    s += " ";
                s += "|";
            }
            result = result + s + "\n";
        }
        return result;
    }

    public void move(int x1, int y1, int x2, int y2) {
        if (isMoveValid(x1, y1, x2, y2)) {
            Figure figure = getFigure(x1, y1);
            setFigure(x2, y2, figure);
            checkPawnPromotion(x2, y2);
            setFigure(x1, y1, new NoneFigure());
            changeActiveSide();
        } else if (isMoveValidWithHit(x1, y1, x2, y2)) {
            Figure figure = getFigure(x1, y1);
            setFigure(x2, y2, figure);
            checkPawnPromotion(x2, y2);
            setFigure(x1, y1, new NoneFigure());
            removeHitFigure(x1, y1, x2, y2);
            changeActiveSide();
        }
    }

    public boolean isMoveValid(int x1, int y1, int x2, int y2) {
        boolean result = true;
        result = result && isMoveToEmptyField(x2, y2);
        result = result && isPlayerCorrect(x1, y1);
        if (getFigure(x1, y1) instanceof PawnFigure) {
            result = result && isMoveInGoodDirection(x1, y1, x2, y2);
            result = result && isMoveOneBoxDiagonal(x1, y1, x2, y2);
        } else {
            if (getFigure(x1, y1) instanceof QueenFigure) {
                result = result && isMoveDiagonal(x1, y1, x2, y2);
                result = result && isMovePathOpen(x1, y1, x2, y2);
            } else {
                result = false;
            }
        }
        return result;
    }

    private boolean isMoveToEmptyField(int x2, int y2) {
        if (getFigure(x2, y2) instanceof NoneFigure) return true;
        else return false;
    }

    private boolean isPlayerCorrect(int x1, int y1) {
        return getFigure(x1, y1).getColor().equals(whoseMoveIsIt);
    }

    private boolean isMoveInGoodDirection(int x1, int y1, int x2, int y2) {
        if (getFigure(x1, y1).getColor().equals(FigureColor.BLACK) && (getFigure(x1, y1) instanceof PawnFigure)) {
            if (y1 < y2) return true;
        } else {
            if (getFigure(x1, y1).getColor().equals(FigureColor.WHITE) && (getFigure(x1, y1) instanceof PawnFigure)) {
                if (y1 > y2) return true;
            }
        }
        return false;
    }

    private boolean isMoveOneBoxDiagonal(int x1, int y1, int x2, int y2) {
        if (abs(x1 - x2) == 1 && abs(y1 - y2) == 1) return true;
        else return false;
    }

    private boolean isMoveDiagonal(int x1, int y1, int x2, int y2) {
        if ((abs(x1 - x2) == abs(y1 - y2)) && (abs(x1 - x2) != 0)) return true;
        else return false;
    }

    private boolean isMovePathOpen(int x1, int y1, int x2, int y2) {
        int dX = (x2 - x1 > 0) ? 1 : -1;
        int dY = (y2 - y1 > 0) ? 1 : -1;
        int yTemp = y1;
        boolean result = true;
        for (int xTemp = x1 + dX; xTemp != x2; xTemp += dX) {
            yTemp = yTemp + dY;
            result = result && (getFigure(xTemp, yTemp) instanceof NoneFigure);
        }
        return result;
    }

    private void checkPawnPromotion(int x2, int y2) {
        if (getFigure(x2, y2) instanceof PawnFigure) {
            if (getFigure(x2, y2).getColor().equals(FigureColor.BLACK) && y2 == 7) {
                setFigure(x2, y2, new QueenFigure(FigureColor.BLACK));
            } else {
                if (getFigure(x2, y2).getColor().equals(FigureColor.WHITE) && y2 == 0)
                    setFigure(x2, y2, new QueenFigure(FigureColor.WHITE));
            }
        }
    }

    public boolean isMoveValidWithHit(int x1, int y1, int x2, int y2) {
        boolean result = true;
        result = result && isMoveToEmptyField(x2, y2);
        result = result && isPlayerCorrect(x1, y1);
        if (getFigure(x1, y1) instanceof PawnFigure) {
            result = result && isThereValidFigureToHit(x1, y1, x2, y2);
            result = result && isMoveDiagonal(x1, y1, x2, y2);
        } else {
            if (getFigure(x1, y1) instanceof QueenFigure) {
                result = result && isMoveDiagonal(x1, y1, x2, y2);
                result = result && isHitPathOpen(x1, y1, x2, y2);
                result = result && isThereValidFigureToHit(x1, y1, x2, y2);
            } else {
                result = false;
            }
        }
        return result;
    }

    private boolean isThereValidFigureToHit(int x1, int y1, int x2, int y2) {
        int dX = (x2 - x1 > 0) ? 1 : -1;
        int dY = (y2 - y1 > 0) ? 1 : -1;
        Figure hitFigure;
        if(getFigure(x1,y1) instanceof PawnFigure){
            if (x2 - dX > 0 && x2 - dX < 8 && y2 - dY > 0 && y2 - dY < 8 && abs(x1 - x2) == 2 && abs(y1 - y2) == 2) hitFigure = getFigure(x2 - dX, y2 - dY);
            else hitFigure = new Figure(FigureColor.NONE);
        } else {
            if (x2 - dX > 0 && x2 - dX < 8 && y2 - dY > 0 && y2 - dY < 8) hitFigure = getFigure(x2 - dX, y2 - dY);
            else hitFigure = new Figure(FigureColor.NONE);
        }
        if (getFigure(x1, y1).getColor().equals(FigureColor.BLACK) && hitFigure.getColor().equals(FigureColor.WHITE)) {
            return true;
        } else {
            if (getFigure(x1, y1).getColor().equals(FigureColor.WHITE) && hitFigure.getColor().equals(FigureColor.BLACK))
                return true;
        }
        return false;
    }

    private void removeHitFigure(int x1, int y1, int x2, int y2) {
        int dX = (x2 - x1 > 0) ? 1 : -1;
        int dY = (y2 - y1 > 0) ? 1 : -1;
        setFigure(x2 - dX, y2 - dY, new NoneFigure());
    }

    private boolean isHitPathOpen(int x1, int y1, int x2, int y2) {
        boolean result = true;
        int dX = (x2 - x1 > 0) ? 1 : -1;
        int dY = (y2 - y1 > 0) ? 1 : -1;
        int yTemp = y1;
        for (int xTemp = x1 + dX; xTemp != x2 - dX; xTemp += dX) {
            yTemp = yTemp + dY;
            result = result && (getFigure(xTemp, yTemp) instanceof NoneFigure);
        }
        return result;
    }

    private void changeActiveSide() {
        if (whoseMoveIsIt == FigureColor.WHITE) whoseMoveIsIt = FigureColor.BLACK;
        else whoseMoveIsIt = FigureColor.WHITE;
        System.out.println("It is now " + whoseMoveIsIt + " turn to move!");
    }

    public int calculateBoardValue() {
        int score = 0;
        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                Figure figure = getFigure(x, y);
                if (figure.getColor() == FigureColor.BLACK && figure instanceof PawnFigure)
                    score = score + y + 1;
                else if (figure.getColor() == FigureColor.BLACK && figure instanceof QueenFigure)
                    score = score + 50;
                else if (figure.getColor() == FigureColor.WHITE && figure instanceof PawnFigure)
                    score = score - abs(7 - y);
                else if (figure.getColor() == FigureColor.WHITE && figure instanceof QueenFigure)
                    score = score - 50;
            }
        }
        return score;
    }
}
