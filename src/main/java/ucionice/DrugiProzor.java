package ucionice;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import projekat.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static ucionice.MainUcionice.*;

public class DrugiProzor {
    private TableView<Raspodela> tv = new TableView<>();
    private Button button = new Button("Snimi");
    private Button buttonC = new Button("Dodeli");
    private Button buttonR = new Button("Ucitaj za termin");
    private Label label1 = new Label("Broj nerasporedjenih studenata");
    private ListView<Student> lv1 = new ListView<>();
    private ListView<Ucionica> lv2 = new ListView<>();
    private ComboBox<String> cb1 = new ComboBox<>();
    private ObservableList<String> obsTermini;
    private ObservableList<Student> obsStudenti;
    private ObservableList<Ucionica> obsUcionice;
    private ObservableList<Raspodela> obsRaspodela = FXCollections.emptyObservableList();

    public Scene getScene(int brojTermina) {
        pripremiUcionicePoTerminima(brojTermina);

        VBox vbox = new VBox();

        VBox vboxL = new VBox();
        label1.setText(label1.getText() + " " + studenti.size());
        vboxL.getChildren().add(label1);
        obsStudenti = FXCollections.observableList(studenti);
        lv1.setItems(obsStudenti);
        lv1.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        vboxL.getChildren().add(lv1);
        vboxL.setAlignment(Pos.CENTER);
        vboxL.setSpacing(5);

        VBox vboxR = new VBox();
        HBox hborR = new HBox();

        obsTermini = FXCollections.observableList(
                IntStream.range(1, brojTermina + 1)
                        .mapToObj(x -> "Termin " + x)
                        .collect(Collectors.toList())
        );
        cb1.setItems(obsTermini);
        cb1.getSelectionModel().selectFirst();

        hborR.getChildren().add(cb1);
        buttonR.setOnAction(e -> {
            obsUcionice = FXCollections.observableList(ucionicePoTerminu.get(cb1.getSelectionModel().getSelectedItem()));
            lv2.setItems(obsUcionice);
        });
        hborR.getChildren().add(buttonR);
        hborR.setAlignment(Pos.CENTER);
        hborR.setSpacing(5);
        vboxR.getChildren().add(hborR);

        obsUcionice = FXCollections.observableList(ucionicePoTerminu.get(cb1.getSelectionModel().getSelectedItem()));
        lv2.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        lv2.setItems(obsUcionice);
        vboxR.getChildren().add(lv2);
        vboxR.setAlignment(Pos.CENTER);
        vboxR.setSpacing(5);

        HBox hbox = new HBox();
        hbox.getChildren().add(vboxL);
        buttonC.setOnAction(e -> {
            List<Student> students = lv1.getSelectionModel().getSelectedItems();
            Ucionica ucionica = lv2.getSelectionModel().getSelectedItem();
            if (students.size() <= ucionica.getBrojMesta()) {
                for (Ucionica ucionica1 : obsUcionice) {
                    if (ucionica1.equals(ucionica)) {
                        ucionica1.setBrojMesta(ucionica1.getBrojMesta() - students.size());
                    }
                }
                lv2.setItems(obsUcionice);
                for (Student student : students) {
                    raspodela.add(new Raspodela(student, cb1.getSelectionModel().getSelectedItem(), ucionica));
                }
                obsStudenti.removeAll(students);
                label1.setText("Broj nerasporedjenih studenata " + obsStudenti.size());
                obsRaspodela = FXCollections.observableList(raspodela);
                tv.setItems(obsRaspodela);
            }
        });
        hbox.getChildren().add(buttonC);
        hbox.getChildren().add(vboxR);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);

        vbox.getChildren().add(hbox);
        TableColumn<Raspodela, Student> student = new TableColumn<>("Student");
        student.setCellValueFactory(new PropertyValueFactory<>("student"));
        tv.getColumns().add(student);
        TableColumn<Raspodela, String> termin = new TableColumn<>("Termin");
        termin.setCellValueFactory(new PropertyValueFactory<>("termin"));
        tv.getColumns().add(termin);
        TableColumn<Raspodela, Ucionica> ucionica = new TableColumn<>("Ucionica");
        ucionica.setCellValueFactory(new PropertyValueFactory<>("ucionica"));
        tv.getColumns().add(ucionica);
        vbox.getChildren().add(tv);
        vbox.getChildren().add(button);

        vbox.setPrefSize(600, 600);
        vbox.setSpacing(20);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox);
        return scene;
    }

    private void pripremiUcionicePoTerminima(int brojTermina) {
        ucionicePoTerminu.clear();
        IntStream.range(1, brojTermina + 1)
                .forEach(x -> ucionicePoTerminu.put("Termin " + x, new ArrayList<>()));
        for (String s : ucionicePoTerminu.keySet()) {
            List<Ucionica> ucionice2 = ucionice.stream()
                    .map(Ucionica::copy)
                    .collect(Collectors.toList());
            ucionicePoTerminu.get(s).addAll(ucionice2);
        }
    }
}
