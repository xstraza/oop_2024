package muzickiStudio;

public abstract class Oprema implements Comparable<Oprema> {
    private String naziv;

    public Oprema(String naziv) {
        this.naziv = naziv;
    }
}
