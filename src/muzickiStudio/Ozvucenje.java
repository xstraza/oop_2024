package muzickiStudio;

public class Ozvucenje extends Oprema {
    private double cena;

    public Ozvucenje(String naziv, double cena) {
        super(naziv);
        this.cena = cena;
    }

    @Override
    public double cena(int brojSati) {
        return cena * brojSati;
    }

    @Override
    public int compareTo(Oprema o) {
        return 0;
    }

    @Override
    public String toString() {
        return getNaziv();
    }
}
