package database.entitati;

public class Adresa {
    private int id;
    private String tara;
    private String oras;
    private String strada;

    public Adresa () {}

    public Adresa(int id) {
        this.id = id;
    }
    public Adresa(int id, String tara, String oras, String strada) {
        this.id = id;
        this.tara = tara;
        this.oras = oras;
        this.strada = strada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTara() {
        return tara;
    }

    public void setTara(String tara) {
        this.tara = tara;
    }

    public String getOras() {
        return oras;
    }

    public void setOras(String oras) {
        this.oras = oras;
    }

    public String getStrada() {
        return strada;
    }

    public void setStrada(String strada) {
        this.strada = strada;
    }

    @Override
    public String toString(){
        return "Adresa : " +
                "id = " + id +
                " tara: '" + tara + "\n" +
                " oras: " + oras + "\n" +
                " strada: '" + strada;
    }
}
