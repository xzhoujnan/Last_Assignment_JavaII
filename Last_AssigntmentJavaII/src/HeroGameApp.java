import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.cell.PropertyValueFactory;


import java.util.List;

public class HeroGameApp extends Application {

    private TableView<Player> tableView;
    private DatabaseManager databaseManager;

    @Override
    public void start(Stage primaryStage) {
        databaseManager = new DatabaseManager();
        tableView = new TableView<>();

        TableColumn<Player, Integer> idColumn = new TableColumn<>("Player Id");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("playerId"));

        TableColumn<Player, String> nameColumn = new TableColumn<>("Player Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("playerName"));

        TableColumn<Player, Integer> scoreColumn = new TableColumn<>("High Score");
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("highScore"));

        TableColumn<Player, Integer> levelColumn = new TableColumn<>("Level");
        levelColumn.setCellValueFactory(new PropertyValueFactory<>("level"));

        tableView.getColumns().addAll(idColumn, nameColumn, scoreColumn, levelColumn);

        Button loadButton = new Button("Load Players");
        loadButton.setOnAction(e -> loadPlayers());

        VBox vbox = new VBox(loadButton, tableView);
        Scene scene = new Scene(vbox);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Hero Game");
        primaryStage.show();
    }

    private void loadPlayers() {
        List<Player> players = databaseManager.displayAll(); // Gọi phương thức để lấy danh sách người chơi
        tableView.getItems().clear();
        tableView.getItems().addAll(players);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
