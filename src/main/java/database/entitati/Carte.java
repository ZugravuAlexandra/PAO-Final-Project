package database.entitati;

import java.util.List;

public class Carte {
    private int id;
    private String titlu;
    private String autor;
    private String editura;
    private Sectiuni sectiune;
    // private String gen (sectiunea din care face parte)
    private String descriere;
    private List<Recenzii> recenzii;
    private boolean disponibila;


    Carte() {}

    public Carte(int id, String titlu, String autor, String editura, Sectiuni sectiune, String descriere, List<Recenzii> recenzii ) {
        this.id = id;
        this.titlu = titlu;
        this.autor = autor;
        this.editura = editura;
        this.sectiune = sectiune;
        this.descriere = descriere;
        this.recenzii = recenzii;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditura() {
        return editura;
    }

    public void setEditura(String editura) {
        this.editura = editura;
    }

    public Sectiuni getSectiune() {
        return sectiune;
    }

    public void setSectiune(Sectiuni sectiune) {
        this.sectiune = sectiune;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public List<Recenzii> getRecenzii() {
        return recenzii;
    }

    public void setRecenzii(List<Recenzii> recenzii) {
        this.recenzii = recenzii;
    }

    @Override
    public String toString() {
        String result = "Carte " +
                " id = " + id +
                " titlu = " + titlu + "\n" +
                " autor = " + autor + "\n" +
                " editura = " + editura + "\n"+
                " sectiune : " + sectiune + "\n" +
                " descriere : " + descriere + "\n" +
                " recenzii: \n";

        for (Recenzii recenzie : recenzii) {
            result += recenzie.toString() + "\n";
        }

        return result;
    }

    public void setDisponibila(boolean disponibila) {
        this.disponibila = disponibila;
    }
}
