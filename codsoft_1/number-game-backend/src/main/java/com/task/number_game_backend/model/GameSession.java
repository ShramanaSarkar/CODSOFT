package com.task.number_game_backend.model;

public class GameSession {

    private int targetNumber;
    private int attemptsLeft;
    private int score;

    public GameSession(int targetNumber, int maxAttempts) {
        this.targetNumber = targetNumber;
        this.attemptsLeft = maxAttempts;
        this.score = 0;
    }

    public int getTargetNumber() { return targetNumber; }
    public int getAttemptsLeft() { return attemptsLeft; }
    public int getScore() { return score; }

    public void decreaseAttempts() { attemptsLeft--; }
    public void increaseScore(int points) { score += points; }

    public void setScore(int score) {
        this.score = score;
    }
}
