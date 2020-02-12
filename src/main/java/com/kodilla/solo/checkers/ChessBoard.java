package com.kodilla.solo.checkers;

import java.util.ArrayList;

public class ChessBoard {
    private ArrayList<BoardRow> theBoard = new ArrayList<>();

    public void initBoard() {
        for (int n = 0; n < 8; n++) {
            if (n % 2 != 0)
                setFigure(n, 0, new PawnFigure(FigureColor.WHITE));
        }
        for (int n = 0; n < 8; n++) {
            if (n % 2 == 0)
                setFigure(n, 1, new PawnFigure(FigureColor.WHITE));
        }
        for (int n = 0; n < 8; n++) {
            if (n % 2 != 0)
                setFigure(n, 2, new PawnFigure(FigureColor.WHITE));
        }
        for (int n = 0; n < 8; n++) {
            if (n % 2 == 0)
                setFigure(n, 5, new PawnFigure(FigureColor.BLACK));
        }
        for (int n = 0; n < 8; n++) {
            if (n % 2 != 0)
                setFigure(n, 6, new PawnFigure(FigureColor.BLACK));
        }
        for (int n = 0; n < 8; n++) {
            if (n % 2 == 0)
                setFigure(n, 7, new PawnFigure(FigureColor.BLACK));
        }
    }

    public ChessBoard() {
        for (int n = 0; n < 8; n++) {
            theBoard.add(new BoardRow());
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
            setFigure(x1, y1, new NoneFigure());
        }
    }

    private boolean isMoveValid(int x1, int y1, int x2, int y2) {
        // uzupełnić logikę ruchu pionu bez bicia
        if (x1 != x2 && y1 != y2)
            return true;
        else
            return false;
    }
}
