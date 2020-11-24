package ui;

import javafx.scene.shape.Circle;

public class InputPanel extends CodePanel {
    
    public InputPanel() {
        super();
        
        for (Circle circle: super.getCircles()) {
            
            circle.setFill(super.getColours().get(0));
            
            circle.setOnMouseClicked((event) -> {
                int index = super.getColours().indexOf(circle.getFill());
                if (index == 5) {
                    index = 0;
                } else {
                    index++;
                }
                circle.setFill(super.getColours().get(index));
            });            
        }  
    }    
}
