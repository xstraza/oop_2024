package ucionice;

public class Ucionica {
    String oznaka;
    String tip;
    Integer brojMesta;

    public Ucionica(String oznaka, String tip) {
        this.oznaka = oznaka;
        this.tip = tip;
        this.brojMesta = 0;
    }

    private Ucionica(String oznaka, String tip, Integer brojMesta) {
        this.oznaka = oznaka;
        this.tip = tip;
        this.brojMesta = brojMesta;
    }

    public Ucionica copy() {
        return new Ucionica(oznaka, tip, brojMesta);
    }

    public String getOznaka() {
        return oznaka;
    }

    public void setOznaka(String oznaka) {
        this.oznaka = oznaka;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    @Override
    public String toString() {
        return oznaka + (tip != null ? " (" + tip + ")" : "") + " - " + brojMesta;
    }

    public Integer getBrojMesta() {
        return brojMesta;
    }

    public void setBrojMesta(Integer brojMesta) {
        this.brojMesta = brojMesta;
    }
}
