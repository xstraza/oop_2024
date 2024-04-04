package kolokvijumi.predstava;

public class Plesac extends Ucesnik {
    private String uloga;
    private Velicina velicina;

    public Plesac(String ime, boolean solista, Velicina velicina) {
        super(ime);
        this.uloga = solista ? "SOLISTA" : "PRATNJA";
        this.velicina = velicina;
    }

    @Override
    public String toString() {
        return "PLESAC-" + uloga;
    }

    public String getUloga() {
        return uloga;
    }

    public Velicina getVelicina() {
        return velicina;
    }
}
