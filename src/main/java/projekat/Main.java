package projekat;

import filmovi.DataReader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ucionice.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Main extends Application {
    public static List<Akcija> akcije;
    public static List<Osoba> osobe;
    public static Map<Osoba, List<Akcija>> akcijeZaOsobu = new HashMap<>();

    public static void main(String[] args) {
        akcije = ucitajAkcije();
        osobe = akcije.stream()
                .map(Akcija::getOsoba)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        akcijeZaOsobu = osobe.stream()
                .collect(Collectors.toMap(
                        x -> x,
                        x -> akcije.stream().filter(akcija -> akcija.getOsoba().equals(x)).collect(Collectors.toList())
                ));
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new VelikiProzor().getScene();
        primaryStage.setScene(scene);
        primaryStage.setTitle("OOP Jul 20/21");
        primaryStage.show();
    }

    private static List<Akcija> ucitajAkcije() {
        List<Akcija> akcije = new ArrayList<>();

        try (InputStream inputStream = DataReader.class.getResourceAsStream("/skola.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String strTipAkcije = parts[0].trim();
                    TipAkcije tipAkcije = strTipAkcije.contains("-")
                            ? TipAkcije.getTip(strTipAkcije.split("-")[0])
                            : TipAkcije.getTip(strTipAkcije);
                    Double iznos = strTipAkcije.contains("-")
                            ? Double.parseDouble(strTipAkcije.split("-")[1])
                            : null;
                    System.out.println(parts[1]);
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
                    Date datum = formatter.parse(parts[1]);
                    String prezime = parts[2].trim();
                    String ime = parts[3].trim();
                    Osoba osoba = new Osoba(ime, prezime);
                    Akcija akcija = new Akcija(osoba, tipAkcije, datum, iznos);
                    akcije.add(akcija);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        for (Akcija s : akcije) {
            System.out.println(s);
        }
        return akcije;
    }

}
