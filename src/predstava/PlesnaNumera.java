package predstava;

import java.util.ArrayList;
import java.util.List;

public class PlesnaNumera implements Izodjenje {
    private String naziv;
    private List<Ucesnik> ucesnici = new ArrayList<>();

    public PlesnaNumera() {
    }


    @Override
    public void dodajUcesnika(Ucesnik ucesnik) {
        ucesnici.add(ucesnik);
    }

    @Override
    public boolean jeMoguceIzvesti() {
        int brojPevacaISolista = 0;
        for (Ucesnik ucesnik : ucesnici) {
            if (ucesnik instanceof Muzicar) {
                Muzicar muzicar = (Muzicar) ucesnik;
                if (muzicar.isVokal()) {
                    brojPevacaISolista++;
                }
            } else {
                Plesac plesac = (Plesac) ucesnik;
                if (plesac.getUloga().equals("SOLISTA")) {
                    brojPevacaISolista++;
                }
            }
        }
        return brojPevacaISolista == 0 || brojPevacaISolista == 1;
    }

    @Override
    public void izvedi() {
        System.out.println("Izvodjenje plesne numere " + naziv);
        ispisiUcesnike();
    }

    private void ispisiUcesnike() {
        Ucesnik prvi = null;
        for (Ucesnik ucesnik : ucesnici) {
            if (ucesnik instanceof Muzicar) {
                Muzicar muzicar = (Muzicar) ucesnik;
                if (muzicar.isVokal()) {
                    System.out.println(muzicar);
                    prvi = muzicar;
                    break;
                }
            } else {
                Plesac plesac = (Plesac) ucesnik;
                if (plesac.getUloga().equals("SOLISTA")) {
                    System.out.println(plesac);
                    prvi = plesac;
                    break;
                }
            }
        }
        for (Ucesnik ucesnik : ucesnici) {
            if (ucesnik instanceof Plesac) {
                Plesac plesac = (Plesac) ucesnik;
                if (!plesac.equals(prvi)) {
                    System.out.println(plesac);
                }
            }
        }
        for (Ucesnik ucesnik : ucesnici) {
            if (ucesnik instanceof Muzicar) {
                Muzicar muzicar = (Muzicar) ucesnik;
                if (!muzicar.equals(prvi)) {
                    System.out.println(muzicar);
                }
            }
        }
    }

    public boolean proveriKostime(List<Kostim> kostimi) {
        for (Ucesnik ucesnik : ucesnici) {
            if (ucesnik instanceof Plesac) {
                Kostim zaSkloniti = null;
                Plesac plesac = (Plesac) ucesnik;
                if (plesac.getUloga().equals("SOLISTA")) {
                    for (Kostim kostim : kostimi) {
                        if (kostim.getVelicina().equals(plesac.getVelicina()) && kostim.isSolisticki())  {
                            zaSkloniti = kostim;
                            break;
                        }
                    }
                } else {
                    for (Kostim kostim : kostimi) {
                        if (kostim.getVelicina().equals(plesac.getVelicina()))  {
                            zaSkloniti = kostim;
                            break;
                        }
                    }
                }
                if (zaSkloniti == null) {
                    return false;
                } else {
                    kostimi.remove(zaSkloniti);
                }
            }
        }
        return true;
    }
}
