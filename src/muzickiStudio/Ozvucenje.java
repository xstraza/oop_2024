package muzickiStudio;

public class Ozvucenje extends Oprema {
    private double cena;

    public Ozvucenje(String naziv, double cena) {
        super(naziv);
        this.cena = cena;
    }

    public double cena(int x) {
        return 0;
    }

    @Override
    public int compareTo(Oprema o) {
        return 0;
    }

    @Override
    public String toString() {
        return "Instrument{" +
                "cena=" + cena +
                '}';
    }
}
