package karantin;

import java.util.ArrayList;
import java.util.List;

public class Sastanak implements Comparable<Sastanak> {
    private String predmet;
    private int satPocetka;
    private int satZavrsetka;
    private boolean aktivan;
    private List<UcesnikSastanka> ucesnici = new ArrayList<>();
    private Nastavnik domacin;

    public Sastanak(String predmet, int satPocetka, int satZavrsetka) {
        this.predmet = predmet;
        this.satPocetka = satPocetka;
        this.satZavrsetka = satZavrsetka;
        this.aktivan = false;
    }

    public String getPredmet() {
        return predmet;
    }

    public int getSatPocetka() {
        return satPocetka;
    }

    public int getSatZavrsetka() {
        return satZavrsetka;
    }

    public boolean isAktivan() {
        return aktivan;
    }

    public List<UcesnikSastanka> getUcesnici() {
        return ucesnici;
    }

    public Nastavnik getDomacin() {
        return domacin;
    }

    public void setAktivan(boolean aktivan) {
        this.aktivan = aktivan;
    }

    public void setDomacin(Nastavnik domacin) {
        this.domacin = domacin;
        dodajUcesnika(domacin);
    }

    @Override
    public int compareTo(Sastanak other) {
        int comparison = Integer.compare(this.satPocetka, other.satPocetka);
        return comparison == 0
                ? Integer.compare(this.satZavrsetka, other.satZavrsetka)
                : comparison;
    }

    public void dodajUcesnika(UcesnikSastanka ucesnikSastanka) {
        if (ucesnici.contains(ucesnikSastanka)) {
            return;
        }
        ucesnici.add(ucesnikSastanka);
    }

    public void ukloniUcesnika(UcesnikSastanka ucesnikSastanka) {
        ucesnici.remove(ucesnikSastanka);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sastanak sastanak = (Sastanak) o;

        if (satPocetka != sastanak.satPocetka) return false;
        return satZavrsetka == sastanak.satZavrsetka;
    }

    @Override
    public String toString() {
        return "Sastanak{" +
                "predmet='" + predmet + '\'' +
                ", satPocetka=" + satPocetka +
                ", satZavrsetka=" + satZavrsetka +
                ", aktivan=" + aktivan +
                ", ucesnici=" + ucesnici +
                ", domacin=" + domacin +
                '}';
    }
}