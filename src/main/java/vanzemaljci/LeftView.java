package vanzemaljci;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.*;
import java.util.stream.Collectors;

import static vanzemaljci.Database.getAliens;

public class LeftView {

    private ComboBox<String> cb = new ComboBox<>();
    private CheckBox pronadjeni = new CheckBox("Pronadjeni");
    private CheckBox izgubljeni = new CheckBox("Izgubljeni");
    private ObservableList<Alien> aliens = FXCollections.observableList(new ArrayList<>());
    private static TableView<Alien> tv = new TableView();
    TableColumn<Alien, Integer> tcId = new TableColumn<>("ID");

    public static TableView<Alien> getTv() {
        return tv;
    }

    public VBox createLeftView() {
        VBox vBox = new VBox();
        vBox.getChildren().add(createTopHBox());
        vBox.getChildren().add(createTableView());
        vBox.getChildren().add(createButton());
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(5, 5, 5, 10));
        return vBox;
    }

    private HBox createTopHBox() {
        HBox hBox = new HBox();
        Label label = new Label("Kontinent");
        List<String> continents = new ArrayList<>(Database.getContinents().keySet());
        continents.add("Svi Kontinenti");
        ObservableList<String> obscontinents = FXCollections.observableList(continents);
        cb.setItems(obscontinents);
        cb.getSelectionModel().selectLast();
        Button button = new Button("Filtriraj");
        button.setOnAction(e -> {
            aliens.clear();
            aliens.addAll(getAliens());
            String item = cb.getSelectionModel().getSelectedItem();
            if (item.equals("Svi Kontinenti")) {
                aliens.clear();
                if (izgubljeni.isSelected()) {
                    aliens.addAll(getAliens().stream()
                            .filter(x -> x.isFound()).collect(Collectors.toSet()));
                }
                if (pronadjeni.isSelected()) {
                    aliens.addAll(getAliens().stream()
                            .filter(x -> !x.isFound()).collect(Collectors.toSet()));
                }
            } else {
                if (!izgubljeni.isSelected()) {
                    aliens.removeIf(x -> x.isFound());
                }
                if (!pronadjeni.isSelected()) {
                    aliens.removeIf(x -> !x.isFound());
                }
                aliens.removeIf(x -> !x.getContinent().equals(item));
            }
            tv.getSortOrder().add(tcId);
        });
        pronadjeni.setSelected(true);
        izgubljeni.setSelected(true);
        hBox.getChildren().addAll(label, cb, pronadjeni, izgubljeni, button);
        hBox.setSpacing(20);
        return hBox;
    }

    private TableView<Alien> createTableView() {
        tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Alien, String> tcKontinent = new TableColumn<>("Kontinent");
        tcKontinent.setCellValueFactory(cellData -> cellData.getValue().getContinentStringProperty());
        TableColumn<Alien, String> tcDrzava = new TableColumn<>("Drzava");
        tcDrzava.setCellValueFactory(cellData -> cellData.getValue().getCountryStringProperty());
        tv.getColumns().add(tcId);
        tv.getColumns().add(tcKontinent);
        tv.getColumns().add(tcDrzava);
        aliens.addAll(getAliens());
        tv.setItems(aliens);
        tv.getSortOrder().add(tcId);
        tv.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        return tv;
    }

    private Button createButton() {
        Button button = new Button("Sacuvaj");
        button.setOnAction(e -> {
            Map<String, List<String>> continents = Database.getContinents();
            List<String> conts = new ArrayList<>(continents.keySet());
            conts.sort(String::compareTo);
            for (String cont : conts) {
                long sum = getAliens().stream()
                        .filter(x -> x.getContinent().equals(cont))
                        .filter(x -> x.isFound())
                        .count();
                System.out.println(cont + " : " + sum);
            }
            List<String> zemlje = continents.values().stream().flatMap(Collection::stream).collect(Collectors.toList());
            zemlje.sort(String::compareTo);
            for (String s : zemlje) {
                long sum = getAliens().stream()
                        .filter(x -> x.getCountry() != null && x.getCountry().equals(s))
                        .filter(x -> x.isFound())
                        .count();
                System.out.println(s + " : " + sum);
            }

        });
        return button;
    }
}
