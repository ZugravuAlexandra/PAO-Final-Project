package database.entitati;

import java.util.List;
public class Biblioteca {
    private int id;
    private String nume;

    // Compozitie (has a)
    private Adresa adresa;

    public Biblioteca() {}

    public Biblioteca(int id, String nume, Adresa adresa) {
        this.id = id;
        this.nume = nume;
        this.adresa = adresa;
    }

    public Biblioteca(int id, String nume) {
        this.id = id;
        this.nume = nume;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Adresa getAdresa() {
        return adresa;
    }

    public void setAdresa(Adresa adresa) {
        this.adresa = adresa;
    }


    @Override
    public String toString() {
        return "Biblioteca " +
                " id = " + id + "\n" +
                " nume: " + nume + "\n" +
                " adresa: " + adresa + "\n";
    }
}
