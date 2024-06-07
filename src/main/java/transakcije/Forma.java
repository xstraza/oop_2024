package transakcije;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import projekat.Main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Forma {
    private Label label1 = new Label("Iznos");
    private TextField tf1 = new TextField();
    private Label label2 = new Label("Primalac");
    private TextField tf2 = new TextField();
    private Label label3 = new Label("Kategorija");
    private ComboBox<String> cb1 = new ComboBox<>();
    private Button button = new Button("Plati");
    private ObservableList<String> categories;

    public Scene getScene() {
        GridPane gridPane = new GridPane();

        categories = FXCollections.observableList(MainTransakcije.categories);
        cb1.setItems(categories);
        cb1.getSelectionModel().selectFirst();

        button.setOnAction(e -> {
            int amount = Integer.parseInt(tf1.getText());
            String senderReceiver = tf2.getText();
            String category = cb1.getValue();

            String pattern = "dd.MM.yyyy";
            DateFormat df = new SimpleDateFormat(pattern);
            Date today = new Date();
            String todayAsString = df.format(today);

            Transaction transaction = new Transaction(amount, senderReceiver, todayAsString, TransactionType.ISPLATA, category);
            MainTransakcije.transactions.add(transaction);

            Stage stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
            stage.setScene(new Tabela().getScene());
        });

        gridPane.add(label1, 0, 0);
        gridPane.add(tf1, 1, 0);
        gridPane.add(label2, 0, 1);
        gridPane.add(tf2, 1, 1);
        gridPane.add(label3, 0, 2);
        gridPane.add(cb1, 1, 2);
        gridPane.add(button, 0, 3);

        gridPane.setPrefSize(300, 200);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Scene scene = new Scene(gridPane);
        return scene;
    }
}
