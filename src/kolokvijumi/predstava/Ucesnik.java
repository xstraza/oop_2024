package kolokvijumi.predstava;

import java.util.Objects;

public abstract class Ucesnik {
    private String ime;

    public Ucesnik(String ime) {
        this.ime = ime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ucesnik ucesnik = (Ucesnik) o;

        return Objects.equals(ime, ucesnik.ime);
    }

    @Override
    public int hashCode() {
        return ime != null ? ime.hashCode() : 0;
    }

    public String getIme() {
        return ime;
    }
}
