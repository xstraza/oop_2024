package filmovi;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MainFilmovi extends Application {

    private static List<Movie> movies;
    private static List<Movie> seenMovies = new ArrayList<>();
    private static List<String> phases;
    private static TextField imeFilma = new TextField();
    private static TextField godinaFilma = new TextField();
    private static TableView tabelaFilmova = new TableView();
    private static CheckBox filterCb = new CheckBox("Primeni filter za faze");
    private static ComboBox phaseCb = new ComboBox();
    private static ListView<Movie> odgledaniFilmovi = new ListView<>();
    private static TextField prosekOcena = new TextField();
    private static TextField prosekTrajanja = new TextField();

    public static void main(String[] args) {
        movies = DataReader.readFile();
        phases = movies.stream()
                .map(Movie::getPhase)
                .distinct()
                .map(x -> "Phase " + x)
                .sorted()
                .collect(Collectors.toList());
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        VBox rootVBox = new VBox();

        rootVBox.getChildren().add(topFilter());
        rootVBox.getChildren().add(fazeFilter());
        rootVBox.getChildren().add(godineFilter());
        rootVBox.getChildren().add(tabele());
        rootVBox.getChildren().add(dugmici());
        rootVBox.getChildren().add(new Label("STATISTIKE"));
        rootVBox.getChildren().add(labele());
        rootVBox.getChildren().add(prosecneOcene());
        rootVBox.getChildren().add(prosekTrajanja());

        rootVBox.setSpacing(20);
        rootVBox.setPadding(new Insets(10, 10, 10, 10));

        Scene sc = new Scene(rootVBox);

        primaryStage.setScene(sc);
        primaryStage.setTitle("OOP Septembar 21/22");
        primaryStage.show();
    }

    private HBox prosecneOcene() {
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.getChildren().add(new Label("Prosecna ocena:"));
        TextField tf1 = new TextField();
        tf1.setEditable(false);
        String prosecnaOcenaSvih = String.valueOf(movies.stream()
                .mapToDouble(Movie::getRating)
                .average().getAsDouble());
        tf1.setText(prosecnaOcenaSvih);
        hBox.getChildren().add(tf1);
        TextField tf2 = prosekOcena;
        tf2.setEditable(false);
        String prosecnaOcenaOdgledanih = movies.stream()
                .filter(Movie::isOdgledan)
                .mapToDouble(Movie::getRating)
                .average().stream()
                .mapToObj(String::valueOf)
                .findFirst()
                .orElse("");
        tf2.setText(prosecnaOcenaOdgledanih);
        hBox.getChildren().add(tf2);
        return hBox;
    }

    private HBox prosekTrajanja() {
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.getChildren().add(new Label("Prosecna duzina trajanja:"));
        TextField tf1 = new TextField();
        tf1.setEditable(false);
        String prosekTrajanjaSvih = String.valueOf(movies.stream()
                .mapToInt(Movie::getDuration)
                .average()
                .getAsDouble()) + " minuta";
        tf1.setText(prosekTrajanjaSvih);
        hBox.getChildren().add(tf1);
        TextField tf2 = prosekTrajanja;
        tf2.setEditable(false);
        String prosekTrajanjaOdgledanih = movies.stream()
                .filter(Movie::isOdgledan)
                .mapToInt(Movie::getDuration)
                .average().stream()
                .mapToObj(String::valueOf)
                .map(x -> x + " minuta")
                .findFirst()
                .orElse("");
        tf2.setText(prosekTrajanjaOdgledanih);
        hBox.getChildren().add(tf2);
        return hBox;
    }

    private HBox labele() {
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.getChildren().add(new Label("Sumarno"));
        hBox.getChildren().add(new Label("Odgledani"));
        return hBox;
    }

    private HBox dugmici() {
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        Button odgledani = new Button("Odgledani");
        odgledani.setOnAction(e -> {
            ObservableList<Movie> selectedItems = tabelaFilmova.getSelectionModel().getSelectedItems();
            selectedItems.stream()
                    .filter(x -> !seenMovies.contains(x))
                    .forEach(x -> {seenMovies.add(x); x.setOdgledan();});
            seenMovies.sort(Comparator.comparing(x -> x.toString()));
            odgledaniFilmovi.setItems(FXCollections.observableList(seenMovies));
            String prosekTrajanjaOdgledanih = seenMovies.stream()
                    .filter(Movie::isOdgledan)
                    .mapToInt(Movie::getDuration)
                    .average().stream()
                    .mapToObj(String::valueOf)
                    .map(x -> x + " minuta")
                    .findFirst()
                    .orElse("");
            prosekTrajanja.setText(prosekTrajanjaOdgledanih);
            String prosecnaOcenaOdgledanih = seenMovies.stream()
                    .filter(Movie::isOdgledan)
                    .mapToDouble(Movie::getRating)
                    .average().stream()
                    .mapToObj(String::valueOf)
                    .findFirst()
                    .orElse("");
            prosekOcena.setText(prosecnaOcenaOdgledanih);
        });
        hBox.getChildren().add(odgledani);
        hBox.getChildren().add(new Button("Zapamti"));
        return hBox;
    }

    private HBox tabele() {
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        TableColumn<Movie, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        tabelaFilmova.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tabelaFilmova.getColumns().add(nameColumn);
        TableColumn<Movie, String> phaseColumn = new TableColumn<>("Phase");
        phaseColumn.setCellValueFactory(cellData -> cellData.getValue().phaseProperty());
        tabelaFilmova.getColumns().add(phaseColumn);
        TableColumn<Movie, String> ratingColumn = new TableColumn<>("Rating");
        ratingColumn.setCellValueFactory(cellData -> cellData.getValue().ratingProperty());
        tabelaFilmova.getColumns().add(ratingColumn);
        TableColumn<Movie, String> yearColumn = new TableColumn<>("Year");
        yearColumn.setCellValueFactory(cellData -> cellData.getValue().yearProperty());
        tabelaFilmova.getColumns().add(yearColumn);
        TableColumn<Movie, String> durationColumn = new TableColumn<>("Duration");
        durationColumn.setCellValueFactory(cellData -> cellData.getValue().durationProperty());
        tabelaFilmova.getColumns().add(durationColumn);
        TableColumn<Movie, String> seenColumn = new TableColumn<>("Seen");
        seenColumn.setCellValueFactory(cellData -> cellData.getValue().seenProperty());
        tabelaFilmova.getColumns().add(seenColumn);

        ObservableList<Movie> observableList = FXCollections.observableList(movies);
        tabelaFilmova.setItems(observableList);

        hBox.getChildren().add(tabelaFilmova);
        hBox.getChildren().add(odgledaniFilmovi);
        return hBox;
    }

    private HBox godineFilter() {
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.getChildren().add(new Label("Godina:"));
        hBox.getChildren().add(godinaFilma);
        Button filtriraj = new Button("Filtriraj");
        filtriraj.setOnAction(e -> {
            if (filterCb.isSelected()) {
                String selectedPhase = (String) phaseCb.getSelectionModel().getSelectedItem();
                int faza = Integer.valueOf(selectedPhase.split(" ")[1]);
                List<Movie> filtrirani = movies.stream()
                        .filter(x -> x.getPhase() == faza)
                        .collect(Collectors.toList());
                ObservableList<Movie> filtriraniFilmovi = FXCollections.observableList(filtrirani);
                tabelaFilmova.setItems(filtriraniFilmovi);
            } else {
                String text = imeFilma.getText().toLowerCase();
                Integer godina = godinaFilma.getText().length() > 0
                        ? Integer.parseInt(godinaFilma.getText()) //mozda nije string
                        : 0;
                List<Movie> filtrirani = movies.stream()
                        .filter(x -> x.getTitle().toLowerCase().contains(text))
                        .filter(x -> godina == 0 || x.getYear() == godina)
                        .collect(Collectors.toList());
                ObservableList<Movie> filtriraniFilmovi = FXCollections.observableList(filtrirani);
                tabelaFilmova.setItems(filtriraniFilmovi);
            }
        });
        hBox.getChildren().add(filtriraj);
        Button prikazSvih = new Button("Prikaz svih");
        prikazSvih.setOnAction(e -> {
            List<Movie> sortedMovies = movies.stream()
                    .sorted(Comparator.comparing(x -> x.getTitle()))
                    .collect(Collectors.toList());
            tabelaFilmova.setItems(FXCollections.observableList(sortedMovies));
        });
        hBox.getChildren().add(prikazSvih);
        return hBox;
    }

    private HBox fazeFilter() {
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        ObservableList<String> observableList = FXCollections.observableList(phases);
        phaseCb.setItems(observableList);
        phaseCb.getSelectionModel().selectFirst();
        hBox.getChildren().add(phaseCb);
        hBox.getChildren().add(filterCb);
        return hBox;
    }

    private HBox topFilter() {
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.getChildren().add(new Label("Filter:"));
        hBox.getChildren().add(new Label("Deo/Celo ime filma"));
        hBox.getChildren().add(imeFilma);
        return hBox;
    }

}
