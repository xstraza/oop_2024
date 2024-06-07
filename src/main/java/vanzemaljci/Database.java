package vanzemaljci;

import filmovi.DataReader;
import vanzemaljci.Alien;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database {

    private static Map<String, List<String>> continents = new HashMap<>();
    private static List<Alien> aliens = new ArrayList<>();

    public static void readFile() {
        int state = 0;
        String continent = "";
        try (InputStream inputStream = DataReader.class.getResourceAsStream("/vanzemaljci.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Kontinent:")) {
                    continent = line.split(": ")[1];
                } else if (line.contains("IZGUBLJENI")) {
                    state = 1;
                } else if (line.contains("PRONADJENI")) {
                    state = 2;
                } else {
                    if (state == 0) {
                        if (continents.get(continent) == null) {
                            List<String> lista = new ArrayList<>();
                            lista.add(line);
                            continents.put(continent, lista);
                        } else {
                            List<String> strings = continents.get(continent);
                            strings.add(line);
                            continents.put(continent, strings);
                        }
                    }
                    if (state == 1) {
                        String[] split = line.split(";");
                        aliens.add(new Alien(Integer.parseInt(split[0]), split[1], null, false));
                    }
                    if (state == 2) {
                        String[] split = line.split(";");
                        String kantri = split[1];
                        String kontinent = "ERROR";
                        for (Map.Entry<String, List<String>> entry : continents.entrySet()) {
                            if (entry.getValue().contains(kantri)) {
                                kontinent = entry.getKey();
                            }
                        }
                        aliens.add(new Alien(Integer.parseInt(split[0]), kontinent, kantri, true));
                    }
                }

            }
            for (Map.Entry<String, List<String>> entry : continents.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
            aliens.forEach(System.out::println);
            System.out.println("-----");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, List<String>> getContinents() {
        return continents;
    }

    public static List<Alien> getAliens() {
        return aliens;
    }
}
