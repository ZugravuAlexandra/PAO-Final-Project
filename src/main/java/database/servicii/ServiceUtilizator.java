package database.servicii;

import database.repository.UtilizatorRepo;
import database.entitati.Utilizator;

import java.util.List;
import java.util.Scanner;

public class ServiceUtilizator {
    private UtilizatorRepo utilizatorRepo;
    private Scanner scanner;

    public ServiceUtilizator() {
        utilizatorRepo = new UtilizatorRepo();
        scanner = new Scanner(System.in);
    }

    public void meniuUtilizator() {
        while(true) {
            System.out.println("----------------------------------------------------------");
            System.out.println("Bine ați venit în meniul utilizatorilor");
            System.out.println("Ce doriți să faceți?");
            System.out.println("1. Adăugare utilizator");
            System.out.println("2. Afișare utilizatori");
            System.out.println("3. Actualizare utilizator");
            System.out.println("4. Ștergere utilizator");
            System.out.println("0. Înapoi");

            int optiune = citesteOptiune(4);
            switch(optiune) {
                case 1: {
                    adaugaUtilizator();
                    break;
                }
                case 2 : {
                    afiseazaUtilizatori();
                    break;
                }
                case 3 : {
                    actualizeazaUtilizator();
                    break;
                }
                case 4 : {
                    stergeUtilizator();
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
                System.out.println("Trebuie să selectați o opțiune validă!");
            }
        }
        return optiune;
    }

    private Utilizator citesteUtilizator () {
        System.out.println("Id: ");
        int id = Integer.parseInt(scanner.nextLine());

        // Verificare dacă id-ul este unic
        for (Utilizator user : utilizatorRepo.readAll()) {
            if (user.getId() == id) {
                System.out.println("Id-ul trebuie să fie unic! Introduceți un alt ID.");
                return citesteUtilizator(); // Reapelăm metoda pentru a citi un alt id
            }
        }
        System.out.println("Nume: ");
        String nume = scanner.nextLine();
        System.out.println("Prenume: ");
        String prenume = scanner.nextLine();
        System.out.println("Email: ");
        String email = scanner.nextLine();
        System.out.println("Telefon: ");
        String telefon = scanner.nextLine();

        return new Utilizator(id, nume, prenume, email, telefon);
    }

    private void adaugaUtilizator() {
        System.out.println("Adăugare utilizator!");
        Utilizator utilizator = citesteUtilizator();
        utilizatorRepo.create(utilizator);
        System.out.println("Utilizatorul a fost adăugat cu succes!");
    }

    private void afiseazaUtilizatori() {
        System.out.println("Afișare utilizatori!");
        List<Utilizator> utilizatori = utilizatorRepo.readAll();
        if(utilizatori.isEmpty())
            System.out.println("Nu există utilizatori înregistrați");
        else
            utilizatori.forEach(System.out::println);
    }

    private void actualizeazaUtilizator() {
        System.out.println("Actualizare utilizator!");
        System.out.println("Introduceți ID-ul utilizatorului pe care doriți să-l actualizați:");
        int id = Integer.parseInt(scanner.nextLine());

        // Verificăm dacă utilizatorul există în baza de date
        Utilizator existent = utilizatorRepo.findById(id);
        if (existent == null) {
            System.out.println("Utilizatorul nu există în baza de date.");
            return;
        }

        // Citim datele actualizate pentru utilizator
        Utilizator actualizare = citesteUtilizator();
        actualizare.setId(id);

        // Actualizăm utilizatorul în baza de date
        utilizatorRepo.update(actualizare);
        System.out.println("Utilizatorul a fost actualizat cu succes!");
    }

    private void stergeUtilizator() {
        System.out.println("Ștergere utilizator!");
        System.out.println("Introduceți ID-ul utilizatorului pe care doriți să-l ștergeți:");
        int id = Integer.parseInt(scanner.nextLine());

        // Verificăm dacă utilizatorul există în baza de date
        Utilizator existent = utilizatorRepo.findById(id);
        if (existent == null) {
            System.out.println("Utilizatorul nu există în baza de date.");
            return;
        }

        // Confirmare pentru ștergerea utilizatorului
        System.out.println("Sunteți sigur că doriți să ștergeți acest utilizator? (da/nu)");
        String confirmare = scanner.nextLine().trim().toLowerCase();
        if (confirmare.equals("da")) {
            // Ștergem utilizatorul din baza de date
            utilizatorRepo.delete(id);
            System.out.println("Utilizatorul a fost șters cu succes!");
        } else {
            System.out.println("Operația de ștergere a fost anulată.");
        }
    }

}
