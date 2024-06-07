package termin2;

import java.util.*;

public class Stringovi {

    public static void main(String[] args) {
        zadatak1();
        zadatak2();
    }

    /*
    * (7p) Upotrebom naredbe while i funkcijom getchar() za čitanje karaktera obraditi ulaznu
    sekvencu koja se sastoji od slova i cifara tako da brojevi koji su okruženi identičnim
    karakterom slova se ispišu tako da se svaka cifra u broju uveća za poziciju te cifre unutar
    broja (cifra najveće težine ima poziciju 1 u broju). Umesto brojeva koji su okruženi
    karakterom slova treba da se ispiše ostatak pri deljenju datog broja kvadratom broja cifara.
    Napomena : Mala i velika slova se posmatraju ako različita ‘a’ != ‘A’.
    Karakteri slova se regularno ispisuju.
    Ograničenje : broj neće počinjati cifrom 0 i uvek će biti između slova.
    Primer: Ulaz : ca123ab15C3dE782EfG1228g
    Izlaz : ca246ab3C0dE805EfG12g
    Pojašenje primera se nalazi na drugoj strani.
    * */
    public static void zadatak1() {
        //radi lakseg testiranja, necemo svaki put unositi string sa standardnog ulaza vec cemo ga fiksirati
//        Scanner scanner = new Scanner(System.in);
//        String line = scanner.nextLine();
        String line = "ca123ab15C3dE782EfG1228g";
        StringBuilder result = new StringBuilder(); //StringBuilder klasu bi trebalo da koristimo svaki put kada
        //string gradimo iz vise koraka, konkatenacijom
        //razlog za koriscenje Sb-a je javim string pool. Bez Sb-a, savka konkatenacija bi napravila nov string u poolu
        // i time bi zauzimali nepotrebno mnogo memorije. Intellij uglavnom zna da vam predlozi da iskoristite StringBuilder
        //umesto iterativne konkatenacije stringova.
        Character c = null;
        int pozicija_c = -1;
        boolean lastIsDigit = false;
        for (int i = 0; i < line.length(); i++) { //for petlje rade i izgledaju isto kao u c-u
            if (Character.isDigit(line.charAt(i))) { //pomocna metoda za proveru karaktera, postoje slicne i za druge tipove karaktera
                if (!lastIsDigit) { //if-else blok izgleda i radio kao i u c-u
                    c = line.charAt(i - 1);
                    pozicija_c = i;
                    lastIsDigit = true;
                }
            } else {
                if (lastIsDigit) {
                    if (line.charAt(i) == c) {
                        String broj = line.substring(pozicija_c, i); // vraca nam,podstring string koji pocinje od prvog argumenta a zavrsava se odmah pre drugog
                        StringBuilder noviBroj = new StringBuilder();
                        int x = 0;
                        int j = 1;
//                        for (int j = 1; j < broj.length() + 1; j++) {
                        while (j < broj.length() + 1) { //ovde bi iskoristili for petlju pre while-a, ali
                            //primera radi, while petlja izgleda i radi kao i u c-u. isto vazi i za do-while.
                            String substring = broj.substring(x, x + 1);
                            int i1 = Integer.parseInt(substring); //castuje string u int
                            i1 += j;
                            if (i1 > 9) {
                                i1 %= 10;
                            }
                            Character cc = new Character((char) (i1 + '0')); //ova metoda je deprecated u novijim verzijama jave, zato imamo warning
                            //upozorava nas da je moguce da ce biti obrisana u novijim verzijama jave
                            noviBroj.append(cc);
                            x++;
                            j++;
                        }
                        result.append(noviBroj);
                    } else {
                        String broj = line.substring(pozicija_c, i);
                        int delilac = broj.length() * broj.length();
                        Integer noviBroj = Integer.parseInt(broj) % delilac;
                        result.append(noviBroj);
                    }
                }
                lastIsDigit = false;
                result.append(line.charAt(i));
            }
        }
        System.out.println(result);
        System.out.println("ca246ab3C0dE805EfG12g");
    }

    //za unetu recenicu, prvu rec pretvoriti u velika slova, drugu rec obrnuti redosled, za trecu rec permutujemo slova i tako redom
    private static void zadatak2() {
//        Scanner scanner = new Scanner(System.in);
//        String line = scanner.nextLine();
        String line = "za unetu recenicu, pr5vu rec pretvoriti u velika slova, drugu rec obrnuti redosled, za trecu rec permutujemo slova i tako redom";
        String[] splitted = line.split(" "); //dobijamo niz stringova. nizovi funkcionisu kao u c-u.
        //line.length(); - klasa String ima metodu length pa se poziva sa ()
        //splitted.length - dok niz posto nije klasa ima polje length, poziva se bez ()
        StringBuilder sb = new StringBuilder();
        int changeType = 0;
        for (String s : splitted) {
            String changedWord = changeWord(s, changeType);
            sb.append(changedWord);
            sb.append(" ");
            changeType = (changeType + 1) % 3;
        }
        System.out.println(sb);
    }

    private static String changeWord(String wordToChange, int changeType) {
//        return switch (changeType) { //switch case funkcionise isto kao u c-u
//            case 0: capitalizeWord(wordToChange); //ovo je noviji nacin zapisivanja swithca, iz jave 14
//                break;
//            case 1: invertWord(wordToChange);break; // u starijim verzijama jave je izgledao kao i u c-u, sa break-om u svakoj grani
//            case 2: permuteWord(wordToChange);
//            default: null; //mozemo imati default granu koja se izvrsi ako se ne udje ni u jednu drugu granu
//        };
        return null;
    }

    private static String permuteWord(String wordToPermute) {
        List<Character> karakteri = new ArrayList<>();
        for (char c : wordToPermute.toCharArray()) {
            karakteri.add(c);
        }
        Collections.shuffle(karakteri); // Collections klasa ima korisne staticke metode za rad sa kolekcijama,
        //najcesce koristimo Collections.sort();
        StringBuilder novi = new StringBuilder();
        for (Character character : karakteri) {
            novi.append(character);
        }
        return novi.toString();
    }

    private static String invertWord(String s) {
        return new StringBuilder(s).reverse().toString();
    }

    private static String capitalizeWord(String s) {
        return s.toUpperCase();
    }

}

