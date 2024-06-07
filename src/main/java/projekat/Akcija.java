package projekat;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Akcija {
    private Osoba osoba;
    private TipAkcije tipAkcije;
    private Date datum;
    private Double iznos;

    public Akcija(Osoba osoba, TipAkcije tipAkcije, Date datum, Double iznos) {
        this.osoba = osoba;
        this.tipAkcije = tipAkcije;
        this.datum = datum;
        this.iznos = iznos;
    }

    public Osoba getOsoba() {
        return osoba;
    }

    public void setOsoba(Osoba osoba) {
        this.osoba = osoba;
    }

    public TipAkcije getTipAkcije() {
        return tipAkcije;
    }

    public void setTipAkcije(TipAkcije tipAkcije) {
        this.tipAkcije = tipAkcije;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Double getIznos() {
        return iznos;
    }

    public void setIznos(Double iznos) {
        this.iznos = iznos;
    }

    @Override
    public String toString() {
        return "Akcija{" +
                "osoba=" + osoba +
                ", tipAkcije=" + tipAkcije +
                ", datum=" + datum +
                ", iznos=" + iznos +
                '}';
    }

    public SimpleStringProperty getTipProperty() {
        return new SimpleStringProperty(tipAkcije.getIspis());
    }

    public SimpleStringProperty getDatumPoperty() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        String format = formatter.format(datum);
        return new SimpleStringProperty(format);
    }

    public SimpleStringProperty getImeProperty() {
        return new SimpleStringProperty(osoba.getIme());
    }

    public SimpleStringProperty getPrezimeProperty() {
        return new SimpleStringProperty(osoba.getPrezime());
    }
}
