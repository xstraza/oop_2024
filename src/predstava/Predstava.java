package predstava;

import java.util.ArrayList;
import java.util.List;

public class Predstava {
    private List<Izodjenje> izvodjenja = new ArrayList<>();
    private List<Kostim> dostupniKostimi = new ArrayList<>();

    public static void main(String[] args) {
        Ucesnik u1 = KreiranjeUcesnika.kreirajUcesnika("P mika L solista");
        Ucesnik u2 = KreiranjeUcesnika.kreirajUcesnika("M pera PEVAC");
        Ucesnik u3 = KreiranjeUcesnika.kreirajUcesnika("P zika XL");
        Ucesnik u4 = KreiranjeUcesnika.kreirajUcesnika("M laza BUBNJAR");
        Ucesnik u5 = KreiranjeUcesnika.kreirajUcesnika("P kaza L solista");
        Ucesnik u6 = KreiranjeUcesnika.kreirajUcesnika("M bole VIOLINISTA");
        Ucesnik u7 = KreiranjeUcesnika.kreirajUcesnika("M cole VIOLINISTA GITARISTA");
        Ucesnik u8 = KreiranjeUcesnika.kreirajUcesnika("M bobi BUBNJAR");

        Predstava predstava = new Predstava();
        PlesnaNumera pn1 = new PlesnaNumera("p1");
        pn1.dodajUcesnika(u1);
        pn1.dodajUcesnika(u3);
        MuzickaNumera mn1 = new MuzickaNumera();
        mn1.dodajUcesnika(u2);
        mn1.dodajUcesnika(u4);
        mn1.dodajUcesnika(u6);
        PlesnaNumera pn2 = new PlesnaNumera("p2");
        pn2.dodajUcesnika(u1);
        pn2.dodajUcesnika(u3);
        MuzickaNumera mn2 = new MuzickaNumera();
        mn2.dodajUcesnika(u2);
        mn2.dodajUcesnika(u6);
        mn2.dodajUcesnika(u7);
        mn2.dodajUcesnika(u8);
        PlesnaNumera pn3 = new PlesnaNumera("p3");
        pn3.dodajUcesnika(u5);

        predstava.getIzvodjenja().add(pn1);
        predstava.getIzvodjenja().add(mn1);
        predstava.getIzvodjenja().add(pn2);
        predstava.getIzvodjenja().add(mn2);
        predstava.getIzvodjenja().add(pn3);

        predstava.getDostupniKostimi().add(new Kostim(true, Velicina.S));
        predstava.getDostupniKostimi().add(new Kostim(true, Velicina.M));
        predstava.getDostupniKostimi().add(new Kostim(true, Velicina.L));
        predstava.getDostupniKostimi().add(new Kostim(true, Velicina.XL));
        predstava.getDostupniKostimi().add(new Kostim(false, Velicina.S));
        predstava.getDostupniKostimi().add(new Kostim(false, Velicina.M));
        predstava.getDostupniKostimi().add(new Kostim(false, Velicina.L));
        predstava.getDostupniKostimi().add(new Kostim(false, Velicina.XL));

        boolean proba = predstava.proba();
        System.out.println(proba ? "uspesna proba" : "neuspesna proba");
    }

    public List<Izodjenje> getIzvodjenja() {
        return izvodjenja;
    }

    public List<Kostim> getDostupniKostimi() {
        return dostupniKostimi;
    }

    public boolean proba() {
        boolean svakoIzvodjenjeJeMoguce = daLiJeSvakoIzvodjenjeMoguce();
        boolean plesnaIzvodjenjaImajuKostime = daLiPlesnaIzvodjenjaImajuKostime();
        boolean naizmenicno = daLiSeNaizmenicnoIzvodePlesneIMuzickeNumere();
        boolean nemaIstogUcesnikaUzastopno = daLiNemaUzastpnoihIzvodjaca();
        boolean moguce = svakoIzvodjenjeJeMoguce
                && plesnaIzvodjenjaImajuKostime
                && naizmenicno
                && nemaIstogUcesnikaUzastopno;
        if (moguce) {
            for (Izodjenje izodjenje : izvodjenja) {
                izodjenje.izvedi();
            }
        }
        return moguce;
    }

    private boolean daLiNemaUzastpnoihIzvodjaca() {
        for (int i = 0; i < izvodjenja.size() - 1; i++) {
            List<Ucesnik> ucesnici1 = izvodjenja.get(i).ucesniciIzvodjenja();
            List<Ucesnik> ucesnici2 = izvodjenja.get(i + 1).ucesniciIzvodjenja();
            for (Ucesnik ucesnik : ucesnici1) {
                if (ucesnici2.contains(ucesnik)) {
                    System.out.println("uzastopni");
                    return false;
                }
            }

        }
        return true;
    }

    private boolean daLiSeNaizmenicnoIzvodePlesneIMuzickeNumere() {
        TipaIzvodjenja prethodniTip = izvodjenja.get(0).getTipIzvodjenja();
        for (int i = 1; i < izvodjenja.size(); i++) {
            TipaIzvodjenja trenutniTip = izvodjenja.get(i).getTipIzvodjenja();
            if (prethodniTip.equals(trenutniTip)) {
                System.out.println("naizmenicno");
                return false;
            }
            prethodniTip = trenutniTip;
        }
        return true;
    }

    private boolean daLiPlesnaIzvodjenjaImajuKostime() {
        for (Izodjenje izodjenje : izvodjenja) {
            if (izodjenje instanceof PlesnaNumera) {
                PlesnaNumera plesnaNumera = (PlesnaNumera) izodjenje;
                if (!plesnaNumera.proveriKostime(dostupniKostimi)) {
                    System.out.println("kostimi");
                    return false;
                }
            }
        }
        return true;
    }

    private boolean daLiJeSvakoIzvodjenjeMoguce() {
        for (Izodjenje izodjenje : izvodjenja) {
            if (!izodjenje.jeMoguceIzvesti()) {
                System.out.println("moguce" + izodjenje);
                return false;
            }
        }
        return true;
    }
}
