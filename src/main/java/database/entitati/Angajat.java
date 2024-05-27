package database.entitati;

public class Angajat extends Utilizator{

    private String data_angajare;
    private String pozitie;

    public Angajat() {}

    public Angajat(int id, String nume, String prenume, String email, String telefon, String data_angajare, String pozitie) {
        super(id, nume, prenume, email, telefon);
        this.data_angajare = data_angajare;
        this.pozitie = pozitie;
    }

    public String getDataAngajare() {
        return data_angajare;
    }

    public void setDataAngajare(String data_angajare) {
        this.data_angajare = data_angajare;
    }

    public String getPozitie() {
        return pozitie;
    }

    public void setPozitie(String pozitie) {
        this.pozitie = pozitie;
    }

    @Override
    public String toString() {
        return "Angajat: " +
                "id = " + getId() + "\n" +
                " nume = " + getNume() + "\n" +
                " prenume = " + getPrenume() + "\n" +
                " data angajarii: " + data_angajare + "\n" +
                " pozitie: " + pozitie + "\n";
    }
}
