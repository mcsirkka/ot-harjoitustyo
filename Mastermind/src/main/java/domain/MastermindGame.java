package domain;

import java.util.ArrayList;
import java.util.Random;

public class MastermindGame {
    ArrayList<Integer> secretCode;
    boolean codeIsSolved;
    int numberOfGuesses;
    
    public MastermindGame() {
        this.codeIsSolved = false;
        this.numberOfGuesses = 0;
        this.secretCode = new ArrayList<>();

        this.generateSecretCode();
    }
    
    public boolean codeIsSolved() {
        return this.codeIsSolved;
    }
    
    public ArrayList<Integer> compareCodes(ArrayList<Integer> guessedCode) {
        this.numberOfGuesses++;
        ArrayList<Integer> resultOfComparison = new ArrayList<>();
        
        if (this.secretCode.equals(guessedCode)) {
            this.codeIsSolved = true;
            for (int i = 1; i < 5; i++) {
                resultOfComparison.add(2);
            }
            return resultOfComparison;
        }  
        
        int correctNumbersAtCorrectIndexes = 0;
        int correctNumbersAtWrongIndexes = 0;
        
        for (int i = 0; i < 4; i++) {
            if (this.secretCode.get(i) == guessedCode.get(i)) {
                correctNumbersAtCorrectIndexes++;
                resultOfComparison.add(2);
            }
        }
        
        for (int i = 0; i < 4; i++) {
            if (guessedCode.contains(this.secretCode.get(i))) {
                guessedCode.remove(this.secretCode.get(i));
                correctNumbersAtWrongIndexes++;
            }
        }

        correctNumbersAtWrongIndexes = correctNumbersAtWrongIndexes - correctNumbersAtCorrectIndexes;
        
        for (int i = 1; i <= correctNumbersAtWrongIndexes; i++) {
            resultOfComparison.add(1);
        }
        while (resultOfComparison.size() < 4)  {
            resultOfComparison.add(0);
        }
        return resultOfComparison;
    }
    
    public ArrayList<Integer> getSecretCode() {
        return this.secretCode;
    }
    
    public int getNumberOfGuesses() {
        return this.numberOfGuesses;
    }

    public void setNumberOfGuesses(int numberOfGuesses) {
        this.numberOfGuesses = numberOfGuesses;
    }

    public void generateSecretCode() {
        this.secretCode.clear();
        Random r = new Random();
        
        for (int i = 0; i < 4; i++) {
            this.secretCode.add(r.nextInt(6)); //Assumes game is played with 6 different numbers (colours)
        }        
    }
    
    public void restart() {
        this.codeIsSolved = false;
        this.numberOfGuesses = 0;
        this.generateSecretCode();        
    }
}
