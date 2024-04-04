package kolokvijumi.muzickiStudio;

public class Snimanje {

    public static void main(String[] args) {
        Studio studio = new Studio("moj studio");
        Orkestar orkestar = new Orkestar("moj orkestar", KlasaInstrumenta.A, true);
        Instrument violina = new Instrument("violina", KlasaInstrumenta.A, 1);
        Instrument gitara = new Instrument("gitara", KlasaInstrumenta.A, 2);
        Instrument bubanj = new Instrument("bubanj", KlasaInstrumenta.A, 3);
        Ozvucenje ozvucenje1 = new Ozvucenje("o1", 10);
        Ozvucenje ozvucenje2 = new Ozvucenje("o2", 20);
        Kamera teleKamera = new Kamera("tele", TipKamere.TELESKOPSKA, 30);
        Kamera teleKamera2 = new Kamera("tele2", TipKamere.TELESKOPSKA, 30);
        Kamera siroKamera = new Kamera("siro", TipKamere.SIROKOUGAONA, 60);
        studio.dodajOpremu(violina);
        studio.dodajOpremu(gitara);
        studio.dodajOpremu(bubanj);
        studio.dodajOpremu(ozvucenje1);
        studio.dodajOpremu(ozvucenje2);
        studio.dodajOpremu(teleKamera);
        studio.dodajOpremu(teleKamera2);
        studio.dodajOpremu(siroKamera);

        orkestar.getInstrumenti().add("bubanj");
        orkestar.getInstrumenti().add("gitara");
//        orkestar.getInstrumenti().add("violina");
        orkestar.getOzvucenje().add("o1");

        PonudaOpreme ponudaOpreme = studio.napraviPonuduOpreme(orkestar);
        if (ponudaOpreme.isKompletiran()) {
            System.out.println("cena za 4h je " + ponudaOpreme.cena(4));
            System.out.println("opreme:");
            for (Oprema oprema : ponudaOpreme.getOpreme()) {
                System.out.println(oprema);
            }
            PonudaOpreme ponudaSnimanja = studio.ponudiSnimanjeSpota(orkestar);
            if (ponudaSnimanja.isMoguceSnimanjeSpota()) {
                for (Oprema oprema : ponudaSnimanja.getOpreme()) {
                    if (oprema instanceof Kamera) {
                        System.out.println(oprema);
                    }
                }
                System.out.println("nova cena je " + ponudaSnimanja.cena(4));
            }
        } else {
            System.out.println("nedostaje oprema:");
            for (String oprema : ponudaOpreme.getNedostajucaOprema()) {
                System.out.println(oprema);
            }
        }
    }
}
