package database.entitati;

public class Evenimente {
    private int id;
    private String titlu;
    private String data;
    private String ora;
    private Adresa adresa;
    private int pret;

    public Evenimente() {}

    public Evenimente(int id, String titlu, String data, String ora, Adresa adresa, int pret) {
        this.id = id;
        this.titlu = titlu;
        this.data = data;
        this.ora = ora;
        this.adresa = adresa;
        this.pret = pret;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getOra() {
        return ora;
    }

    public void setOra(String ora) {
        this.ora = ora;
    }

    public Adresa getAdresa() {
        return adresa;
    }

    public void setAdresa(Adresa adresa) {
        this.adresa = adresa;
    }

    public int getPret() {
        return pret;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }

    @Override
    public String toString() {
        return "Evenimente: " + "\n" +
                " id: " + id + "\n" +
                " titlu: " + titlu + "\n" +
                " data: " + data + "\n" +
                " ora: " + ora + "\n" +
                " locatia: " + adresa + "\n" +
                " pret: " + pret;
    }
}
