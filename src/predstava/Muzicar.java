package predstava;

import java.util.ArrayList;
import java.util.List;

public class Muzicar {
    private boolean vokal;
    private List<Instrument> instrumenti;

    public Muzicar(boolean vokal, List<Instrument> instrumenti) {
        this.vokal = vokal;
        this.instrumenti = instrumenti;
    }

    @Override
    public String toString() {
        return "Muzicar{" +
                "vokal=" + vokal +
                ", instrumenti=" + instrumenti +
                '}';
    }
}
