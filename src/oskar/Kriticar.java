package oskar;

public class Kriticar {
    private String ime;
    private String prezime;
    private double reputacija;

    public Kriticar(String ime, String prezime, double reputacija) {
        this.ime = ime;
        this.prezime = prezime;
        if (reputacija > 100) {
            throw new IllegalArgumentException("reputacija ne moze biti veca od 100");
        }
        this.reputacija = reputacija;
    }

    public void oceniFilm(Film film, double ocena) {
        film.getKritike().add(new Kritika(film, this, ocena));
    }

    public void pohvaliKriticara(Kriticar kriticar) {
        kriticar.povecajReputaciju(this.reputacija * 0.1);
    }

    private void povecajReputaciju(double kolicina) {
        this.reputacija+=kolicina;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public double getReputacija() {
        return reputacija;
    }
}
