package database.servicii;

import database.entitati.Cititor;
import database.entitati.Carte;
import database.entitati.Imprumut;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Map;

public class ServiceCititor {

    private List<Cititor> cititori = new ArrayList<>();
    private ServiceCarte serviceCarte;
    private Scanner scanner = new Scanner(System.in);

    public ServiceCititor(ServiceCarte serviceCarte) {
        this.serviceCarte = serviceCarte;
    }

    public void meniuCititor() {
        while (true) {
            System.out.println("----------------------------------------");
            System.out.println("Bine ati venit in meniul cititorului");
            System.out.println("Cu ce va pot ajuta?");
            System.out.println("1. Arata toate cartile disponibile");
            System.out.println("2. Imprumuta o carte");
            System.out.println("3. Returneaza o carte");
            System.out.println("4. Afiseaza cartile imprumutate");
            System.out.println("0. Iesire!");

            int p = citireOP(4);
            switch (p) {
                case 1: {
                    afisareCarte();
                    ServiceAudit.logAction("AfisareCarte");
                    break;
                }
                case 2: {
                    imprumutaCarte();
                    ServiceAudit.logAction("ImprumutaCarte");
                    break;
                }
                case 3: {
                    returneazaCarte();
                    ServiceAudit.logAction("ReturneazaCarte");
                    break;
                }
                case 4: {
                    afisareCartiImprumutate();
                    ServiceAudit.logAction("AfisareCartiImprumutate");
                    break;
                }
                case 0: {
                    return;
                }
            }
        }
    }

    private int citireOP(int maxIn) {
        int p;
        while (true) {
            try {
                p = Integer.parseInt(scanner.nextLine());
                if (p >= 0 && p <= maxIn) {
                    break;
                } else {
                    System.out.println("Trebuie sa selectati o optiune valida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Trebuie sa selectati o optiune valida!");
            }
        }
        return p;
    }

    private void afisareCarte() {
        Map<Integer, Carte> carti = serviceCarte.getCarti(); // Obținem mapa de cărți de la ServiceCarte
        if (carti.isEmpty()) {
            System.out.println("Nu exista carti disponibile in acest moment!");
            return;
        }
        System.out.println("Acestea sunt cartile disponibile: ");
        for (Carte carte : carti.values()) {
            System.out.println(carte);
        }
    }

    // cont pentru cititor

    private Cititor contCititor() {
        System.out.println("Introduceti numele cititorului: ");
        String nume = scanner.nextLine();

        // generam un id unic pentru cititor
        int id = cititori.size() + 1;
        Cititor cititor = new Cititor(id, nume);
        cititori.add(cititor);

        System.out.println("Bine ati venit " + nume + "!");
        System.out.println("Contul a fost creat cu succes! Id-ul contului este: " + id);
        System.out.println("Va rugam sa va logati cu id-ul contului pentru a putea imprumuta carti.");

        return cititor;
    }

    private void imprumutaCarte() {
        System.out.println("Introduceti id-ul cartii pe care doriti sa o imprumutati: ");
        int id = citireOP(Integer.MAX_VALUE);
        Carte c = serviceCarte.getCarti().get(id);
        if (c == null) {
            System.out.println("Cartea nu exista!");
            return;
        }

        Cititor cititor = null;

        // Verificam daca cititorul este inregistrat
        if (cititori.isEmpty()) {
            System.out.println("Nu exista niciun cititor inregistrat!");
            System.out.println("Pentru a putea imprumuta o carte, va rugam sa va creati un cont.");
            cititor = contCititor();
        } else {
            System.out.println("Introduceti id-ul cititorului: ");
            int idCititor = citireOP(Integer.MAX_VALUE);

            // Cautam cititorul dupa id
            for (Cititor cit : cititori) {
                if (cit.getId() == idCititor) {
                    cititor = cit;
                    break;
                }
            }
            if (cititor == null) {
                System.out.println("Cititorul nu exista!");
                return;
            }
        }

        // Verificam daca cititorul are deja cartea imprumutata
        for (Imprumut imprumut : cititor.getImprumuturi()) {
            if (imprumut.getCarte().getId() == c.getId()) {
                System.out.println("Cititorul are deja cartea imprumutata!");
                return;
            }
        }

        // Cream un nou imprumut
        Imprumut imprumut = new Imprumut(cititor, c, LocalDate.now().toString());

        // Adaugam imprumutul la lista de imprumuturi a cititorului
        cititor.addImprumut(imprumut);

        // Scoatem cartea din mapa de carti disponibile
        serviceCarte.getCarti().remove(id);

        System.out.println("Cartea " + c.getTitlu() + " a fost imprumutata cu succes de catre " + cititor.getNume());
    }

    private void returneazaCarte() {
        System.out.println("Introduceti id-ul cartii pe care doriti sa o returnati: ");
        int id = citireOP(Integer.MAX_VALUE);
        Cititor cititor = null;
        Imprumut imprumutDeReturnat = null;

        // Cautam imprumutul si cititorul asociat
        for (Cititor c : cititori) {
            for (Imprumut imprumut : c.getImprumuturi()) {
                if (imprumut.getCarte().getId() == id) {
                    imprumutDeReturnat = imprumut;
                    cititor = c;
                    break;
                }
            }
            if (imprumutDeReturnat != null) {
                break;
            }
        }

        if (imprumutDeReturnat == null) {
            System.out.println("Imprumutul nu exista!");
            return;
        }

        // Adaugam cartea inapoi in mapa de carti disponibile
        serviceCarte.getCarti().put(imprumutDeReturnat.getCarte().getId(), imprumutDeReturnat.getCarte());

        // Scoatem imprumutul din lista de imprumuturi a cititorului
        cititor.removeImprumut(imprumutDeReturnat);

        System.out.println("Cartea " + imprumutDeReturnat.getCarte().getTitlu() + " a fost returnata cu succes de catre " + imprumutDeReturnat.getCititor().getNume());
    }

    private void afisareCartiImprumutate() {
        System.out.println("Introduceti id-ul cititorului pentru a afisa cartile imprumutate: ");
        int idCititor = citireOP(Integer.MAX_VALUE);
        Cititor cititor = null;
        for (Cititor cit : cititori) {
            if (cit.getId() == idCititor) {
                cititor = cit;
                break;
            }
        }
        if (cititor == null) {
            System.out.println("Cititorul nu exista!");
            return;
        }
        List<Imprumut> imprumuturi = cititor.getImprumuturi();
        if (imprumuturi.isEmpty()) {
            System.out.println("Cititorul nu a imprumutat nicio carte.");
            return;
        }
        System.out.println("Cartile imprumutate de catre " + cititor.getNume() + ":");
        for (Imprumut imprumut : imprumuturi) {
            System.out.println(imprumut.getCarte().getTitlu() + " - Data imprumut: " + imprumut.getData_imprumut());
        }
    }
}
