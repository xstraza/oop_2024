package muzickiStudio;

import java.util.Collections;
import java.util.List;

public class PonudaOpreme implements Procenjivo {
    private List<String> nedostajucaOprema;
    private List<Oprema> opreme;
    private boolean kompletiran;

    public PonudaOpreme(List<String> nedostajucaOprema, List<Oprema> opreme) {
        this.nedostajucaOprema = nedostajucaOprema;
        this.opreme = opreme;
        this.kompletiran = nedostajucaOprema.isEmpty();
    }

    public void ispisOpreme() {
        Collections.sort(opreme);
        for (Oprema oprema : opreme) {
            System.out.println(oprema);
        }
    }
    public void ispisNedostajuceOpreme() {
        Collections.sort(nedostajucaOprema);
        for (String x : nedostajucaOprema) {
            System.out.println(x);
        }
    }

    @Override
    public double cena(int brojSati) {
        double suma = 0;
        for (Oprema oprema : opreme) {
            suma += oprema.cena(brojSati);
        }
        return suma;
    }
}
