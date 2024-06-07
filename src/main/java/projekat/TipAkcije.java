package projekat;

public enum TipAkcije {
    CAS_TEORIJE("ČAS TEORIJE"),
    CAS_VOZNJE("ČAS VOŽNJE"),
    UPLATA("UPLATA"),
    POLAGANJE("POLAGANJE");

    private String ispis;

    TipAkcije(String ispis) {
        this.ispis = ispis;
    }

    public String getIspis() {
        return ispis;
    }

    public static TipAkcije getTip(String s) {
        if (s.equals(CAS_TEORIJE.ispis)) return CAS_TEORIJE;
        if (s.equals(CAS_VOZNJE.ispis)) return CAS_VOZNJE;
        if (s.equals(UPLATA.ispis)) return UPLATA;
        if (s.equals(POLAGANJE.ispis)) return POLAGANJE;
        throw new IllegalArgumentException(s);
    }
}
