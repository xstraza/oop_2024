package projekat;

import java.util.Objects;

public class Osoba implements Comparable<Osoba> {
    private String ime;
    private String prezime;

    public Osoba(String ime, String prezime) {
        this.ime = ime;
        this.prezime = prezime;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    @Override
    public String toString() {
        return prezime + " " + ime;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Osoba) {
            Osoba obj1 = (Osoba) obj;
            return obj1.getIme().equals(this.getIme()) && obj1.getPrezime().equals(this.getPrezime());
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(ime);
        result = 31 * result + Objects.hashCode(prezime);
        return result;
    }

    @Override
    public int compareTo(Osoba o) {
        return this.toString().compareTo(o.toString());
    }
}
