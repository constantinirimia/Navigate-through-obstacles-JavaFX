import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CircleMarker extends Circle {

    private int x;
    private int y;

    public CircleMarker(int size){
        super(size / Math.PI, Color.GREEN);
        super.setStroke(Color.GREEN);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void drawCircle(Grid grid, int [] coords){
        this.x = coords[0];
        this.y = coords[1];

        grid.getCell(coords[0],coords[1]).getChildren().add(this);
        grid.setAlignment(Pos.CENTER);
    }

    public void updateLocation(Grid grid, int [] coordinates){
        int x = grid.getSize();
        int y = grid.getSize();

        if(coordinates[0] <= x && coordinates[1] <= y) {
            drawCircle(grid, coordinates);
        }

    }

}
