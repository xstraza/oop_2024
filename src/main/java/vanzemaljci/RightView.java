package vanzemaljci;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import vanzemaljci.Alien;
import vanzemaljci.Database;
import vanzemaljci.LeftView;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RightView {
    private Label label2 = new Label();

    public VBox createRightView() {
        VBox vbox = new VBox();
        Label label = new Label("Pronadjen");
        ComboBox<String> cb = new ComboBox<>();
        List<String> countries = Database.getContinents().values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        cb.setItems(FXCollections.observableList(countries));
        Button button = new Button("Upisi");
        button.setOnAction(e -> {
            Alien alien = LeftView.getTv().getSelectionModel().getSelectedItem();
            List<String> strings = Database.getContinents().get(alien.getContinent());
            String selectedItem = cb.getSelectionModel().getSelectedItem();
            if (alien.isFound()) {
                label2.setText("Alien with id:" + alien.getId() + " already found!");
            } else if (!strings.contains(selectedItem)) {
                label2.setText("Country does not belong to continent");
            } else {
                alien.setCountry(selectedItem);
                LeftView.getTv().refresh();
                label2.setText("");
            }

        });
        vbox.getChildren().addAll(label, cb, button, label2);
        vbox.setSpacing(20);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(5, 10, 5, 5));
        return vbox;
    }
}
