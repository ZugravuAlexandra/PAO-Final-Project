package database.entitati;

public class Imprumut {

    private static int nr = 1;
    private int id;
    private Cititor cititor;
    private Carte carte;
    private String data_imprumut;

    public Imprumut(){
        this.id = nr++;
    }

    public Imprumut(Cititor cititor, Carte carte, String data_imprumut) {
        this.id = nr++;
        this.cititor = cititor;
        this.carte = carte;
        this.data_imprumut = data_imprumut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cititor getCititor() {
        return cititor;
    }

    public void setCititor(Cititor cititor) {
        this.cititor = cititor;
    }

    public Carte getCarte() {
        return carte;
    }

    public void setCarte(Carte carte) {
        this.carte = carte;
    }

    public String getData_imprumut() {
        return data_imprumut;
    }

    public void setData_imprumut(String data_imprumut) {
        this.data_imprumut = data_imprumut;
    }

    @Override
    public String toString() {
        return "Imprumut : " +
                "id = " + id + "\n" +
                " Cititor : " + cititor + "\n" +
                " Carte : " + carte + "\n" +
                " Data imprumut : " + getData_imprumut();
    }
}
