package transakcije;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import projekat.Main;
import transakcije.MainTransakcije;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Pregled {
    private Label label1 = new Label();
    private Label label2 = new Label();
    private Button button = new Button("Zatvori");
    private ListView<String> lv = new ListView<>();
    private ObservableList<String> obsKategorije;

    public Scene getScene() {
        BorderPane borderPane = new BorderPane();

        button.setOnAction(e -> {
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setScene(new Tabela().getScene());
        });
        button.setAlignment(Pos.CENTER);

        int min = MainTransakcije.transactions.stream()
                .filter(t -> t.getTransactionType() == TransactionType.ISPLATA)
                .map(Transaction::getAmount)
                .reduce(Integer.MAX_VALUE, Integer::min);
        int max = MainTransakcije.transactions.stream()
                .filter(t -> t.getTransactionType() == TransactionType.ISPLATA)
                .map(Transaction::getAmount)
                .reduce(Integer.MIN_VALUE, Integer::max);
        label1.setText("Minimalna isplata: " + min + " RSD");
        label2.setText("Maksimalna isplata: " + max + " RSD");
        VBox vbox = new VBox();
        vbox.setSpacing(15);
        vbox.getChildren().addAll(label1, label2);

        List<String> redovi = MainTransakcije.categories.stream()
                .map(k -> {
                    int suma = MainTransakcije.transactions.stream()
                            .filter(t -> t.getTransactionType() == TransactionType.ISPLATA)
                            .filter(t -> t.getCategory().equals(k))
                            .mapToInt(Transaction::getAmount)
                            .sum();
                    int cnt = (int) MainTransakcije.transactions.stream()
                            .filter(t -> Objects.equals(t.getCategory(), k))
                            .count();
                    return k + " (" + cnt + " placanja): " + suma + " RSD";
                })
                .collect(Collectors.toList());

        obsKategorije = FXCollections.observableList(redovi);
        lv.setItems(obsKategorije);

        borderPane.setMargin(vbox, new Insets(10, 0, 10, 10));
        borderPane.setTop(vbox);
        borderPane.setCenter(lv);
        borderPane.setAlignment(button, Pos.CENTER);
        borderPane.setMargin(button, new Insets(10, 0, 10, 0));
        borderPane.setBottom(button);

        borderPane.setPrefSize(400, 600);

        Scene scene = new Scene(borderPane);
        return scene;
    }
}
