package oskar;

import java.util.ArrayList;
import java.util.List;

public class Film {
    private String naziv;
    private int godina;
    private int trajanje;
    private List<ClanEkipe> clanoviEkipe = new ArrayList<>();
    private Zanr zanr;

    public Film(String naziv, int godina, int trajanje, Zanr zanr) {
        this.naziv = naziv;
        this.godina = godina;
        if (trajanje <= 0) {
            throw new IllegalArgumentException("trajanje mora biti pozitivno");
        }
        this.trajanje = trajanje;
        this.zanr = zanr;
    }

    public boolean dodajGlumca(Glumac glumac) {
        if (!clanoviEkipe.contains(glumac)) {
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

    @Override
    public String toString() {
        return "Film{" +
                "naziv='" + naziv + '\'' +
                ", godina=" + godina +
                ", clanoviEkipe=" + clanoviEkipe +
                ", zanr=" + zanr +
                '}';
    }
}
