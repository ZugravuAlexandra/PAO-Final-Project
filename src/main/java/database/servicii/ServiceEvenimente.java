package database.servicii;

import database.repository.EvenimentRepo;
import database.entitati.Evenimente;
import database.entitati.Adresa;
import database.repository.AdresaRepo;

import java.util.List;
import java.util.Scanner;

public class ServiceEvenimente {
    private EvenimentRepo evenimenteRepo;
    private Scanner scanner;

    public ServiceEvenimente() {
        evenimenteRepo = new EvenimentRepo();
        scanner = new Scanner(System.in);
    }

    public void meniuEvenimente() {
        while(true) {
            System.out.println("----------------------------------------------------------");
            System.out.println("Bine ați venit în meniul evenimentelor");
            System.out.println("Ce doriți să faceți?");
            System.out.println("1. Adăugare eveniment");
            System.out.println("2. Afișare evenimente");
            System.out.println("3. Actualizare eveniment");
            System.out.println("4. Ștergere eveniment");
            System.out.println("0. Înapoi");

            int optiune = citesteOptiune(4);
            switch(optiune) {
                case 1: {
                    adaugaEveniment();
                    break;
                }
                case 2 : {
                    afiseazaEvenimente();
                    break;
                }
                case 3 : {
                    actualizeazaEveniment();
                    break;
                }
                case 4 : {
                    stergeEveniment();
                    break;
                }
                case 0 : {
                    return;
                }
            }
        }
    }

    private int citesteOptiune(int maxIn) {
        int optiune;
        while(true) {
            try {
                optiune = Integer.parseInt(scanner.nextLine());
                if (optiune >= 0 && optiune <=maxIn) {
                    break;
                }
                else {
                    System.out.println("Trebuie să selectați o opțiune validă!");
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Selectați o opțiune validă:");
            }
        }
        return optiune;
    }

    private Evenimente citesteEveniment() {
        int id;
        while (true) {
            System.out.println("ID: ");
            id = Integer.parseInt(scanner.nextLine());

            // Verificare dacă id-ul evenimentului este unic
            boolean idUnic = true;
            for (Evenimente eveniment : evenimenteRepo.readAll()) {
                if (eveniment.getId() == id) {
                    System.out.println("ID-ul trebuie să fie unic! Introduceți alt ID.");
                    idUnic = false;
                    break;
                }
            }
            if (idUnic) {
                break; // Ieșim din bucla while dacă ID-ul este unic
            }
        }

        System.out.println("Titlu: ");
        String titlu = scanner.nextLine();
        System.out.println("Data (yyyy-mm-dd): ");
        String data = scanner.nextLine();
        System.out.println("Ora (hh:mm:ss): ");
        String ora = scanner.nextLine();

        int adresaId;
        Adresa adresa;
        while (true) {
            // Verificare dacă adresa există în baza de date
            System.out.println("Adresa (introduceți ID-ul adresei): ");
            adresaId = Integer.parseInt(scanner.nextLine());
            adresa = AdresaRepo.readById(adresaId);
            if (adresa == null) {
                System.out.println("Adresa introdusă nu există în baza de date. Introduceți alt ID pentru adresa.");
            } else {
                break; // Ieșim din bucla while dacă adresa este validă
            }
        }

        int pret;
        while (true) {
            // Verificare dacă prețul este un număr
            System.out.println("Pret: ");
            try {
                pret = Integer.parseInt(scanner.nextLine());
                break; // Ieșim din bucla while dacă prețul este un număr
            } catch (NumberFormatException e) {
                System.out.println("Trebuie să introduceți un număr pentru preț!");
            }
        }

        // Construirea unui obiect Evenimente cu adresa completată
        Evenimente eveniment = new Evenimente(id, titlu, data, ora, adresa, pret);

        return eveniment;
    }



    private void adaugaEveniment() {
        System.out.println("Adăugare eveniment!");
        Evenimente eveniment = citesteEveniment();
        evenimenteRepo.create(eveniment);
        System.out.println("Evenimentul a fost adăugat cu succes!");
    }

    private void afiseazaEvenimente() {
        System.out.println("Afișare evenimente!");
        List<Evenimente> evenimente = evenimenteRepo.readAll();
        if(evenimente.isEmpty())
            System.out.println("Nu există evenimente înregistrate");
        else
            evenimente.forEach(System.out::println);
    }

    private void actualizeazaEveniment() {
        System.out.println("Actualizare eveniment!");
        System.out.println("Introduceți ID-ul evenimentului pe care doriți să-l actualizați:");
        int id = Integer.parseInt(scanner.nextLine());

        // Verificăm dacă evenimentul există în baza de date
        Evenimente evenimentExist = evenimenteRepo.readById(id);
        if (evenimentExist != null) {
            // Citim noul eveniment
            Evenimente evenimentNou = citesteEveniment();
            evenimentNou.setId(id); // Setăm ID-ul noului eveniment

            // Actualizăm evenimentul în baza de date
            evenimenteRepo.update(evenimentNou);
            System.out.println("Evenimentul a fost actualizat cu succes!");
        } else {
            System.out.println("Nu există un eveniment cu ID-ul specificat!");
        }
    }

    private void stergeEveniment() {
        System.out.println("Ștergere eveniment!");
        System.out.println("Introduceți ID-ul evenimentului pe care doriți să-l ștergeți:");
        int id = Integer.parseInt(scanner.nextLine());

        // Verificăm dacă evenimentul există în baza de date
        Evenimente evenimentExist = evenimenteRepo.readById(id);
        if (evenimentExist != null) {
            // Ștergem evenimentul din baza de date
            evenimenteRepo.delete(id);
            System.out.println("Evenimentul a fost șters cu succes!");
        } else {
            System.out.println("Nu există un eveniment cu ID-ul specificat!");
        }
    }

}
