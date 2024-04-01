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
        this.oprema.add(oprema);
    }

    public List<Ozvucenje> filtrirajOzvucenja() {
        List<Ozvucenje> ozvucenjes = new ArrayList<>();
        for (Oprema o : oprema) {
            if (o instanceof Ozvucenje) {
                ozvucenjes.add((Ozvucenje) o);
            }
        }
        return ozvucenjes;
    }

    public List<Instrument> filtrirajInstrumente(KlasaInstrumenta klasaInstrumenta) {
        List<Instrument> instruments = new ArrayList<>();
        for (Oprema o : oprema) {
            if (o instanceof Instrument) {
                Instrument instrument = (Instrument) o;
                if (instrument.getKlasa().equals(klasaInstrumenta)) {
                    instruments.add(instrument);
                }
            }
        }
        return instruments;
    }


    public PonudaOpreme napraviPonuduOpreme(Orkestar orkestar) {
        List<Oprema> opremaZaOrkestar = new ArrayList<>();
        List<String> nedostajucaOprema = new ArrayList<>();
        List<Instrument> instruments = filtrirajInstrumente(orkestar.getMinimalnaKlasa());
        for (String imeInstrumenta : orkestar.getInstrumenti()) {
            boolean nasao = false;
            for (Instrument instrument : instruments) {
                if (instrument.getNaziv().equals(imeInstrumenta)) {
                    opremaZaOrkestar.add(instrument);
                    nasao = true;
                    break;
                }
            }
            if (!nasao) {
                nedostajucaOprema.add(imeInstrumenta);
            }
        }
        List<Ozvucenje> ozvucenjes = filtrirajOzvucenja();
        for (String imeOzvucenja : orkestar.getOzvucenje()) {
            boolean nasao = false;
            for (Ozvucenje ozvucenje : ozvucenjes) {
                if (ozvucenje.getNaziv().equals(imeOzvucenja)) {
                    opremaZaOrkestar.add(ozvucenje);
                    nasao = true;
                    break;
                }
            }
            if (!nasao) {
                nedostajucaOprema.add(imeOzvucenja);
            }
        }
        return new PonudaOpreme(nedostajucaOprema, opremaZaOrkestar);
    }

    @Override
    public String toString() {
        return "Studio{" +
                "naziv='" + naziv + '\'' +
                ", oprema=" + oprema +
                '}';
    }
}
