package predstava;

import java.util.ArrayList;
import java.util.List;

public class PlesnaNumera implements Izodjenje {
    private String naziv;
    private List<Ucesnik> ucesnici = new ArrayList<>();

    public PlesnaNumera() {
    }


    @Override
    public void dodajUcesnika(Ucesnik ucesnik) {

    }

    @Override
    public boolean jeMoguceIzvesti() {
        return false;
    }

    @Override
    public void izvedi() {

    }

    private void ispisiUcesnike() {

    }

    public boolean proveriKostime(List<Kostim> kostimi) {
        return false;
    }
}
