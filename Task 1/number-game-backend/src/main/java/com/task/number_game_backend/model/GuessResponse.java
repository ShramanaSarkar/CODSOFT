package com.task.number_game_backend.model;

public class GuessResponse {

    private String message;
    private int attemptsLeft;
    private int score;
    private boolean gameWon;

    public GuessResponse(String message, int attemptsLeft, int score, boolean gameWon) {
        this.message = message;
        this.attemptsLeft = attemptsLeft;
        this.score = score;
        this.gameWon = gameWon;
    }

    public String getMessage() { return message; }
    public int getAttemptsLeft() { return attemptsLeft; }
    public int getScore() { return score; }
    public boolean isGameWon() { return gameWon; }
}
