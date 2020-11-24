package ui;

import java.util.ArrayList;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CodePanel {
    private HBox panel;
    private ArrayList<Circle> circles;
    private ArrayList<Color> colours;
    private ArrayList<Integer> codeAsIntegers;
    
    public CodePanel() {
        this.panel = new HBox(5);
        this.circles = new ArrayList<>();
        this.colours = new ArrayList<>();
        
        this.colours.add(Color.PALEVIOLETRED);
        this.colours.add(Color.DARKSEAGREEN);
        this.colours.add(Color.ORANGE);
        this.colours.add(Color.SLATEBLUE);
        this.colours.add(Color.LIGHTPINK);
        this.colours.add(Color.WHEAT);

        this.codeAsIntegers = new ArrayList<>();
        
        for (int i = 1; i < 5; i++) {
            Circle circle = new Circle();
            circle.setFill(Color.WHITE);
            circle.setRadius(25.0);
            this.circles.add(circle);
            panel.getChildren().add(circle);
        }        
    }
    
    public HBox getPanel() {
        return this.panel;
    }

    public ArrayList<Circle> getCircles() {
        return circles;
    }
    
    public void setCircles(ArrayList<Integer> code) {
        for (int i = 0; i < this.circles.size(); i++) {
            this.circles.get(i).setFill(this.colours.get(code.get(i)));
        }
    }

    public ArrayList<Color> getColours() {
        return colours;
    } 
    
    public ArrayList<Integer> getCodeAsIntegers() {
        this.codeAsIntegers.clear();
        for (Circle circle: this.circles) {
            int colour = this.colours.indexOf(circle.getFill());
            this.codeAsIntegers.add(colour);
        }
        return this.codeAsIntegers;
    }    
}
