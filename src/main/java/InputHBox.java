import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.shape.Circle;
import org.w3c.dom.ls.LSOutput;

import java.util.Arrays;

public class InputHBox extends HBox
{
    private Label directionLabel;
    private Label spacesLabel;
    private TextField directionText;
    private TextField spacesText;
    private Button btnMove;
    private Button rules;

    public InputHBox() {
        setSpacing(10);
        setAlignment(Pos.CENTER);
        setPadding(new Insets(10, 0, 0, 10));
        createAndAddChildren();
    }

    private void createLabels() {
        this.directionLabel = new Label("Direction (U/D/L/R)");
        this.spacesLabel = new Label("Number of Spaces");
    }

    private void createTextFields() {
        this.directionText = new TextField();
        this.spacesText = new TextField();
        this.directionText.setPrefWidth(30);
        this.spacesText.setPrefWidth(50);

    }

    private void createMoveButton() {
        this.btnMove = new Button("Move");
    }


    private void createRulesButton() {
        this.rules = new Button("Rules");
    }

    private void createAndAddChildren() {
        createLabels();
        createTextFields();
        createMoveButton();
        createRulesButton();
        getChildren().addAll(this.directionLabel, this.directionText,
                             this.spacesLabel, this.spacesText, this.btnMove , this.rules);
    }

    public void addRulesAction(){
        this.rules.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("RULES");
                alert.setHeaderText("Hello!" +
                        "\n" +
                        "You have to move the green circle location on the grid avoiding the red circles - obstacles. " +
                        "\n" +
                        "The end of the race is when you make it to the cell of index 24/24 - most bottom right ");

                alert.showAndWait();

            }
        });
    }

    public void addMoveButtonAction( Grid grid, CircleMarker circleMarker, int[][] arr){
        this.btnMove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            try {
                String myString = directionText.getText();

                if (myString.length() != 1)
                    throw new IllegalArgumentException("Wrong input");

                int numSpaces = Integer.valueOf(spacesText.getText());
                char myChar = myString.charAt(0);
                int[] myNewCoordinates =
                           Direction.getNewCoordinatesForDirection(myChar, circleMarker, numSpaces);

                if (myString.length() != 1)
                       throw new IllegalArgumentException("Wrong input");

                boolean check = false;
                for(int i = 0; i < arr.length; i++){
                        if(arr[i][0] == myNewCoordinates[0] && arr[i][1] == myNewCoordinates[1]){
                            check = true;
                        }
                }

                if(myNewCoordinates[0] == 24 && myNewCoordinates[1] == 24){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("CONGRATULATIONS");
                    alert.setHeaderText("You have made it to the destination " + "\n" +
                            "You were able to find a clear path through the obstacles");

                    alert.showAndWait();
                }


                if(!check)
                    circleMarker.updateLocation(grid, myNewCoordinates);
                else{
                      Alert alert = new Alert(Alert.AlertType.INFORMATION);
                      alert.setTitle("OOPS");
                      alert.setHeaderText("You cant go this way. Try again on a different route!");
                      alert.showAndWait();
                  }

               }catch (IllegalArgumentException exception){
                    System.out.println("Exception");
               }
               finally {
                   directionText.setText("");
                   spacesText.setText("");
               }
            }
        });
    }



}
