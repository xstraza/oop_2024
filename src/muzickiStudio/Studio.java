package muzickiStudio;

import java.util.ArrayList;
import java.util.List;

public class Studio {
    private String naziv;
    private List<Oprema> oprema = new ArrayList<>();

    public Studio(String naziv) {
        this.naziv = naziv;
    }

    public void dodajOpremu(Oprema oprema) {

    }

    public List<Ozvucenje> filtrirajOzvucenja() {
        return null;
    }

    public List<Instrument> filtrirajInstrumente(KlasaInstrumenta klasaInstrumenta) {
        return null;
    }


    public PonudaOpreme napraviPonuduOpreme(Orkestar orkestar) {
        return null;
    }

    @Override
    public String toString() {
        return "Studio{" +
                "naziv='" + naziv + '\'' +
                ", oprema=" + oprema +
                '}';
    }
}
