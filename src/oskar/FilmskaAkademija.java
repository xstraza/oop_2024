package oskar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FilmskaAkademija {
    private final double MIN_REJTING = 5;

    private List<Glumac> glumci = new ArrayList<>();
    private List<Glumac> glumice = new ArrayList<>();
    private List<Reziser> reziseri = new ArrayList<>();
    private List<Film> filmovi = new ArrayList<>();

    public void dodelaOskara() {
        predstaviNominovane();
        if (glumci.size() >= 1 && glumice.size() >= 1 && reziseri.size() >= 1 && filmovi.size() >= 1) {
            dodeliOskare();
        }
    }

    public boolean dodajNominaciju(Nominovan nominovan) {
        if (nominovan instanceof Glumac) {
            Glumac glumac = (Glumac) nominovan;
            if (glumci.contains(glumac)) {
                return false;
            }
            if (glumac.getPol().equals(Pol.MUSKI)) {
                glumci.add(glumac);
            } else {
                glumice.add(glumac);
            }
            return true;
        }
        if (nominovan instanceof Film) {
            Film film = (Film) nominovan;
            if (filmovi.contains(film)) {
                return false;
            }
            if (film.izracunajRatingFilma() < MIN_REJTING) {
                return false;
            }
            if (film.getZanr().equals(Zanr.BIOGRAFSKI) || film.getZanr().equals(Zanr.DOKUMENTARNI)) {
                if (film.getTrajanje() > 2.5) {
                    return false;
                }
            }
            filmovi.add(film);
            return true;
        }
        if (nominovan instanceof Reziser) {
            Reziser reziser = (Reziser) nominovan;
            if (reziseri.contains(reziser)) {
                return false;
            }
            reziseri.add(reziser);
            return true;
        }
        return false;
    }

    private void dodeliOskare() {
        Random r = new Random();
        int pobednikGlumac = r.nextInt(glumci.size());
        glumci.get(pobednikGlumac).primiOskara();
        int pobednikGlumica = r.nextInt(glumice.size());
        glumice.get(pobednikGlumica).primiOskara();
        int pobednikReziser = r.nextInt(reziseri.size());
        reziseri.get(pobednikReziser).primiOskara();
        int pobednikFilm = r.nextInt(filmovi.size());
        reziseri.get(pobednikFilm).primiOskara();
    }

    private void predstaviNominovane() {
        System.out.println("kategorija glumci");
        for (Glumac glumac : glumci) {
            glumac.predstavi();
        }
        System.out.println("kategorija glumice");
        for (Glumac glumica : glumice) {
            glumica.predstavi();
        }
        System.out.println("kategorija reziseri");
        for (Reziser reziser : reziseri) {
            reziser.predstavi();
        }
        System.out.println("kategorija filmovi");
        for (Film film : filmovi) {
            film.predstavi();
        }
    }
}
