package database.entitati;

public class Recenzii {
    private String individ;
    private String recenzie;

    public Recenzii() {}
    public Recenzii(String individ, String recenzie) {
        this.individ = individ;
        this.recenzie = recenzie;
    }

    public String getIndivid() {
        return individ;
    }

    public void setIndivid(String individ) {
        this.individ = individ;
    }

    public String getRecenzie() {
        return recenzie;
    }

    public void setRecenzie(String recenzie) {
        this.recenzie = recenzie;
    }

    @Override
    public String toString() {
        return "Recenzii: " + "\n" +
                "Individ: " + individ + "\n" +
                "Recenzie: " + recenzie + "\n" ;
    }

}
