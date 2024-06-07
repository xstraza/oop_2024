package transakcije;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MainTransakcije extends Application {

    public static List<Transaction> transactions;
    public static List<String> categories;

    public static void main(String[] args) {
        transactions = Transakcije.readFile();
        categories = transactions.stream()
                .map(Transaction::getCategory)
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Tabela().getScene();
        primaryStage.setScene(scene);
        primaryStage.setTitle("OOP Jul 21/22");
        primaryStage.show();
    }
}
