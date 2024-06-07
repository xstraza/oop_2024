package vanzemaljci;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Alien {
    Integer id;
    String continent;
    String country;
    private SimpleIntegerProperty idStringProperty;
    private SimpleStringProperty continentStringProperty;
    private SimpleStringProperty countryStringProperty;
    private boolean found;

    public Alien(Integer id, String continent, String country, boolean found) {
        this.id = id;
        this.idStringProperty = new SimpleIntegerProperty(id);
        this.continent = continent;
        this.continentStringProperty = new SimpleStringProperty(continent);
        this.country = country;
        this.countryStringProperty = new SimpleStringProperty(country);
        this.found = found;
    }

    public boolean isFound() {
        return found;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.countryStringProperty = new SimpleStringProperty(country);
        this.country = country;
    }

    @Override
    public String toString() {
        return id + ";" + continent + "," + country;
    }

    public SimpleIntegerProperty getIdStringProperty() {
        return idStringProperty;
    }

    public SimpleStringProperty getContinentStringProperty() {
        return continentStringProperty;
    }

    public SimpleStringProperty getCountryStringProperty() {
        return countryStringProperty;
    }
}
