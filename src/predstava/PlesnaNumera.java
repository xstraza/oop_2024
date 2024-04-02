package predstava;

import java.util.ArrayList;
import java.util.List;

public class PlesnaNumera implements Izodjenje {
    private String naziv;
    private List<Ucesnik> ucesnici = new ArrayList<>();

    public PlesnaNumera(String naziv) {
        this.naziv = naziv;
    }


    @Override
    public void dodajUcesnika(Ucesnik ucesnik) {
        ucesnici.add(ucesnik);
    }

    @Override
    public List<Ucesnik> ucesniciIzvodjenja() {
        return ucesnici;
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

    @Override
    public TipaIzvodjenja getTipIzvodjenja() {
        return TipaIzvodjenja.PLESNA_NUMERA;
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
        List<Kostim> kostims = new ArrayList<>(kostimi);
        for (Ucesnik ucesnik : ucesnici) {
            if (ucesnik instanceof Plesac) {
                Kostim zaSkloniti = null;
                Plesac plesac = (Plesac) ucesnik;
                if (plesac.getUloga().equals("SOLISTA")) {
                    for (Kostim kostim : kostims) {
                        if (kostim.getVelicina().equals(plesac.getVelicina()) && kostim.isSolisticki())  {
                            zaSkloniti = kostim;
                            break;
                        }
                    }
                } else {
                    for (Kostim kostim : kostims) {
                        if (kostim.getVelicina().equals(plesac.getVelicina()))  {
                            zaSkloniti = kostim;
                            break;
                        }
                    }
                }
                if (zaSkloniti == null) {
                    return false;
                } else {
                    kostims.remove(zaSkloniti);
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "PlesnaNumera{" +
                "naziv='" + naziv + '\'' +
                '}';
    }
}
