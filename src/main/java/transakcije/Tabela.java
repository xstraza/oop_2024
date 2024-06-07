package transakcije;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import projekat.Main;
import transakcije.Forma;

import java.util.List;

public class Tabela {
    private Label label = new Label();
    private TableView tableView = new TableView();
    private Button button1 = new Button("Nova isplata");
    private Button button2 = new Button("Pregled isplata");
    private Button button3 = new Button("Snimi");
    private ObservableList<Transaction> obsTransactions;

    public Scene getScene() {
        VBox vbox = new VBox();

        label.setText("Trenutno stanje: " + getTrenutnoStanje(MainTransakcije.transactions) + " RSD");

        TableColumn<Transaction, Integer> column1 = new TableColumn<>("Iznos");
        column1.setCellValueFactory(new PropertyValueFactory<>("amount"));
        TableColumn<Transaction, String> column2 = new TableColumn<>("Uplatilac/Primalac");
        column2.setCellValueFactory(new PropertyValueFactory<>("senderReceiver"));
        TableColumn<Transaction, String> column3 = new TableColumn<>("Datum");
        column3.setCellValueFactory(new PropertyValueFactory<>("date"));
        TableColumn<Transaction, TransactionType> column4 = new TableColumn<>("Tip");
        column4.setCellValueFactory(new PropertyValueFactory<>("transactionType"));

        tableView.getColumns().addAll(column1, column2, column3, column4);
        obsTransactions = FXCollections.observableList(MainTransakcije.transactions);
        tableView.setItems(obsTransactions);

        HBox buttonHBox = new HBox();
        buttonHBox.getChildren().add(button1);
        button1.setOnAction(e -> {
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setScene(new Forma().getScene());
        });
        buttonHBox.getChildren().add(button2);
        button2.setOnAction(e -> {
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setScene(new Pregled().getScene());
        });
        buttonHBox.getChildren().add(button3);
        button3.setOnAction(e -> Transakcije.saveFiles(MainTransakcije.transactions));
        buttonHBox.setSpacing(30);
        buttonHBox.setAlignment(Pos.CENTER);

        vbox.getChildren().add(label);
        vbox.getChildren().add(tableView);
        vbox.getChildren().add(buttonHBox);

        vbox.setSpacing(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10, 0, 10, 0));
        vbox.setPrefSize(600, 400);

        Scene scene = new Scene(vbox);
        return scene;
    }

    private String getTrenutnoStanje(List<Transaction> transactions) {
        return transactions.stream()
                .mapToInt(x ->
                        x.getTransactionType().equals(TransactionType.UPLATA)
                                ? x.getAmount()
                                : -x.getAmount())
                .sum() + "";
    }
}
