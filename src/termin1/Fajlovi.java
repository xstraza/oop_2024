package termin1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Fajlovi {

    public static void main(String[] args) {
        String csvFile = "resources/users.csv"; // putanja do fajla
        //csv je jedan od najcescih formata tekstualnih fajlova koje obradjujemo programski
        //pored csv-a, cesti su i json i xml formati.
        // csv je skracenica od comma-separated values. dakle imamo opcioni header red na pocetku fajla
        // koji opisuje imena kolona. zatim idu redovi teksta, gde je svaki red podeljen na segmente separatorom.
        // separator je najcesce ',' ali moze biti i '\t' i ';' i ' ' itd.
        String line = ""; // ovde cemo cuvati trenutnu linju koju obradjujemo
        String csvSplitBy = ","; // moramo reci splitu po cemu da razdvoji red teksta.

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            //da bi otvorili fajl, moram da imamo FileReader kojem prosledjujemo putanju do fajla
            //FileReader onda prosledjujemo u konstruktor BufferedReader-a. BufferedReader je klasa
            //slicn scanneru, ima meteode za citanje linije teksta
            String header = br.readLine();//header procitamo da bi stigli do sledecg reda
            //cesto nam sam header nije potreban u kodu jer fajl otvorimo i sami vidimo koja je koja kolona

            while ((line = br.readLine()) != null) { //slicno kako je u c-u radjeno
                String[] userData = line.split(csvSplitBy); //koristimo split metodu klase String
                //split razdvaja string na niz stringova. Razdavaja ih po regex-u
                //regex je regularni izraz, nesto sto se mnogo detaljnije uci u trecoj godini
                //za vecinu slucajeva to sto je regex a ne obican string ne pravi razliku ali
                //ako dobijate rezultate koji vam nemaju smisla moguce je da je problem u tome sto ste napisali
                //regex koji znaci nesto sto niste hteli.


                String username = userData[0]; //kada smo razdvojili string na niz stringova, pristupamo elemntima po indeksu
                String email = userData[1]; //posto znamo strukturu csv-a, znamo na kom mestu se nalazi koja vrednost
                String id = userData[2];
                String name = userData[3];

                System.out.println("Username: " + username);
                System.out.println("Email: " + email);
                System.out.println("ID: " + id);
                System.out.println("Name: " + name);
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
