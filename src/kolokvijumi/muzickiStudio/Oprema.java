package kolokvijumi.muzickiStudio;

public abstract class Oprema implements Procenjivo, Comparable<Oprema> {
    private String naziv;

    public Oprema(String naziv) {
        this.naziv = naziv;
    }

    public String getNaziv() {
        return naziv;
    }

    @Override
    public int compareTo(Oprema other) {
        return naziv.toLowerCase().compareTo(other.naziv.toLowerCase());
    }

}
