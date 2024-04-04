package termin1;

import java.util.Scanner; //importi idu iznad klase.
// uglavnom ih intellij sam odradi u pozadini, ako cekirate tu opciju u podesavanjima, sto je preporuceno
// ako pokusate da importujete neku klasu a da postoje dve ili vise klasa sa tim imenom
// intellij ce traziti da odaberete koju. ovo se cesto desava sa listama (java.awt.List i java.util.List)

public class Main { //ova klasa se zove Main ali to nema veze sa main metodom,
    // klasa se moze zvati kako god, bilo koja klasa moze imati main

    public static void main(String[] args) { //kod se pokrece iz pubic static void main metode
        //moze postojati vise main-ova u razlicitim fajlovima koji mogu raditi potpuno razlicite stvari
        //u praksi je dovoljan jedan main

        System.out.println("hello world!");

        Scanner scanner = new Scanner(System.in); // scanner je klasa koju koristimo za citanje sa standardnog ulaza
        //konstruktoru moramo proslediti InputStream, sto je apstraktna klasa. Za to koristimo staticko polje 'in'
        //klase System.

        //nakon instanciranja imamo razne metode za citanje podataka iz konzole
        int i = scanner.nextInt(); //uzima iz unosa sledeci token/'rec' i pokusa da castuje u integer
        boolean b = scanner.nextBoolean(); //analogno za boolean
        double v = scanner.nextDouble();  //analogno za double
        //ukoliko je castovanje nemoguce dobicemo exception

        String s = scanner.nextLine(); //alternativno mozemo ucitati celu liniju do /n
        //pa nju transformisati dalje po potrebi.
        s += " promenili smo string";
        System.out.println(s);
    }
}
