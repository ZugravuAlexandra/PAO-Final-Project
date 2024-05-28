package database.entitati;

public class Sectiuni {
    private String nume_sec;

    public Sectiuni() {}

    public Sectiuni(String nume_sec) {
        this.nume_sec = nume_sec;
    }

    public String getNume_sec() {
        return nume_sec;
    }

    public void setNume_sec(String nume_sec) {
        this.nume_sec = nume_sec;
    }

    @Override
    public String toString() {
        return "Sectiuni: " + "\n" +
                "nume_sec='" + nume_sec ;
    }
}
