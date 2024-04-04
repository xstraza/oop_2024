package karantin;

public class Kamera extends UredjajZaSnimanje {
    public Kamera(String proizvodjac, String vlasnik) {
        super(proizvodjac, TipSnimka.VIDEO, vlasnik);
    }

    @Override
    public void sacuvajSnimak(Snimak snimak, Sastanak sastanak, String name) {

    }

    @Override
    public void pokreniSnimak(Snimak snimak) {

    }

    @Override
    public void zavrsiSnimak(Snimak snimak) {

    }
}
