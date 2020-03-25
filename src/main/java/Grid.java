import javafx.scene.control.Alert;
import javafx.scene.layout.*;

import java.awt.*;

public class Grid extends GridPane {
    //create and make use of nested classes
    class Cell extends StackPane{
        private final int size;


        public Cell(int s){
            this.size = s;
            this.setStyle("-fx-border-color: black;");
            this.setPrefSize(size, size);
        }
    }

    private Cell [][] cells;

    public Grid(int n, int dimension){
        cells = new Cell[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                cells[i][j] = new Cell(dimension);
                this.add(cells[i][j], i, j);
            }
        }
    }

    public Cell getCell(int row, int column){
        for(int i = 0; i < cells.length; i++){
            for(int j = 0; j < cells[0].length; j++){
                if( i == row && j == column)
                    return cells[i][j];
            }
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("OOPS");
        alert.setHeaderText("You have gone rogue !" + "\n" +
                "You were supposed to stay inside the grid" + "\n" +
                "Please restart the game and try again");

        alert.showAndWait();
        throw new IndexOutOfBoundsException("Cell not found");
    }

    public int getSize(){
        return cells.length;
    }

}
