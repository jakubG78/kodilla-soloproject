package com.kodilla.solo.checkers;

public class MoveWithScore {
    private Move move;
    private Integer score;

    public MoveWithScore(Move move, Integer score) {
        this.move = move;
        this.score = score;
    }

    public Integer getScore() {
        return score;
    }

    public Move getMove() {
        return move;
    }
}
