package ucionice;

public class Student {
    private String smer;
    private Integer brojIndeksa;
    private Integer godinaUpisa;
    private String prezime;
    private String ime;

    public Student(String smer, Integer brojIndeksa, Integer godinaUpisa, String prezime, String ime) {
        this.smer = smer;
        this.brojIndeksa = brojIndeksa;
        this.godinaUpisa = godinaUpisa;
        this.prezime = prezime;
        this.ime = ime;
    }

    public String getSmer() {
        return smer;
    }

    public void setSmer(String smer) {
        this.smer = smer;
    }

    public Integer getBrojIndeksa() {
        return brojIndeksa;
    }

    public void setBrojIndeksa(Integer brojIndeksa) {
        this.brojIndeksa = brojIndeksa;
    }

    public Integer getGodinaUpisa() {
        return godinaUpisa;
    }

    public void setGodinaUpisa(Integer godinaUpisa) {
        this.godinaUpisa = godinaUpisa;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    @Override
    public String toString() {
        return ime + " " + prezime + " " + brojIndeksa + "/" + smer + "-" + godinaUpisa;
    }
}
