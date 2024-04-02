package predstava;

import java.util.List;

public class Muzicar extends Ucesnik implements Comparable<Muzicar> {
    private boolean vokal;
    private List<Instrument> instrumenti;

    public Muzicar(String ime, boolean vokal, List<Instrument> instrumenti) {
        super(ime);
        this.vokal = vokal;
        this.instrumenti = instrumenti;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("MUZICAR - ");
        if (vokal) {
            sb.append("VOKAL,");
        }
        if (instrumenti.contains(Instrument.KLAVIR)) {
            sb.append("PIJANISTA,");
        }
        if (instrumenti.contains(Instrument.GITARA)) {
            sb.append("GITARISTA,");
        }
        if (instrumenti.contains(Instrument.BUBANJ)) {
            sb.append("BUBNJAR,");
        }
        if (instrumenti.contains(Instrument.VIOLINA)) {
            sb.append("VIOLINISTA,");
        }
        String string = sb.toString();
        return string.substring(0, string.length() - 1); //izbacimo poslednji zarez
    }

    public boolean isVokal() {
        return vokal;
    }

    public List<Instrument> getInstrumenti() {
        return instrumenti;
    }

    @Override
    public int compareTo(Muzicar other) {
        return this.getIme().compareTo(other.getIme());
    }
}
