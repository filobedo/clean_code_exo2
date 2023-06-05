import java.util.Scanner;
import java.util.Random;

public class Game {
    Random generator = new Random();
    Scanner scanner = new Scanner(System.in);
    private int userGuess;
    private int attempts;
    private int maxAttempts;
    private final int numberToGuess;
    private boolean continuePlaying;
    
    Game() {
        this.numberToGuess = generator.nextInt(100) + 1;
        this.continuePlaying = true;
    }
    
    private void startingMessage() {
        this.userGuess = 0;
        this.attempts = 0;
        this.maxAttempts = 10;
        System.out.println("-===========================-");
        System.out.println("=== GUESS THE NUMBER GAME ===");
        System.out.println("-===========================-");
    }

    private void restartGame() {
        System.out.println("----------------------------------------------------");
        System.out.println("Do you want to try again with a new number (yes/no)?");
        String userResponse = scanner.nextLine().trim().toLowerCase();
        this.continuePlaying = userResponse.equals("yes");
        if (this.continuePlaying) {
            this.userGuess = 0;
            this.attempts = 0;
        }
    }

    private boolean testGuessAndAttempts() {
        return this.numberToGuess != this.userGuess && this.attempts < this.maxAttempts;
    }

    private void getNumber() {
        while (testGuessAndAttempts()) {
            String input = scanner.nextLine();
            this.attempts++;

            try {
                this.userGuess = CheckIsNumber.isInt(input);
                if (this.userGuess == this.numberToGuess) {
                    System.out.println("You found it after " + this.attempts + " tries!");
                } else {
                    String divergence = this.userGuess < this.numberToGuess ? "smaller" : "greater";
                    System.out.println("Wrong! Your number is " + divergence + " than the correct one. " + (this.maxAttempts-this.attempts) + "/" + this.maxAttempts + " tries left");
                }
            } catch (NumberFormatException e) {
                System.out.println("Your input was '" + input + "', please enter a valid integer. " + (this.maxAttempts-this.attempts) + "/" + this.maxAttempts + " tries left");
            }
        }
    }

    public void play() {
        this.startingMessage();

        while(this.continuePlaying) {
            System.out.println("Guess the number (between 1 and 100)!");
//                System.out.println("debug : the expected number is " + number);

            getNumber();

            if (this.numberToGuess != this.userGuess) {
                System.out.println("You lose after " + this.maxAttempts + " tries, the expected number was " + this.numberToGuess);
            }

            restartGame();
        }
        scanner.close();
    }
}
