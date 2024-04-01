package muzickiStudio;

public class Instrument extends Oprema {
    private double osnovnaCena;

    public Instrument(String naziv, double osnovnaCena) {
        super(naziv);
        this.osnovnaCena = osnovnaCena;
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
                "osnovnaCena=" + osnovnaCena +
                '}';
    }
}
