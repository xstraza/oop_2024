package kolokvijumi.karantin;

public class Mikrofon extends UredjajZaSnimanje {
    public Mikrofon(String proizvodjac, String vlasnik) {
        super(proizvodjac, TipSnimka.AUDIO, vlasnik);
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
