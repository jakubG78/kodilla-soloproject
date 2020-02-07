package com.kodilla.solo.checkers;

import java.util.ArrayList;

public class ChessBoard {
    private ArrayList<BoardRow> theBoard = new ArrayList<>();

    public void initBoard() {
        for (int n = 0; n < 8; n++) {
            if (n % 2 != 0)
                setFigure(0, n, new PawnFigure(FigureColor.WHITE));
        }
        for (int n = 0; n < 8; n++) {
            if (n % 2 == 0)
                setFigure(1, n, new PawnFigure(FigureColor.WHITE));
        }
        for (int n = 0; n < 8; n++) {
            if (n % 2 != 0)
                setFigure(2, n, new PawnFigure(FigureColor.WHITE));
        }
        for (int n = 0; n < 8; n++) {
            if (n % 2 == 0)
                setFigure(5, n, new PawnFigure(FigureColor.BLACK));
        }
        for (int n = 0; n < 8; n++) {
            if (n % 2 != 0)
                setFigure(6, n, new PawnFigure(FigureColor.BLACK));
        }
        for (int n = 0; n < 8; n++) {
            if (n % 2 == 0)
                setFigure(7, n, new PawnFigure(FigureColor.BLACK));
        }
    }

    public ChessBoard() {
        for (int n = 0; n < 8; n++) {
            theBoard.add(new BoardRow());
        }
    }

    Figure getFigure(int row, int col) {
        return theBoard.get(row).getTheRow().get(col);
    }

    void setFigure(int row, int col, Figure figure) {
        theBoard.get(row).getTheRow().set(col, figure);
    }

    @Override
    public String toString() {
        String result = "";
        for (int y = 0; y < 8; y++) {
            String s = "|";
            for (int x = 0; x < 8; x++) {
                Figure figure = getFigure(y, x);
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
}
