package ui;

import domain.MastermindGame;

import java.util.Scanner;

public class Ui {
    private MastermindGame game;
    private Scanner scanner;
    
    public Ui(MastermindGame game, Scanner scanner) {
        this.game = game;
        this.scanner = scanner;
    }
    
    public void start() {
        System.out.println("Welcome! Your goal is to guess the super secret code containing four digits from 0 to 5.\n");
        
        while (!(this.game.gameIsOver())) {
            System.out.print("Enter your guess as four comma separated integers (to quit press q): ");
            String userGuess = this.scanner.nextLine();
            
            if (userGuess.equals("q")) {
                System.out.println("\nGame over!");
                System.out.println("The secret code was " + this.game.gerSecretCode());
                break;
            }
            
            System.out.println(this.game.compareCodes(userGuess));
            System.out.println("");
        }
    }
    
    public static void main(String[] args) {
        MastermindGame game = new MastermindGame();
        Scanner scanner = new Scanner(System.in);
        Ui userInterface = new Ui(game, scanner);

        userInterface.start();
    }
    
}
