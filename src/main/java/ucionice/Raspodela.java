package ucionice;

public class Raspodela {
    private Student student;
    private String termin;
    private Ucionica ucionica;

    public Raspodela(Student student, String termin, Ucionica ucionica) {
        this.student = student;
        this.termin = termin;
        this.ucionica = ucionica;
    }

    public Student getStudent() {
        return student;
    }

    public String getTermin() {
        return termin;
    }

    public Ucionica getUcionica() {
        return ucionica;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setTermin(String termin) {
        this.termin = termin;
    }

    public void setUcionica(Ucionica ucionica) {
        this.ucionica = ucionica;
    }
}
