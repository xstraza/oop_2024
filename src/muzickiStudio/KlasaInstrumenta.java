package muzickiStudio;

public enum KlasaInstrumenta {
    A, AA, AAA;

    public double getKoeficijent() {
        if (this.equals(A)) return 1;
        if (this.equals(AA)) return 1.4;
        return 1.8;
    }
}
