package karantin;

import java.util.ArrayList;
import java.util.List;

public class CentralniRegistar {
    private static CentralniRegistar REGISTAR;
    private List<Sastanak> odrzaniSastanci = new ArrayList<>();
    private List<Sastanak> zakazaniSastanci = new ArrayList<>();

    private CentralniRegistar() {

    }

    public static CentralniRegistar getRegistar() {
        if (REGISTAR == null) {
            REGISTAR = new CentralniRegistar();
        }
        return REGISTAR;
    }

    public void zakaziSastanak(Sastanak sastanak) {
        zakazaniSastanci.add(sastanak);
    }

    public void otkaziSastanak(Sastanak sastanak) {
        zakazaniSastanci.remove(sastanak);
    }

    public void pokreniSastanak(Sastanak sastanak, Nastavnik nastavnik) {
        if (!sastanak.getDomacin().equals(nastavnik)) {
            System.out.println("samo domacin moze pokrenuti sastanak");
            return;
        }
        if (sastanak.isAktivan()) {
            System.out.println("sastanak je vec pokrenut");
            return;
        }
        if (!zakazaniSastanci.contains(sastanak)) {
            System.out.println("sastanak nije zakazan");
            return;
        }
        sastanak.setAktivan(true);
    }

    public void zavrsiSastanak(Sastanak sastanak, Nastavnik nastavnik) {
        if (!sastanak.getDomacin().equals(nastavnik)) {
            System.out.println("samo domacin moze zavrsiti sastanak");
            return;
        }
        if (!sastanak.isAktivan()) {
            System.out.println("sastanak nije pokrenut");
            return;
        }
        if (!zakazaniSastanci.contains(sastanak)) {
            System.out.println("sastanak nije zakazan");
            return;
        }
        sastanak.setAktivan(false);
        zakazaniSastanci.remove(sastanak);
        odrzaniSastanci.add(sastanak);
    }

    public void stampajPrisustvoSortirano() {

    }
}
