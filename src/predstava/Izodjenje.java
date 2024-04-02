package predstava;

import java.util.List;

public interface Izodjenje {
    void dodajUcesnika(Ucesnik ucesnik);
    List<Ucesnik> ucesniciIzvodjenja();
    boolean jeMoguceIzvesti();
    void izvedi();
    TipaIzvodjenja getTipIzvodjenja();
}
