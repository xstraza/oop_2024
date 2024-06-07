package transakcije;

import filmovi.DataReader;
import transakcije.Transaction;
import transakcije.TransactionType;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Transakcije {

    public static List<Transaction> readFile() {
        List<Transaction> transactions = new ArrayList<>();

        try (InputStream inputStream = DataReader.class.getResourceAsStream("/placanja.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                int amount = Integer.parseInt(parts[0].trim());
                String senderReceiver = parts[1].trim();
                String date = parts[2].trim();
                TransactionType transactionType = parts.length == 4 ? TransactionType.ISPLATA : TransactionType.UPLATA;
                String category = parts.length == 4 ? parts[3].trim() : null;
                Transaction transaction = new Transaction(amount, senderReceiver, date, transactionType, category);
                transactions.add(transaction);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Transaction t : transactions) {
            System.out.println(t);
        }
        return transactions;
    }

    public static void saveFiles(List<Transaction> transactions) {
        List<Transaction> isplate = transactions.stream()
                .filter(x -> x.getTransactionType().equals(TransactionType.ISPLATA))
                .collect(Collectors.toList());

        writeToFile(isplate, "isplate.txt");

        List<Transaction> uplate = transactions.stream()
                .filter(x -> x.getTransactionType().equals(TransactionType.UPLATA))
                .collect(Collectors.toList());
        writeToFile(uplate, "uplate.txt");
    }

    private static void writeToFile(List<Transaction> transactions, String filename) {
        try (PrintWriter writer = new PrintWriter(filename, StandardCharsets.UTF_8)) {
            for (Transaction transaction : transactions) {
                String category = transaction.getCategory() != null ? transaction.getCategory() : "";
                writer.println(transaction.getAmount() + ";" + transaction.getSenderReceiver() + ";" + transaction.getDate() + ";" + category);
            }
            int sum = transactions.stream().mapToInt(Transaction::getAmount).sum();
            writer.println(sum);
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
}
