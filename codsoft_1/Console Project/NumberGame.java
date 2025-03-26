import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    private static final int MIN_RANGE = 1;
    private static final int MAX_RANGE = 100;
    private static final int MAX_ATTEMPTS = 5;
    private static int score = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            playGame(scanner);
            System.out.println("Do you want to play again? (yes/no): ");
            String response = scanner.next().toLowerCase();
            playAgain = response.equals("yes");
        }

        System.out.println("Thanks for playing! Your final score: " + score);
        scanner.close();
    }

    private static void playGame(Scanner scanner) {
        Random random = new Random();
        int targetNumber = random.nextInt(MAX_RANGE - MIN_RANGE + 1) + MIN_RANGE;
        System.out.println("Generated Number: " + targetNumber);
        int attemptsLeft = MAX_ATTEMPTS;
        int roundScore = 0;

        System.out.println("A random number between " + MIN_RANGE + " and " + MAX_RANGE + " has been generated.");
        System.out.println("Try to guess it! You have " + MAX_ATTEMPTS + " attempts.");

        while (attemptsLeft > 0) {
            System.out.print("Enter your guess: ");
            int userGuess = scanner.nextInt();

            if (userGuess == targetNumber) {
                System.out.println("Congratulations! You guessed the number correctly.");
                roundScore = attemptsLeft * 10;  // Score based on remaining attempts
                break;
            } else if (userGuess > targetNumber) {
                System.out.println("Too high! Try again.");
            } else {
                System.out.println("Too low! Try again.");
            }

            attemptsLeft--;
            System.out.println("Attempts left: " + attemptsLeft);
            System.out.println("----------------------------------");
        }

        if (attemptsLeft == 0) {
            System.out.println("You've run out of attempts! The correct number was: " + targetNumber);
        }

        score += roundScore;
        System.out.println("Your score for this round: " + roundScore);
        System.out.println("Total Score: " + score);
        System.out.println("==================================");
    }
}