package oskar;

import java.util.Date;

public abstract class ClanEkipe implements Nominovan {
    private String ime;
    private String prezime;
    private Date datumRodjenja;
    private Pol pol;

    public ClanEkipe(String ime, String prezime, Date datumRodjenja, Pol pol) {
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.pol = pol;
    }

    public abstract void odrziGovor();

    @Override
    public String toString() {
        return "ClanEkipe{" +
                "ime='" + ime + '\'' +
                ", preime='" + prezime + '\'' +
                ", datumRodjenja=" + datumRodjenja +
                ", pol=" + pol +
                '}';
    }
}
