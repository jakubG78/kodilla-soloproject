package com.kodilla.solo.checkers;

import java.util.ArrayList;

public class BoardRow {
    private ArrayList<Figure> theRow = new ArrayList<>();

    public ArrayList<Figure> getTheRow() {
        return theRow;
    }

    public BoardRow() {
        for (int n = 0; n < 8; n++) {
            theRow.add(new NoneFigure());
        }
    }
}
