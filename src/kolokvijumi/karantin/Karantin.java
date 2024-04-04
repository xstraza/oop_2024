package karantin;

public class Karantin {

    public static void main(String[] args) {
        Nastavnik n1 = new Nastavnik("nas1");
        Nastavnik n2 = new Nastavnik("nas2");

        Student s1 = new Student("stud1");
        Student s2 = new Student("stud2");
        Student s3 = new Student("stud3");

        Sastanak sas1 = new Sastanak("p1", 11, 13);
        sas1.setDomacin(n1);
        sas1.dodajUcesnika(s1);
        sas1.dodajUcesnika(s2);
        sas1.dodajUcesnika(s3);
        Sastanak sas2 = new Sastanak("p2", 12, 14);
        sas2.setDomacin(n1);
        sas2.dodajUcesnika(s1);
        sas2.dodajUcesnika(s2);
        Sastanak sas3 = new Sastanak("p3", 11, 13);
        sas3.setDomacin(n2);
        sas3.dodajUcesnika(s2);
        sas3.dodajUcesnika(s3);
        CentralniRegistar cs = CentralniRegistar.getRegistar();
        cs.zakaziSastanak(sas1);
        cs.pokreniSastanak(sas1, n1);
        cs.otkaziSastanak(sas2);
        cs.zavrsiSastanak(sas1, n2);
        cs.zavrsiSastanak(sas1, n1);
    }
}
