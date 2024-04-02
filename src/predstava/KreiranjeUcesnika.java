package predstava;

import java.util.ArrayList;
import java.util.List;

public class KreiranjeUcesnika {
    public static Ucesnik kreirajUcesnika(String unos) {
        if (unos.startsWith("P")) {
            return kreirajPlesaca(unos);
        }
        if (unos.startsWith("M")) {
            return kreirajMuzicara(unos);
        }
        throw new IllegalArgumentException("neispravan string");
    }

    private static Ucesnik kreirajMuzicara(String unos) {
        String[] s = unos.split(" ");
        String ime = s[1];
        List<Instrument> instrumenti = new ArrayList<>();
        boolean vokal = false;
        for (int i = 2; i < s.length; i++) {
            if (s[i].equals("PEVAC")) {
                vokal = true;
            } else {
                if (s[i].startsWith("GIT")) {
                    instrumenti.add(Instrument.GITARA);
                }
                if (s[i].startsWith("VIO")) {
                    instrumenti.add(Instrument.VIOLINA);
                }
                if (s[i].startsWith("BUB")) {
                    instrumenti.add(Instrument.BUBANJ);
                }
                if (s[i].startsWith("PIJ")) {
                    instrumenti.add(Instrument.KLAVIR);
                }
            }
        }
        return new Muzicar(ime, vokal, instrumenti);
    }

    private static Ucesnik kreirajPlesaca(String unos) {
        String[] s = unos.split(" ");
        String ime = s[1]; //na s[0] je "P"
        Velicina velicina = Velicina.valueOf(s[2]);
        boolean solista = s.length == 4;
        //valueof je metoda iz enuma bilo kog, zahteva da joj date isto ono sto bi vam enum.name() vratio
        return new Plesac(ime, solista, velicina);
    }
}
