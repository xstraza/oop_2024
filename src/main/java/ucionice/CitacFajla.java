package ucionice;

import filmovi.DataReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CitacFajla {

    public static List<Student> ucitajStudente() {
        List<Student> students = new ArrayList<>();

        try (InputStream inputStream = DataReader.class.getResourceAsStream("/studenti1.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String smer = parts[0].trim();
                    int brojIndeksa = Integer.parseInt(parts[1].trim());
                    int godinaUpisa = Integer.parseInt(parts[2].trim());
                    String prezime = parts[3].trim();
                    String ime = parts[4].trim();
                    Student student = new Student(smer, brojIndeksa, godinaUpisa, prezime, ime);
                    students.add(student);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Student s : students) {
            System.out.println(s);
        }
        return students;
    }

    public static List<Ucionica> ucitajUcionice() {
        List<Ucionica> ucionice = new ArrayList<>();

        try (InputStream inputStream = DataReader.class.getResourceAsStream("/ucionice1.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("-");
                Ucionica ucionica = parts.length == 2
                        ? new Ucionica(parts[0].trim(), parts[1].trim())
                        : new Ucionica(line, null);
                ucionice.add(ucionica);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Ucionica u : ucionice) {
            System.out.println(u);
        }
        return ucionice;
    }
}
