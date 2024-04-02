package oskar;

import java.util.Date;

public class Reziser extends ClanEkipe {
    private int brojFilmova = 0;

    public Reziser(String ime, String prezime, Date datumRodjenja, Pol pol) {
        super(ime, prezime, datumRodjenja, pol);
    }

    @Override
    public void odrziGovor() {
        System.out.println("govor rezisera");
    }

    @Override
    public void predstavi() {

    }

    @Override
    public void primiOskara() {
        odrziGovor();
    }

    @Override
    public void pripremiGovor() {

    }

    public void povecajBrojFilmova() {
        this.brojFilmova++;
    }
}
