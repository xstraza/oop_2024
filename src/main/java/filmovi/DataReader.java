package filmovi;

import filmovi.Movie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DataReader {

    public static List<Movie> readFile() {
        List<Movie> movies = new ArrayList<>();

        try (InputStream inputStream = DataReader.class.getResourceAsStream("/data.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String title = parts[0].trim();
                    int phase = Integer.parseInt(parts[1].trim());
                    double rating = Double.parseDouble(parts[2].trim());
                    int year = Integer.parseInt(parts[3].trim());
                    int duration = Integer.parseInt(parts[4].trim());
                    Movie movie = new Movie(title, phase, rating, year, duration);
                    movies.add(movie);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Print the movies to verify the result
        for (Movie movie : movies) {
            System.out.println(movie);
        }
        return movies;
    }
}
