package domain;

import java.util.ArrayList;
import java.util.Random;

public class MastermindGame {
    ArrayList<Integer> secretCode;
    boolean gameOver;
    int numberOfGuesses;
    
    public MastermindGame() {
        this.gameOver = false;
        this.numberOfGuesses = 0;
        this.secretCode = new ArrayList<>();
        Random r = new Random();
        
        for (int i = 0; i < 4; i++) {
            this.secretCode.add(r.nextInt(6)); //Assumes game is played with 6 different numbers (colours)
        }
    }
    
    public boolean gameIsOver() {
        return this.gameOver;
    }
    
    public String compareCodes(String guess) {
        this.numberOfGuesses++;
        ArrayList<Integer> guessedCode = this.convertStringToCode(guess);
        
        if (this.secretCode.equals(guessedCode)) {
            this.gameOver = true;
            return "Congrats! You have solved the secret code! It took you " + this.numberOfGuesses + " guesses.";
        }  
        
        int correctNumbersAtCorrectIndexes = 0;
        int correctNumbersAtWrongIndexes = 0;
        
        for (int i = 0; i < 4; i++) {
            if (this.secretCode.get(i) == guessedCode.get(i)) {
                correctNumbersAtCorrectIndexes++;
            }
        }
        
        for (int i = 0; i < 4; i++) {
            if (guessedCode.contains(this.secretCode.get(i))) {
                guessedCode.remove(this.secretCode.get(i));
                correctNumbersAtWrongIndexes++;
            }
        }

        correctNumbersAtWrongIndexes = correctNumbersAtWrongIndexes - correctNumbersAtCorrectIndexes;
        return "Your guess contains " + correctNumbersAtCorrectIndexes + " correct numbers at their correct indexes and " + 
                correctNumbersAtWrongIndexes + " correct numbers at wrong indexes.";
    }
    
    public ArrayList<Integer> convertStringToCode(String guess) {
        ArrayList<Integer> convertedCode = new ArrayList<>();
        String[] inputAsStringArray = guess.split(",");
        
        for (int i = 0; i < inputAsStringArray.length; i++) {
            convertedCode.add(Integer.valueOf(inputAsStringArray[i]));
        }
        return convertedCode;
    }
    
    public void quit() {
        this.gameOver = true;
    }
    
    public ArrayList<Integer> gerSecretCode() {
        return this.secretCode;
    }
}
