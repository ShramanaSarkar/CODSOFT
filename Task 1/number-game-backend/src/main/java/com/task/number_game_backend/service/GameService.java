package com.task.number_game_backend.service;

import com.task.number_game_backend.model.GameSession;
import com.task.number_game_backend.model.GuessRequest;
import com.task.number_game_backend.model.GuessResponse;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class GameService {
    private GameSession gameSession;
    private static final int MAX_ATTEMPTS = 5;
    private static final int RANGE_MIN = 1, RANGE_MAX = 100;

    public void startNewGame() {
        Random random = new Random();
        int randomNumber = random.nextInt(RANGE_MAX - RANGE_MIN + 1) + RANGE_MIN;
        gameSession = new GameSession(randomNumber, MAX_ATTEMPTS);
    }

    public GameSession getGameSession() {
        return gameSession;
    }

    public GuessResponse checkGuess(GuessRequest userGuess) {
        int guess = userGuess.getGuess();
        if (gameSession == null) {
            return new GuessResponse("Game has not started yet!", gameSession.getAttemptsLeft(), gameSession.getScore(), true);
        }

        if (gameSession.getAttemptsLeft() <= 0) {
            return new GuessResponse("No attempts left! Start a new game.", gameSession.getAttemptsLeft(), gameSession.getScore(), true);
        }

        gameSession.decreaseAttempts();
        int correctNumber = gameSession.getTargetNumber();

        if (guess == correctNumber) {
            int score = calculateScore(gameSession.getAttemptsLeft());
            gameSession.setScore(score);
            return new GuessResponse("Correct! You won!", gameSession.getAttemptsLeft(), gameSession.getScore(), true);
        } else if (guess < correctNumber) {
            return new GuessResponse("Too low!", gameSession.getAttemptsLeft(), gameSession.getScore(), false);
        } else {
            return new GuessResponse("Too high!", gameSession.getAttemptsLeft(), gameSession.getScore(), false);
        }
    }

    private int calculateScore(int attemptsLeft) {
        switch (attemptsLeft) {
            case 4: return 50;
            case 3: return 40;
            case 2: return 30;
            case 1: return 20;
            case 0: return 10;
            default: return 0;
        }
    }
}