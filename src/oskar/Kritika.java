package oskar;

public class Kritika {
    private Film film;
    private Kriticar kriticar;
    private double ocena;

    public Kritika(Film film, Kriticar kriticar, double ocena) {
        this.film = film;
        this.kriticar = kriticar;
        this.ocena = ocena;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Kriticar getKriticar() {
        return kriticar;
    }

    public void setKriticar(Kriticar kriticar) {
        this.kriticar = kriticar;
    }

    public double getOcena() {
        return ocena;
    }

    public void setOcena(double ocena) {
        this.ocena = ocena;
    }
}
