package kolokvijumi.predstava;

public class Kostim {
    private boolean solisticki;
    private Velicina velicina;

    public Kostim(boolean solisticki, Velicina velicina) {
        this.solisticki = solisticki;
        this.velicina = velicina;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Kostim kostim = (Kostim) o;

        if (solisticki != kostim.solisticki) return false;
        return velicina == kostim.velicina;
    }

    @Override
    public int hashCode() {
        int result = (solisticki ? 1 : 0);
        result = 31 * result + (velicina != null ? velicina.hashCode() : 0);
        return result;
    }

    public boolean isSolisticki() {
        return solisticki;
    }

    public Velicina getVelicina() {
        return velicina;
    }
}
