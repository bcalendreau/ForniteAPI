package com.brunocalendreau.model.restmodel;

public class PlayerOnBoard {

    private String name;
    private final int score;
    private int rank;

    PlayerOnBoard(String name, int score, int rank){
        this.name = name;
        this.score = score;
        this.rank = rank;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public int getRank() {
        return rank;
    }
}
