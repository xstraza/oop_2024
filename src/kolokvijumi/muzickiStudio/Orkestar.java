package kolokvijumi.muzickiStudio;

import java.util.ArrayList;
import java.util.List;

public class Orkestar {
    private String naziv;
    private KlasaInstrumenta minimalnaKlasa;
    private List<String> instrumenti = new ArrayList<>();
    private List<String> ozvucenje = new ArrayList<>();
    private boolean prvoBolji;

    public Orkestar(String naziv, KlasaInstrumenta minimalnaKlasa, boolean prvoBolji) {
        this.naziv = naziv;
        this.minimalnaKlasa = minimalnaKlasa;
        this.prvoBolji = prvoBolji;
    }

    public String getNaziv() {
        return naziv;
    }

    public KlasaInstrumenta getMinimalnaKlasa() {
        return minimalnaKlasa;
    }

    public List<String> getInstrumenti() {
        return instrumenti;
    }

    public List<String> getOzvucenje() {
        return ozvucenje;
    }

    public boolean isPrvoBolji() {
        return prvoBolji;
    }

    @Override
    public String toString() {
        return "Orkestar " + naziv;
    }
}
