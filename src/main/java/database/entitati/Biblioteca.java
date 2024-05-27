package database.entitati;

import java.util.List;
public class Biblioteca {
    private int id;
    private String nume;

    private Adresa adresa;
    private List<Carte> carti;

    public Biblioteca() {}

    public Biblioteca(int id, String nume, Adresa adresa, List<Carte> carti) {
        this.id = id;
        this.nume = nume;
        this.adresa = adresa;
        this.carti = carti;
    }

    public Biblioteca(int id, String nume) {
        this.id = id;
        this.nume = nume;
    }

    public Biblioteca(int id, String nume, Adresa adresa) {
        this.id = id;
        this.nume = nume;
        this.adresa = adresa;
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

    public List<Carte> getCarti() {
        return carti;
    }

    public void setCarti(List<Carte> carti) {
        this.carti = carti;
    }

    @Override
    public String toString() {
        return "Biblioteca " +
                " id = " + id + "\n" +
                " nume: " + nume + "\n" +
                " adresa: " + adresa + "\n" +
                " carti " + carti;
    }
}
