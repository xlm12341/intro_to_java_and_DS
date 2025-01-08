import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class EightQueens extends Application {
    public static final int SIZE = 8;
    //queens are placed at (i, queens[i])
    private int[] queens = {-1, -1, -1, -1, -1, -1, -1, -1,};

    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage stage) throws Exception {
        search();

        GridPane chessBoard = new GridPane();
        chessBoard.setAlignment(Pos.CENTER);
        Label[][] labels = new Label[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++) {
                chessBoard.add(labels[i][j] = new Label(), j, i);
                labels[i][j].setStyle("-fx-border-color: black");
                labels[i][j].setPrefSize(55, 55);
            }
        Image image = new Image("img/queen.jpg");
        for (int i= 0; i < SIZE; i++){
            labels[i][queens[i]].setGraphic(new ImageView(image));


        }

        Scene scene = new Scene(chessBoard, 55 * SIZE, 55*SIZE);

        stage.setTitle("EightQueens");
        stage.setScene(scene);
        stage.show();

    }


    private boolean search() {
        int k = 0; //kth row to place a queen

        while (k >= 0 && k < SIZE) {
            int j = findPosition(k);
            if (j >= 0) {
                this.queens[k] = j;

                k++;
            } else {
                this.queens[k] = -1;

                k--;
            }
        }

        return k != -1;
    }

    private int findPosition(int k) {
        int start = this.queens[k] + 1;
        for (int j = start; j < SIZE; j++) {
            if (isValid(k, j))
                return j;
        }
        return -1;
    }

    private boolean isValid(int row, int col) {
        for (int i = 1; i <= row; i++) {
            if (this.queens[row - i] == col
                    || this.queens[row - i] == col + i
                    || this.queens[row - i] == col - i)
                return false;
        }
        return true;
    }
}
