package kolokvijumi.muzickiStudio;

public class Kamera extends Oprema {
    private TipKamere tipKamere;
    private double fiksnaCena;

    public Kamera(String naziv, TipKamere tipKamere, double fiksnaCena) {
        super(naziv);
        this.tipKamere = tipKamere;
        this.fiksnaCena = fiksnaCena;
    }

    @Override
    public double cena(int brojSati) {
        return fiksnaCena;
    }

    @Override
    public String toString() {
        return "Kamera " + getNaziv() + " " + tipKamere.name();
    }

    public TipKamere getTipKamere() {
        return tipKamere;
    }

    public double getFiksnaCena() {
        return fiksnaCena;
    }
}
