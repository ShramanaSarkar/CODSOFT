package com.task.number_game_backend.controller;

import com.task.number_game_backend.model.GameSession;
import com.task.number_game_backend.model.GuessRequest;
import com.task.number_game_backend.model.GuessResponse;
import com.task.number_game_backend.service.GameService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
@CrossOrigin(origins = "http://localhost:3000")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
        this.gameService.startNewGame();
    }

    @PostMapping("/start")
    public GameSession startGame() {
        gameService.startNewGame();
        return gameService.getGameSession();
    }

    @PostMapping("/guess")
    public GuessResponse makeGuess(@RequestBody GuessRequest guessRequest) {
        return gameService.checkGuess(guessRequest);
    }
}