package kolokvijumi.muzickiStudio;

public class Instrument extends Oprema {
    private double osnovnaCena;
    private KlasaInstrumenta klasa;

    public Instrument(String naziv, KlasaInstrumenta klasa, double osnovnaCena) {
        super(naziv);
        this.klasa = klasa;
        this.osnovnaCena = osnovnaCena;
    }

    public double getOsnovnaCena() {
        return osnovnaCena;
    }

    public KlasaInstrumenta getKlasa() {
        return klasa;
    }

    @Override
    public double cena(int brojSati) {
        return brojSati * osnovnaCena * klasa.getKoeficijent();
    }

    @Override
    public String toString() {
        return getNaziv() + " " + klasa.name() + " klase";
    }
}
