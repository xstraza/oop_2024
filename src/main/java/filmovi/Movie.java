package filmovi;

import javafx.beans.property.SimpleStringProperty;

public class Movie {
    private String title;
    private int phase;
    private double rating;
    private int year;
    private int duration;
    private boolean odgledan;

    private SimpleStringProperty titleStringProperty;
    private SimpleStringProperty phaseStringProperty;
    private SimpleStringProperty ratingStringProperty;
    private SimpleStringProperty yearStringProperty;
    private SimpleStringProperty durationStringProperty;
    private SimpleStringProperty odgledanStringProperty;

    public Movie(String title, int phase, double rating, int year, int duration) {
        this.title = title;
        this.titleStringProperty = new SimpleStringProperty(title);
        this.phase = phase;
        this.phaseStringProperty = new SimpleStringProperty(String.valueOf(phase));
        this.rating = rating;
        this.ratingStringProperty = new SimpleStringProperty(String.valueOf(rating));
        this.year = year;
        this.yearStringProperty = new SimpleStringProperty(String.valueOf(year));
        this.duration = duration;
        this.durationStringProperty = new SimpleStringProperty(String.valueOf(duration));
        this.odgledan = false;
        this.odgledanStringProperty = new SimpleStringProperty(String.valueOf(false));
    }

    public boolean isOdgledan() {
        return odgledan;
    }

    public String getTitle() {
        return title;
    }

    public int getPhase() {
        return phase;
    }

    public double getRating() {
        return rating;
    }

    public int getYear() {
        return year;
    }

    public int getDuration() {
        return duration;
    }

    public SimpleStringProperty titleProperty() {
        return titleStringProperty;
    }

    public SimpleStringProperty phaseProperty() {
        return phaseStringProperty;
    }

    public SimpleStringProperty ratingProperty() {
        return ratingStringProperty;
    }

    public SimpleStringProperty yearProperty() {
        return yearStringProperty;
    }

    public SimpleStringProperty durationProperty() {
        return durationStringProperty;
    }

    public SimpleStringProperty seenProperty() {
        return odgledanStringProperty;
    }

    @Override
    public String toString() {
        return title + " (" + year + ")" + " - " + duration;
    }

    public void setOdgledan() {
        this.odgledan = true;
    }
}
