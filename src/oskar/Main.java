package oskar;

import java.util.Date;

public class Main {

    public static void main(String[] args) {
        Glumac glumac1 = new Glumac("i1", "p1", new Date(), Pol.MUSKI);
        Glumac glumac2 = new Glumac("i2", "p2", new Date(), Pol.MUSKI);
        Glumac glumac3 = new Glumac("i3", "p3", new Date(), Pol.ZENSKI);
        Glumac glumac4 = new Glumac("i4", "p4", new Date(), Pol.ZENSKI);

        Reziser reziser1 = new Reziser("r1","rr1", new Date(), Pol.MUSKI);
        Reziser reziser2 = new Reziser("r2","rr2", new Date(), Pol.ZENSKI);
        Reziser reziser3 = new Reziser("r3","rr3", new Date(), Pol.MUSKI);

        Kriticar kriticar = new Kriticar("k1", "k2", 5);

        Film film1 = new Film("film1", 2024, 100, reziser1, Zanr.HOROR);
        film1.dodajGlumca(glumac2);
        film1.dodajGlumca(glumac3);
        film1.dodajGlumca(glumac3);
        Film film2 = new Film("film2", 1990, 70, reziser1, Zanr.MJUZIKL);
        film2.dodajGlumca(glumac1);
        film2.dodajGlumca(glumac2);
        Film film3 = new Film("film3", 2005, 120, reziser1, Zanr.AVANTURA);
        film3.dodajGlumca(glumac4);

        kriticar.oceniFilm(film1, 6);
        kriticar.oceniFilm(film2, 7);
        kriticar.oceniFilm(film3, 8);

        System.out.println(film1.izracunajRatingFilma());
        System.out.println(film2.izracunajRatingFilma());
        System.out.println(film3.izracunajRatingFilma());

        FilmskaAkademija fa = new FilmskaAkademija();
        fa.dodajNominaciju(glumac1);
        fa.dodajNominaciju(glumac2);
        fa.dodajNominaciju(glumac2);
        fa.dodajNominaciju(glumac3);
        fa.dodajNominaciju(glumac4);

        fa.dodajNominaciju(reziser1);
        fa.dodajNominaciju(reziser2);
        fa.dodajNominaciju(reziser3);

        fa.dodajNominaciju(film1);
        fa.dodajNominaciju(film2);
        fa.dodajNominaciju(film3);

        fa.dodelaOskara();
        fa.generisiIzvestajUFajl();
    }
}
