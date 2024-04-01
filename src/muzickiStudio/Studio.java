package muzickiStudio;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        List<Instrument> sviInstrumenti = filtrirajInstrumente();
        List<Instrument> instrumetniZaKlasu = new ArrayList<>();
        for (Instrument instrument : sviInstrumenti) {
            if (instrument.getKlasa().equals(klasaInstrumenta)) {
                instrumetniZaKlasu.add(instrument);
            }
        }
        return instrumetniZaKlasu;
    }

    private List<Instrument> filtrirajInstrumente() {
        List<Instrument> instruments = new ArrayList<>();
        for (Oprema o : oprema) {
            if (o instanceof Instrument) {
                Instrument instrument = (Instrument) o;
                instruments.add(instrument);
            }
        }
        return instruments;
    }

    public List<Kamera> filtrirajKamere(TipKamere tipKamere) {
        List<Kamera> kamere = new ArrayList<>();
        for (Oprema o : oprema) {
            if (o instanceof Kamera) {
                Kamera kamera = (Kamera) o;
                if (kamera.getTipKamere().equals(tipKamere)) {
                    kamere.add(kamera);
                }
            }
        }
        return kamere;
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

    public PonudaOpreme ponudiSnimanjeSpota(Orkestar orkestar) {
        PonudaOpreme ponudaOpreme = napraviPonuduOpreme(orkestar);
        if (!ponudaOpreme.isKompletiran()) {
            System.out.println("nije kompletna");
            ponudaOpreme.setMoguceSnimanjeSpota(false);
            return ponudaOpreme;
        }
        List<Kamera> sirokougaoneKamere = filtrirajKamere(TipKamere.SIROKOUGAONA);
        if (sirokougaoneKamere.isEmpty()) {
            System.out.println("nema sirokougaona kamera");
            ponudaOpreme.setMoguceSnimanjeSpota(false);
            return ponudaOpreme;
        }
        List<Kamera> teleskopskeKamere = filtrirajKamere(TipKamere.TELESKOPSKA);
        int brojTeleskopskihKamera = izracunajPotrebnihTeleskopskihKamera(ponudaOpreme.getOpreme());
        if (brojTeleskopskihKamera > teleskopskeKamere.size()) {
            System.out.println("nema dovoljno teleskopskih kamera");
            ponudaOpreme.setMoguceSnimanjeSpota(false);
            return ponudaOpreme;
        }
        teleskopskeKamere = teleskopskeKamere.subList(0, brojTeleskopskihKamera);
        ponudaOpreme.setMoguceSnimanjeSpota(true);
        ponudaOpreme.getOpreme().add(sirokougaoneKamere.get(0));
        ponudaOpreme.getOpreme().addAll(teleskopskeKamere);
        return ponudaOpreme;
    }

    private int izracunajPotrebnihTeleskopskihKamera(List<Oprema> opreme) {
        Set<String> imenaInstrumenata = new HashSet<>();
        List<Instrument> sviInstrumenti = new ArrayList<>();
        for (Oprema o : opreme) {
            if (o instanceof Instrument) {
                sviInstrumenti.add((Instrument) o);
            }
        }

        for (Instrument instrument : sviInstrumenti) {
            imenaInstrumenata.add(instrument.getNaziv());
        }
        int treba = (int) Math.ceil(imenaInstrumenata.size() / 2.0);
        System.out.println(treba+" - "+sviInstrumenti.size());
        return treba;
    }

    @Override
    public String toString() {
        return "Studio{" +
                "naziv='" + naziv + '\'' +
                ", oprema=" + oprema +
                '}';
    }
}
