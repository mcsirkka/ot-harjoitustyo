package ui;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.layout.CornerRadii;
import java.util.ArrayList;

import domain.MastermindGame;

public class Ui extends Application {
    private MastermindGame game;
    
    @Override
    public void init() {
        this.game = new MastermindGame();
    }
    
    @Override
    public void start(Stage stage) {
        stage.setTitle("Mastermind");
        
        //Set up the game layout
        
        GridPane gameLayout = new GridPane();
        gameLayout.setPrefSize(360, 790);
        gameLayout.setVgap(10.0);
        gameLayout.setHgap(10.0);
        gameLayout.setPadding(new Insets(10, 10, 10, 10));
        BackgroundFill fill1 = new BackgroundFill(Color.LINEN, new CornerRadii(Math.PI/2), new Insets(0));
        Background gameBackground = new Background(fill1);
        gameLayout.setBackground(gameBackground);
        
        InputPanel inputPanel = new InputPanel();
        CodePanel secretCode = new CodePanel();        
        gameLayout.add(secretCode.getPanel(), 1, 0);
        gameLayout.add(inputPanel.getPanel(), 1, 12);        
        
        //Set up the panels that will hold the user guesses. Initialise as all white i.e. empty
        
        ArrayList<CodePanel> guesses = new ArrayList<>();
        for (int i = 1; i < 12; i++) {
            CodePanel guess = new CodePanel();
            guesses.add(guess);
            gameLayout.add(guess.getPanel(), 1, i);
        }
        
        //Create a submit button, that compares the guess to the secret code and adds the guess to the board
        
        Button submitButton = new Button("Submit");
        BackgroundFill fill2 = new BackgroundFill(Color.NAVAJOWHITE, new CornerRadii(Math.PI/2), new Insets(0));
        Background buttonBackground = new Background(fill2);
        submitButton.setBackground(buttonBackground);
        
        submitButton.setOnAction((event) -> {
            guesses.get(this.game.getNumberOfGuesses()).setCircles(inputPanel.getCodeAsIntegers());
            ArrayList<Integer> resultOfComparison = this.game.compareCodes(inputPanel.getCodeAsIntegers());
            gameLayout.add(createResultPanel(resultOfComparison), 0, this.game.getNumberOfGuesses());
            if (this.game.codeIsSolved() || this.game.getNumberOfGuesses() == 11) {
                Scene gameOverScene = new Scene(gameOverLayout());
                stage.setScene(gameOverScene);
            }
        });
        
        gameLayout.add(submitButton, 2, 12);
        
        Scene gameScene = new Scene(gameLayout);  

        stage.setScene(gameScene);
        
        stage.show();
    }
    
    public GridPane createResultPanel(ArrayList<Integer> result) {
        GridPane resultLayout = new GridPane();
        resultLayout.setVgap(3);
        resultLayout.setHgap(3);
        resultLayout.setPadding(new Insets(15, 3, 3, 3));
        int k = 0;

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                Circle circle = new Circle();
                circle.setRadius(5.0);
                
                if (result.get(k) == 2) {
                    circle.setFill(Color.DIMGRAY);
                } else if (result.get(k) == 1) {
                    circle.setFill(Color.WHITE);
                } else {
                    circle.setFill(Color.ANTIQUEWHITE);
                }
                k++;
                resultLayout.add(circle, j, i);
            }
        }
        return resultLayout;
    }    
    
    public VBox gameOverLayout() {
        VBox gameOverLayout = new VBox(20);
        gameOverLayout.setPrefSize(360, 790);
        gameOverLayout.setPadding(new Insets(30, 30, 30, 30));
        BackgroundFill fill1 = new BackgroundFill(Color.LINEN, new CornerRadii(Math.PI/2), new Insets(0));
        Background gameBackground = new Background(fill1);
        gameOverLayout.setBackground(gameBackground);
        
        Text endText = new Text();
        if (this.game.codeIsSolved()) {
            endText.setText("Congratulations!\nYou have solved the code – it took you " +
                    this.game.getNumberOfGuesses() + " guesses.");
        } else {
            endText.setText("Game over :( You did not manage to solve the code.\nBetter luck next time!");
        }
//        Button playAgainButton = new Button("Play again"); //Add the button function
//        BackgroundFill fill2 = new BackgroundFill(Color.NAVAJOWHITE, new CornerRadii(Math.PI/2), new Insets(0));
//        Background buttonBackground = new Background(fill2);   
//        playAgainButton.setBackground(buttonBackground);
//        playAgainButton.setOnAction((event) -> {
//            
//        });
        gameOverLayout.getChildren().add(endText);
//        gameOverLayout.getChildren().add(playAgainButton);
        return gameOverLayout;
    }    
    
    public static void main(String[] args) {
        launch(Ui.class);
    }
    
}
