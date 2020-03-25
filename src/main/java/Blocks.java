import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Blocks extends Circle {

    private int x;
    private int y;

    public Blocks(int size){
        super(size / Math.PI, Color.RED);
        super.setStroke(Color.RED);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int[] drawBlock(Grid grid, int [] coords){
        this.x = coords[0];
        this.y = coords[1];

        grid.getCell(coords[0],coords[1]).getChildren().add(this);
        grid.setAlignment(Pos.CENTER);

        int [] coor = new int [2];
        coor[0] = coords[0];
        coor[1] = coords[1];

        return coor;
    }
}
