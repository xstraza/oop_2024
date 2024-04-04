package kolokvijumi.karantin;

import java.util.ArrayList;
import java.util.List;

public class Nastavnik extends UcesnikSastanka {
    private List<UredjajZaSnimanje> uredjajiZaSnimanje = new ArrayList<>();
    public Nastavnik(String korisnickoIme) {
        super(korisnickoIme);
    }

    public void dodajUredjaj(UredjajZaSnimanje uredjajZaSnimanje) {
        if (uredjajiZaSnimanje.contains(uredjajZaSnimanje)) {
            return;
        }
        uredjajiZaSnimanje.add(uredjajZaSnimanje);
    }

    public void ukloniUredjaj(UredjajZaSnimanje uredjajZaSnimanje) {
        uredjajiZaSnimanje.remove(uredjajZaSnimanje);
    }

    public List<UredjajZaSnimanje> getUredjajiZaSnimanje() {
        return uredjajiZaSnimanje;
    }

    @Override
    public void ucestvuj(Sastanak sastanak) {
        sastanak.dodajUcesnika(this);
    }

    @Override
    public void napusti(Sastanak sastanak) {
        sastanak.ukloniUcesnika(this);
    }
}
