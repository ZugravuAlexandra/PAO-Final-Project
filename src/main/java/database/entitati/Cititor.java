package database.entitati;

import java.util.ArrayList;
import java.util.List;

public class Cititor extends Utilizator{
    private boolean elev;
    // sa fac si un istoric de imprumuturi

    private List<Imprumut> imprumuturi = new ArrayList<>();

    public Cititor () {}

    public Cititor(int id, String nume, String prenume, String email, String telefon, boolean elev) {
        super(id, nume, prenume, email, telefon);
        this.elev = elev;
    }

    public Cititor(int id, String nume) {
        super(id, nume);
    }

    public void removeImprumut(Imprumut imprumut) {
        imprumuturi.remove(imprumut);
    }
    public boolean isElev() {
        return elev;
    }

    public void setElev(boolean elev) {
        this.elev = elev;
    }

    @Override
    public String toString() {
        return "Cititor: " +
                "id = " + getId() + "\n" +
                " nume = " + getNume() + "\n" +
                " prenume = " + getPrenume() + "\n" +
                " elev = " + elev;
    }

    public List<Imprumut> getImprumuturi() {
        return imprumuturi;
    }

    public void addImprumut(Imprumut imprumut) {
        imprumuturi.add(imprumut);
    }

}
