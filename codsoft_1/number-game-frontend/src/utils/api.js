const API_BASE_URL = "http://localhost:8080/game";

export const startGame = async () => {
    const response = await fetch(`${API_BASE_URL}/start`, { method: "POST" });
    return response.json();
};

export const makeGuess = async (guess) => {
    const response = await fetch(`${API_BASE_URL}/guess`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ guess }),
    });
    return response.json();
};