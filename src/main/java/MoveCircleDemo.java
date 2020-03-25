import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Random;

public class MoveCircleDemo extends Application
{
    private final int N = 25;
    private final int GRID_SIZE = 800;
    private final int DIM = (int) Math.sqrt(GRID_SIZE  * GRID_SIZE/ (N * N));

    private CircleMarker marker;
    private Grid grid;

    private Blocks block;
    private int[][] arr;

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        grid = new Grid(N, DIM);

        MyCodeCircleMaker();

        //"Set up how many obstacles we want to have"
        arr = new int [40][2];

        for(int i = 0; i < 40; i++){
            int[] temp = Block();
            arr[i][0] = temp[0];
            arr[i][1] = temp[1];
        }

        //Print all the locations where i have obstacles so
        // i would know when the green circle hits one of them
        System.out.println(Arrays.deepToString(arr));

        InputHBox hBox = new InputHBox();

        BorderPane borderPane = setupBorderPane(hBox);
        Scene scene = new Scene(borderPane);
        primaryStage.setTitle("Move the Circle");
        primaryStage.setScene(scene);
        primaryStage.show();

        hBox.addMoveButtonAction(grid, marker, arr);
        hBox.addRulesAction();

    }

    private int[] Block(){
        block = new Blocks(DIM);
        int [] arr = new int[2];
        //initiate the values to have the random blocks
        //other than the starting and ending point
        int a = 1 + (int)(Math.random() * ((23 - 1) + 1));
        int b = 1 + (int)(Math.random() * ((23 - 1) + 1));

        arr[0] = a;
        arr[1] = b;

        return block.drawBlock(grid, arr);

    }

    private int []  MyCodeCircleMaker() {
        marker = new CircleMarker(DIM);

        int [] arr = new int[2];
        arr[0] = 0;
        arr[1] = 0;
        marker.drawCircle(grid, arr);
        return arr;
    }

    private BorderPane setupBorderPane(HBox hBox) {
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(20, 20, 20, 20));
        borderPane.setCenter(grid);
        borderPane.setBottom(hBox);
        borderPane.setAlignment(hBox, Pos.CENTER);
        borderPane.setAlignment(grid, Pos.CENTER);
        return borderPane;
    }
}
