package kolokvijumi.predstava;

import java.util.*;

public class MuzickaNumera implements Izodjenje {
    private List<Muzicar> muzicari = new ArrayList<>();

    public MuzickaNumera() {
    }

    @Override
    public void dodajUcesnika(Ucesnik ucesnik) {
        if (ucesnik instanceof Muzicar) {
            muzicari.add((Muzicar) ucesnik);
        }
    }

    @Override
    public List<Ucesnik> ucesniciIzvodjenja() {
        return new ArrayList<>(muzicari);
    }

    @Override
    public boolean jeMoguceIzvesti() {
        return barJedanVokal() && tacnoJedanBubnjar() && barDvaDrugaMuzicara();
    }

    private boolean barDvaDrugaMuzicara() {
        //trazimo dva razlicita muziciara kojima se liste instrumenata ne poklapaju, a da uz to nijedan od njih nije bubnjar
        Muzicar bubnjar = null;
        for (Muzicar muzicar : muzicari) {
            if (muzicar.getInstrumenti().contains(Instrument.BUBANJ)) {
                bubnjar = muzicar;
            }
        }
        for (Muzicar muzicar : muzicari) {
            for (Muzicar muzicar1 : muzicari) {
                if (!muzicar.equals(bubnjar)) {
                    if (!new HashSet<>(muzicar.getInstrumenti()).containsAll(muzicar1.getInstrumenti()) //ovime gledamo da li imaju sve iste instrumente
                            || !new HashSet<>(muzicar1.getInstrumenti()).containsAll(muzicar.getInstrumenti())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean tacnoJedanBubnjar() {
        int bubnjari = 0;
        for (Muzicar muzicar : muzicari) {
            if (muzicar.getInstrumenti().contains(Instrument.BUBANJ)) {
                bubnjari++;
            }
        }
        return bubnjari == 1;
    }

    private boolean barJedanVokal() {
        for (Muzicar muzicar : muzicari) {
            if (muzicar.isVokal()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void izvedi() {
        System.out.println("Izvodjenje muzicke numere");
        Collections.sort(muzicari);
        for (Muzicar muzicar : muzicari) {
            System.out.println(muzicar);
        }
    }

    @Override
    public TipaIzvodjenja getTipIzvodjenja() {
        return TipaIzvodjenja.MUZICKA_NUMERA;
    }
}
