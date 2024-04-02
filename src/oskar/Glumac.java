package oskar;

import java.util.Date;
import java.util.Random;

public class Glumac extends ClanEkipe {
    private boolean lepGlas;

    public Glumac(String ime, String prezime, Date datumRodjenja, Pol pol) {
        super(ime, prezime, datumRodjenja, pol);
        this.lepGlas = new Random().nextBoolean();
    }

    @Override
    public void odrziGovor() {
        System.out.println("govor glumca");
    }

    @Override
    public void predstavi() {

    }

    @Override
    public void primiOskara() {
        float chance = new Random().nextFloat();
        if (chance > 0.1) {
            odrziGovor();
        }
    }

    @Override
    public void pripremiGovor() {

    }

    @Override
    public String toString() {
        return "Glumac{" +
                "lepGlas=" + lepGlas +
                '}';
    }

    public boolean isLepGlas() {
        return lepGlas;
    }
}
