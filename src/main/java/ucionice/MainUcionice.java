package ucionice;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ucionice.CitacFajla;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainUcionice extends Application {
    public static List<Ucionica> ucionice = new ArrayList<>();
    public static Map<String, List<Ucionica>> ucionicePoTerminu = new HashMap<>();
    public static List<Student> studenti = new ArrayList<>();
    public static List<Raspodela> raspodela = new ArrayList<>();

    public static void main(String[] args) {
        ucionice = CitacFajla.ucitajUcionice();
        studenti = CitacFajla.ucitajStudente();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new PrviProzor().getScene();
        primaryStage.setScene(scene);
        primaryStage.setTitle("OOP Jun 21/22");
        primaryStage.show();
    }
}
