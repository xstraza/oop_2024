package predstava;

public class Plesac extends Ucesnik {
    private String uloga;
    private Velicina velicina;

    public Plesac(String ime, String uloga, Velicina velicina) {
        super(ime);
        this.uloga = uloga;
        this.velicina = velicina;
    }

    @Override
    public String toString() {
        return "Plesac{" +
                "uloga='" + uloga + '\'' +
                ", velicina=" + velicina +
                '}';
    }
}
