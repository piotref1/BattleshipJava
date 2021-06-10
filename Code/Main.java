package sample;

import java.util.Random;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import sample.Board.Cell;

public class Main extends Application {

    private boolean running = false;
    private Board enemyBoard, playerBoard;

    private int shipsToPlace = 5;

    private boolean enemyTurn = false;

    private Random random = new Random();

    private Parent createContent() {
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("End game");
        alert1.setHeaderText("YOU WIN");
        alert1.setContentText(null);
        BorderPane root = new BorderPane();
        root.setPrefSize(800, 600);

        Label label = new Label("<< Player Board \n Enemy board >>");

        HBox hbox3 = new HBox();
        hbox3.getChildren().add(label);
        hbox3.setAlignment(Pos.CENTER);
        root.setTop(hbox3);

        enemyBoard = new Board(true, event -> {
            if (!running)
                return;

            Cell cell = (Cell) event.getSource();
            if (cell.wasShot)
                return;

            enemyTurn = !cell.shoot();

            if (enemyBoard.ships == 1) {
                alert1.showAndWait();

                System.exit(0);
            }

            if (enemyTurn)
                enemyMove();
        });

        playerBoard = new Board(false, event -> {
            if (running)
                return;

            Cell cell = (Cell) event.getSource();
            if (playerBoard.placeShip(new Ship(shipsToPlace, event.getButton() == MouseButton.PRIMARY), cell.x, cell.y)) {

                if (--shipsToPlace == 1) {
                    startGame();
                }
            }
        });

        HBox hbox1 = new HBox(playerBoard,enemyBoard);
        hbox1.setAlignment(Pos.CENTER);
        hbox1.setSpacing(150);

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().add(hbox1);

        root.setCenter(vbox);
        return root;
    }

    private void enemyMove() {
        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
        alert2.setTitle("End game");
        alert2.setHeaderText("YOU LOSE");
        alert2.setContentText(null);
        while (enemyTurn) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            Cell cell = playerBoard.getCell(x, y);
            if (cell.wasShot)
                continue;

            enemyTurn = cell.shoot();

            if (playerBoard.ships == 0) {
                alert2.showAndWait();
                System.exit(0);
            }
        }
    }

    private void startGame() {
        // place enemy ships
        int type = 5;

        while (type > 1) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            if (enemyBoard.placeShip(new Ship(type, Math.random() < 0.5), x, y)) {
                type--;
            }
        }

        running = true;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent());
        primaryStage.setTitle("Battleship");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
