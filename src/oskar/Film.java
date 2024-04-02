package oskar;

import java.util.ArrayList;
import java.util.List;

public class Film implements Nominovan {
    private String naziv;
    private int godina;
    private double trajanje;
    private List<ClanEkipe> clanoviEkipe = new ArrayList<>();
    private Zanr zanr;
    private Reziser predstavnik;
    private List<Kritika> kritike = new ArrayList<>();

    public Film(String naziv, int godina, double trajanje, Reziser reziser, Zanr zanr) {
        this.naziv = naziv;
        this.godina = godina;
        if (trajanje <= 0) {
            throw new IllegalArgumentException("trajanje mora biti pozitivno");
        }
        this.trajanje = trajanje;
        this.zanr = zanr;
        this.clanoviEkipe.add(reziser);
        reziser.povecajBrojFilmova();
        this.predstavnik = reziser;
    }

    public float izracunajRatingFilma() {
        float sum = 0;
        for (Kritika kritika : kritike) {
            sum += kritika.getOcena() * kritika.getKriticar().getReputacija();
        }
        return sum / kritike.size();
    }

    public boolean dodajGlumca(Glumac glumac) {
        if (clanoviEkipe.contains(glumac)) {
            return false;
        }
        if (zanr.equals(Zanr.MJUZIKL) || zanr.equals(Zanr.ANIMIRANI)) {
            if (!glumac.isLepGlas()) {
                return false;
            }
        }
        clanoviEkipe.add(glumac);
        return true;
    }

    public boolean dodajRezisera(Reziser reziser) {
        if (!clanoviEkipe.contains(reziser)) {
            return false;
        }
        reziser.povecajBrojFilmova();
        clanoviEkipe.add(reziser);
        if (reziser.getDatumRodjenja().before(predstavnik.getDatumRodjenja())) {
            predstavnik = reziser;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Film{" +
                "naziv='" + naziv + '\'' +
                ", godina=" + godina +
                ", clanoviEkipe=" + clanoviEkipe +
                ", zanr=" + zanr +
                '}';
    }

    @Override
    public void predstavi() {

    }

    @Override
    public void primiOskara() {
        this.predstavnik.primiOskara();
    }

    @Override
    public void pripremiGovor() {

    }

    public String getNaziv() {
        return naziv;
    }

    public int getGodina() {
        return godina;
    }

    public double getTrajanje() {
        return trajanje;
    }

    public List<ClanEkipe> getClanoviEkipe() {
        return clanoviEkipe;
    }

    public Zanr getZanr() {
        return zanr;
    }

    public Reziser getPredstavnik() {
        return predstavnik;
    }

    public List<Kritika> getKritike() {
        return kritike;
    }
}
