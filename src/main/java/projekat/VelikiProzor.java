package projekat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import transakcije.Pregled;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class VelikiProzor {
    private ListView<Osoba> listView = new ListView<>();
    private ComboBox<String> veceManje = new ComboBox<>();
    private ComboBox<String> tipAkcijeComboBox = new ComboBox<>();
    private TableView<Akcija> akcije = new TableView<>();
    private TextField filterIme = new TextField();
    private TextField filterBroj = new TextField();
    private Button filtriraj = new Button("Filtriraj");
    private Button resetuj = new Button("Resetuj");
    private Button pregled = new Button("Pregled");
    private Button obracun = new Button("Obracun");
    private ObservableList<Osoba> osobas;
    private ObservableList<String> znaci;
    private ObservableList<String> tipAkcijes;

    public Scene getScene() {
        VBox vBox = new VBox();

        HBox hBox = new HBox();
        hBox.getChildren().add(new Label("Polaznici"));
        hBox.getChildren().add(new Label("Filter polaznika"));
        hBox.setSpacing(200);
        vBox.getChildren().add(hBox);

        HBox hBox2 = new HBox();
        hBox2.setSpacing(20);
        osobas = FXCollections.observableList(Main.osobe);
        listView.setItems(osobas);
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        hBox2.getChildren().add(listView);
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(15);
        gridPane.add(new Label("Deo imena:"), 0, 0);
        gridPane.add(filterIme, 1, 0);

        gridPane.add(new Label("Ubaci samo one koje imaju"), 0, 1, 2, 1);

        znaci = FXCollections.observableArrayList("<", ">", "=");
        veceManje.setItems(znaci);
        veceManje.getSelectionModel().selectFirst();
        gridPane.add(veceManje, 0, 2);
        gridPane.add(filterBroj, 1, 2);
        tipAkcijes = FXCollections.observableArrayList(TipAkcije.POLAGANJE.getIspis(), TipAkcije.CAS_TEORIJE.getIspis(), TipAkcije.CAS_VOZNJE.getIspis(), TipAkcije.UPLATA.getIspis());
        tipAkcijeComboBox.setItems(tipAkcijes);
        tipAkcijeComboBox.getSelectionModel().selectFirst();
        gridPane.add(tipAkcijeComboBox, 2, 2);

        resetuj.setOnAction(e -> {
            osobas = FXCollections.observableList(Main.osobe);
            listView.setItems(osobas);
            akcije.setItems(FXCollections.emptyObservableList());
        });
        gridPane.add(resetuj, 0, 3);
        filtriraj.setOnAction(e -> {
            String ime = filterIme.getText();
            String broj = filterBroj.getText();
            List<Osoba> filtriraneOsobe = new ArrayList<>(Main.osobe);
            if (ime != null && !ime.isEmpty()) {
                Main.osobe.stream()
                        .filter(x -> x.toString().toLowerCase().contains(ime.toLowerCase()))
                        .forEach(filtriraneOsobe::remove);
            }
            try {
                int i = Integer.parseInt(broj);
                TipAkcije tipAkcije = TipAkcije.getTip(tipAkcijeComboBox.getSelectionModel().getSelectedItem());
                String znak = veceManje.getSelectionModel().getSelectedItem();
                if (znak.equals("<")) {
                    filtriraneOsobe = filtriraneOsobe.stream()
                            .filter(x -> Main.akcijeZaOsobu.get(x).stream()
                                    .filter(y -> y.getTipAkcije().equals(tipAkcije))
                                    .count() < i)
                            .collect(Collectors.toList());
                } else if (znak.equals(">")) {
                    filtriraneOsobe = filtriraneOsobe.stream()
                            .filter(x -> Main.akcijeZaOsobu.get(x).stream()
                                    .filter(y -> y.getTipAkcije().equals(tipAkcije))
                                    .count() > i)
                            .collect(Collectors.toList());
                } else {
                    filtriraneOsobe = filtriraneOsobe.stream()
                            .filter(x -> Main.akcijeZaOsobu.get(x).stream()
                                    .filter(y -> y.getTipAkcije().equals(tipAkcije))
                                    .count() == i)
                            .collect(Collectors.toList());
                }
            } catch (NumberFormatException tt) {
//                tt.printStackTrace();
            }
            osobas = FXCollections.observableList(filtriraneOsobe);
            listView.setItems(osobas);
        });
        gridPane.add(filtriraj, 1, 3);
        pregled.setOnAction(e -> {
            ObservableList<Osoba> selectedItems = listView.getSelectionModel().getSelectedItems();
            List<Akcija> akcijee = selectedItems.stream()
                    .map(x -> Main.akcijeZaOsobu.get(x))
                    .flatMap(Collection::stream)
                    .collect(Collectors.toList());
            ObservableList<Akcija> obsAkcije = FXCollections.observableList(akcijee);
            akcije.setItems(obsAkcije);
        });
        gridPane.add(pregled, 2, 3);

        obracun.setOnAction(e -> {
            VBox vBox1 = new VBox();
            String uplaceno = String.valueOf(Main.akcije.stream()
                    .filter(x -> x.getTipAkcije().equals(TipAkcije.UPLATA))
                    .mapToDouble(Akcija::getIznos)
                    .sum());
            vBox1.getChildren().add(new Label("Ukupan uplacen iznos: " + uplaceno));
            String dug = String.valueOf(Main.akcijeZaOsobu.values().stream()
                    .mapToDouble(this::sraccunaj)
                    .sum());
            vBox1.getChildren().add(new Label("Ukupan dug za casove: " + dug));
            String pretplata = String.valueOf(
                    Main.akcijeZaOsobu.entrySet().stream()
                            .filter(x -> sraccunaj(x.getValue()) > 0)
                            .count()
            );
            vBox1.getChildren().add(new Label("Broj pretplacenih korisnika: " + pretplata));
            Stage stage = new Stage();
            stage.setScene(new Scene(vBox1));
            stage.show();
        });
        gridPane.add(obracun, 0, 4);

        hBox2.getChildren().add(gridPane);
        vBox.getChildren().add(hBox2);

        vBox.getChildren().add(new Label("Akcije"));
        TableColumn<Akcija, String> datum = new TableColumn<>("Datum");
        datum.setCellValueFactory(cellData -> cellData.getValue().getDatumPoperty());
        akcije.getColumns().add(datum);
        TableColumn<Akcija, String> ime = new TableColumn<>("Ime");
        ime.setCellValueFactory(cellData -> cellData.getValue().getImeProperty());
        akcije.getColumns().add(ime);
        TableColumn<Akcija, String> prezime = new TableColumn<>("Prezime");
        prezime.setCellValueFactory(cellData -> cellData.getValue().getPrezimeProperty());
        akcije.getColumns().add(prezime);
        TableColumn<Akcija, String> akcija = new TableColumn<>("Akcija");
        akcija.setCellValueFactory(cellData -> cellData.getValue().getTipProperty());
        akcije.getColumns().add(akcija);
        TableColumn<Akcija, Double> iznos = new TableColumn<>("Iznos");
        iznos.setCellValueFactory(new PropertyValueFactory<>("iznos"));
        akcije.getColumns().add(iznos);
        vBox.getChildren().add(akcije);
        vBox.setSpacing(15);
        vBox.setPadding(new Insets(5, 5, 5, 5));

        Scene scene = new Scene(vBox);
        return scene;
    }

    private double sraccunaj(List<Akcija> akcijas) {
        double suma = 0;
        suma += akcijas.stream()
                .filter(x -> x.getTipAkcije().equals(TipAkcije.UPLATA))
                .mapToDouble(Akcija::getIznos)
                .sum();
        suma -= akcijas.stream()
                .filter(x -> x.getTipAkcije().equals(TipAkcije.CAS_TEORIJE))
                .mapToDouble(x -> 500)
                .sum();
        suma -= akcijas.stream()
                .filter(x -> x.getTipAkcije().equals(TipAkcije.CAS_VOZNJE))
                .mapToDouble(x -> 750)
                .sum();
        suma -= akcijas.stream()
                .filter(x -> x.getTipAkcije().equals(TipAkcije.POLAGANJE))
                .mapToDouble(x -> 2500)
                .sum();
        return -suma;
    }
}
