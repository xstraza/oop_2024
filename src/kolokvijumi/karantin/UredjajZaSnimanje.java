package kolokvijumi.karantin;

import java.util.Objects;

public abstract class UredjajZaSnimanje {
    private String proizvodjac;
    private TipSnimka tipSnimka;
    private String vlasnik;

    public UredjajZaSnimanje(String proizvodjac, TipSnimka tipSnimka, String vlasnik) {
        this.proizvodjac = proizvodjac;
        this.tipSnimka = tipSnimka;
        this.vlasnik = vlasnik;
    }

    public abstract void sacuvajSnimak(Snimak snimak, Sastanak sastanak, String name);

    public abstract void pokreniSnimak(Snimak snimak);

    public abstract void zavrsiSnimak(Snimak snimak);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UredjajZaSnimanje that = (UredjajZaSnimanje) o;

        if (!Objects.equals(proizvodjac, that.proizvodjac)) return false;
        if (tipSnimka != that.tipSnimka) return false;
        return Objects.equals(vlasnik, that.vlasnik);
    }

    @Override
    public int hashCode() {
        int result = proizvodjac != null ? proizvodjac.hashCode() : 0;
        result = 31 * result + (tipSnimka != null ? tipSnimka.hashCode() : 0);
        result = 31 * result + (vlasnik != null ? vlasnik.hashCode() : 0);
        return result;
    }
}
