package database.entitati;

public class Utilizator {
    private int id;
    private String nume;
    private String prenume;
    private String email;
    private String telefon;

    public Utilizator(){}

    public Utilizator(int id, String nume, String prenume, String email, String telefon){
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.email = email;
        this.telefon = telefon;
    }

    public Utilizator(int id, String nume) {
        this.id = id;
        this.nume = nume;
    }

    public int getId() {
        return id;
    }

    public void setId (int id){
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    @Override
    public String toString() {
        return "Utilizator: " +
                "id = " + id +
                ", nume = " + nume +
                ", email='" + email +
                ", telefon='" + telefon +
                '}';
    }

}
