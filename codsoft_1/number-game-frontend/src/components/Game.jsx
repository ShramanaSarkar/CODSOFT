import React, { useState, useEffect } from "react";
import { startGame, makeGuess } from "../utils/api";
import { Card, CardContent, Typography, TextField, Button, Box } from "@mui/material";

const Game = () => {
    const [guess, setGuess] = useState("");
    const [message, setMessage] = useState("");
    const [attemptsLeft, setAttemptsLeft] = useState(0);
    const [score, setScore] = useState(0);
    const [gameWon, setGameWon] = useState(false);

    useEffect(() => {
        handleStartGame(true); // Initialize game on first render
    }, []);

    const handleStartGame = async (isFirstGame = false) => {
        const data = await startGame();
        setAttemptsLeft(data.attemptsLeft);
        setMessage("Guess a number between 1 and 100!");
        setGameWon(false);
        setGuess("");

        if (isFirstGame) {
            setScore(data.score); // Set score only on first game start
        }
    };

    const handleGuess = async () => {
        if (!guess) return;
        const data = await makeGuess(parseInt(guess, 10));

        setMessage(data.message);
        setAttemptsLeft(data.attemptsLeft);
        setGameWon(data.gameWon);

        if (data.gameWon) {
            setScore(prevScore => prevScore + data.score);
            alert("You won! Starting a new round...");
            handleStartGame();
        } else if (data.attemptsLeft === 0) {
            alert("Game Over! Restarting...");
            setScore(0);
            handleStartGame();
        }
    };

    return (
        <Box display="flex" justifyContent="center" alignItems="center" minHeight="100vh">
            <Card sx={{ maxWidth: 400, p: 3, boxShadow: 3, textAlign: "center" }}>
                <CardContent>
                    <Typography variant="h5" gutterBottom>
                        Number Game
                    </Typography>
                    <Typography variant="body1" color="textSecondary" gutterBottom>
                        {message}
                    </Typography>
                    <TextField
                        label="Enter your guess"
                        type="number"
                        inputProps={{ min: 1, max: 100 }}
                        value={guess}
                        onChange={(e) => {
                            let value = parseInt(e.target.value, 10);
                            if (value < 1) value = 1;
                            if (value > 100) value = 100;
                            setGuess(value);
                        }}
                        fullWidth
                        margin="normal"
                        disabled={gameWon || attemptsLeft === 0}
                    />
                    <Button
                        variant="contained"
                        color="primary"
                        fullWidth
                        sx={{ mt: 2 }}
                        onClick={handleGuess}
                        disabled={gameWon || attemptsLeft === 0}
                    >
                        Submit Guess
                    </Button>
                    <Button
                        variant="outlined"
                        color="secondary"
                        fullWidth
                        sx={{ mt: 2 }}
                        onClick={() => handleStartGame(false)}
                    >
                        Restart Game
                    </Button>
                    <Typography variant="body2" sx={{ mt: 2 }}>
                        <strong>Attempts Left:</strong> {attemptsLeft}
                    </Typography>
                    <Typography variant="body2">
                        <strong>Score:</strong> {score}
                    </Typography>
                </CardContent>
            </Card>
        </Box>
    );
};

export default Game;