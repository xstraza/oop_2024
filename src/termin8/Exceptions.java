package termin8;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class Exceptions {
    private Integer ime;

    public Optional<Integer> getIme() {
        return Optional.ofNullable(ime);
    }

    private static final int BUFFER_SIZE = 4096;

    public static void main(String[] args) {
//        Optional<Integer> x = Optional.of(2);
//        Optional<Integer> z = Optional.ofNullable(2);
        Optional<Integer> x = Optional.of(null);
        Optional<Integer> z = Optional.ofNullable(null);

        try {
            x = x();
        } catch (Exception ignore) {

        }
        if (x.isPresent()) {
            Integer i = x.get();
            System.out.println(i);
        } else {

        }

        if (args.length < 2) {
            System.out.println("Please provide input and output files");
            System.exit(0);
        }

        String inputFile = args[0];
        String outputFile = args[1];
//        Integer.parseInt()
        try (
                InputStream inputStream = new FileInputStream(inputFile);
                OutputStream outputStream = new FileOutputStream(outputFile);
        ) {
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead = -1;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }

    private static void y() throws ClassNotFoundException {
        x();
    }

    private static Optional<Integer> x() throws ClassNotFoundException {
        List<Integer> list = new ArrayList<>();
        list.add(1);
//        throw new ClassNotFoundException()
        return list.stream()
                .filter(x -> x > 10)
                .findFirst();
    }

    private static double koren(int x) {
        if (x < 0) {
            throw new IllegalArgumentException();
        }
        return Math.sqrt(x);
    }

    public static Optional<Integer> prviBrojVeciOd10(List<Integer> lista) {
//    public static Integer prviBrojVeciOd10(List<Integer> lista) {
        return lista.stream()
                .filter(x-> x > 10)
                .findFirst();
    }
}
