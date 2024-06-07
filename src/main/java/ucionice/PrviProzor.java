package ucionice;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import projekat.Main;
import ucionice.DrugiProzor;
import ucionice.MainUcionice;

import java.util.Objects;

public class PrviProzor {
    private Label l1 = new Label("Unesite potrebrebne podatke za raspodelu");
    private Label l2 = new Label("Broj termina");
    private Label l3 = new Label("Broj studenata u ucionici");
    private TextField tf1 = new TextField();
    private TextField tf2 = new TextField();
    private CheckBox cb = new CheckBox("samo ucionice sa racunarimna");
    private Button button = new Button("Pokreni");
    private Label l4 = new Label("Poruka");


    public Scene getScene() {
        VBox vbox = new VBox();

        GridPane gp1 = new GridPane();
        gp1.add(l2, 0, 0);
        gp1.add(tf1, 1, 0);
        gp1.add(l3, 0, 1);
        gp1.add(tf2, 1, 1);
        gp1.setHgap(5);
        gp1.setVgap(10);
        gp1.setAlignment(Pos.CENTER);

        button.setOnAction(e -> {
            if (!isInteger(tf1.getText()) || !isInteger(tf2.getText())) {
                l4.setText("Unesite brojeve");
                return;
            }
            ;
            int brojTermina = Integer.parseInt(tf1.getText());
            int brojStudenata = Integer.parseInt(tf2.getText());
            long brojMesta;
            if (cb.isSelected()) {
                brojMesta = MainUcionice.ucionice.stream()
                        .filter(x -> x.getTip() != null)
                        .filter(x -> x.getTip().equals("R"))
                        .count();
            } else {
                brojMesta = MainUcionice.ucionice.stream()
                        .mapToInt(x -> Objects.equals(x.getTip(), "A")
                                ? brojStudenata * 2
                                : brojStudenata)
                        .sum();
            }
            if (brojMesta < brojStudenata) {
                l4.setText("Nema dovoljno ucionica");
                tf1.clear();
                tf2.clear();
            } else {
                MainUcionice.ucionice.stream()
                        .forEach(x -> x.setBrojMesta(
                                Objects.equals(x.getTip(), "A") ? brojStudenata * 2 :
                                        brojStudenata));
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(new DrugiProzor().getScene(brojTermina));
            }
        });

        vbox.getChildren().add(l1);
        vbox.getChildren().add(gp1);
        vbox.getChildren().add(cb);
        vbox.getChildren().add(button);
        vbox.getChildren().add(l4);

        vbox.setPrefSize(400, 300);
        vbox.setSpacing(20);
        vbox.setPadding(new Insets(30, 20, 30, 20));
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox);
        return scene;
    }

    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }
}
