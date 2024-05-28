package database.servicii;

import database.entitati.Carte;
import database.entitati.Sectiuni;
import database.entitati.Recenzii;

import java.util.*;

public class ServiceCarte {
    private Map<Integer, Carte> carti = new HashMap<>(); // am facut un map cu id-ul cartii ca si cheie
    private Scanner scanner = new Scanner(System.in);

    public void meniuCarte() {
        while(true) {
            System.out.println("----------------------------------------");
            System.out.println("Bine ati venit in meniul carte");
            System.out.println("Cu ce va pot ajuta?");
            System.out.println("1. Adauga o carte noua");
            System.out.println("2. Afiseaza toate cartile disponibile");
            System.out.println("3. Actualizeaza o carte");
            System.out.println("4. Cauta o carte dupa id-ul sau");
            System.out.println("5. Sterge o carte");
            System.out.println("0. Iesire!");

            int p = citireOP(5);
            switch(p) {
                case 1: {
                    adaugaCarte();
                    ServiceAudit.logAction("AdaugaCarte");
                    break;
                }
                case 2 : {
                    afisareCarte();
                    ServiceAudit.logAction("AfisareCarte");
                    break;
                }
                case 3 : {
                    actualizareCarte();
                    ServiceAudit.logAction("ActualizareCarte");
                    break;
                }
                case 4 : {
                    cautareCarte();
                    ServiceAudit.logAction("CautareCarte");
                    break;
                }
                case 5 : {
                    stergeCarte();
                    ServiceAudit.logAction("StergeCarte");
                    break;
                }
                case 0 : {
                    return;
                }
            }
        }
    }

    private Carte citireCarte() {
        System.out.println("Id: ");
        int id = citireOP(Integer.MAX_VALUE);

        // verificare daca id-ul este unic
        if(carti.containsKey(id)) {
            System.out.println("Id-ul trebuie sa fie unic!");
            return citireCarte();
        }

        System.out.println("Titlu: ");
        String titlu = scanner.nextLine();
        System.out.println("Autor: ");
        String autor = scanner.nextLine();
        System.out.println("Editura: ");
        String editura = scanner.nextLine();
        System.out.println("Sectiune: ");
        String sectiune = scanner.nextLine();
        System.out.println("Descriere: ");
        String descriere = scanner.nextLine();

        List<Recenzii> recenzii = new ArrayList<>();
        System.out.println("Recenzii (introduceți 'stop' pentru a opri introducerea): ");
        while (true) {
            System.out.println("Utilizator: ");
            String individ = scanner.nextLine();
            if (individ.equalsIgnoreCase("stop")) {
                break;
            }
            System.out.println("Recenzie: ");
            String recenzie = scanner.nextLine();
            recenzii.add(new Recenzii(individ, recenzie));
        }
        return new Carte(id, titlu, autor, editura, new Sectiuni(sectiune), descriere, recenzii);
    }

    private int citireOP(int maxIn) {
        int p;
        while(true) {
            try {
                p = Integer.parseInt(scanner.nextLine());
                if (p >= 0 && p <= maxIn) {
                    break;
                } else {
                    System.out.println("Trebuie sa selectati o optiune valida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Selectati o optiune valida:");
            }
        }
        return p;
    }

    public Map<Integer, Carte> getCarti() {
        return carti;
    }

    private void adaugaCarte() {
        System.out.println("Adaugati o carte: ");
        Carte carte = citireCarte();
        carti.put(carte.getId(), carte);
        System.out.println("Cartea a fost adaugata cu succes!");
    }

    private void sortareRecenzii(List<Recenzii> recenzii) {
        // Sortează lista de recenzii după numele utilizatorului
        Collections.sort(recenzii, Comparator.comparing(Recenzii::getIndivid));

//        // Afiseaza lista sortata
//        for (Recenzii recenzie : recenzii) {
//            System.out.println(recenzie);
//        }
    }

    private void afisareCarte() {
        System.out.println("Detalii despre carti");
        if(carti.isEmpty()) {
            System.out.println("Nu exista carti care au fost inregistrate");
        } else {
            for (Carte carte : carti.values()) {
                sortareRecenzii(carte.getRecenzii());
                System.out.println(carte);
                // Sortează recenziile cărții înainte de afișare


            }
        }
    }

    private void actualizareCarte() {
        System.out.println("Actualizeaza detaliile despre carte cu id-ul: ");
        int id = citireOP(Integer.MAX_VALUE);
        if(carti.containsKey(id)) {
            System.out.println("Introdu noile date despre carte: ");
            Carte updateCarte = citireCarte();
            carti.put(id, updateCarte);
            System.out.println("Cartea a fost actualizata cu succes! ");
        } else {
            System.out.println("Ne pare rau! Nu exista nicio carte cu acest id!");
        }
    }

    private void cautareCarte() {
        System.out.println("Cauta cartea cu id-ul: ");
        int id = citireOP(Integer.MAX_VALUE);
        if(carti.containsKey(id)) {
            System.out.println(carti.get(id));
        } else {
            System.out.println("Ne pare rau! Nu exista nicio carte cu acest id!");
        }
    }

    private void stergeCarte() {
        System.out.println("Sterge cartea cu ID-ul: ");
        int id = citireOP(Integer.MAX_VALUE);
        if(carti.remove(id) != null) {
            System.out.println("Cartea a fost stearsa cu succes!");
        } else {
            System.out.println("Ne pare rau! Nu exista nicio carte cu acest id!");
        }
    }
}
