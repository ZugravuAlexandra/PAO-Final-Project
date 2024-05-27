package database.servicii;

import java.util.List;
import java.util.Scanner;

import database.entitati.Adresa;
import database.repository.AdresaRepo; // Asigurați-vă că importați corect clasa AdresaDAO

public class ServiceAdresa {
    private AdresaRepo adresaDAO;
    private Scanner scanner;

    public ServiceAdresa() {
        adresaDAO = new AdresaRepo();
        scanner = new Scanner(System.in);
    }

    public void meniuAdresa() {
        while (true) {
            System.out.println("----------------------------------------");
            System.out.println("Bine ati venit in meniul adresa");
            System.out.println("Cu ce va pot ajuta?");
            System.out.println("1. Adauga o adresa noua");
            System.out.println("2. Afiseaza toate adresele disponibile");
            System.out.println("3. Actualizeaza o adresa");
            System.out.println("4. Cauta o adresa dupa id-ul sau");
            System.out.println("5. Sterge o adresa");
            System.out.println("0. Iesire!");

            int p = citireOP(5);
            switch(p) {
                case 1: {
                    adaugaAdresa();
                    break;
                }
                case 2 : {
                    afisareAdrese();
                    break;
                }
                case 3 : {
                    actualizeazaAdresa();
                    break;
                }
                case 4 : {
                    cautareAdresa();
                    break;
                }
                case 5 : {
                    stergeAdresa();
                    break;
                }
                case 0 : {
                    return;
                }
            }
        }
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
                System.out.println("Trebuie sa selectati o optiune valida!");
            }
        }
        return p;
    }

    private Adresa citireAdresa() {
        // verific daca id-ul este unic
        int id;
        while (true) {
            System.out.println("Id: ");
            id = Integer.parseInt(scanner.nextLine());
            if (adresaDAO.readById(id) != null) {
                System.out.println("Id-ul trebuie să fie unic! Încercați din nou.");
            } else {
                break;
            }
        }

        System.out.println("Tara: ");
        String tara = scanner.nextLine();
        System.out.println("Oras: ");
        String oras = scanner.nextLine();
        System.out.println("Strada: ");
        String strada = scanner.nextLine();
        return new Adresa(id, tara, oras, strada);
    }

    private void adaugaAdresa() {
        System.out.println("Adauga o adresa noua!");
        Adresa adresa = citireAdresa();
        adresaDAO.create(adresa); // Adaugarea adresei utilizând AdresaDAO
    }

    private void afisareAdrese() {
        List<Adresa> adrese = adresaDAO.readAll(); // Citirea tuturor adreselor din baza de date
        System.out.println("Adresele disponibile sunt: ");
        for (Adresa adresa : adrese) {
            System.out.println(adresa);
        }
        if (adrese.isEmpty()) {
            System.out.println("Nu exista nicio adresa disponibila!");
        }
    }

    private void actualizeazaAdresa() {
        System.out.println("Introduceti id-ul adresei pe care doriti sa o actualizati: ");
        int id = Integer.parseInt(scanner.nextLine());
        Adresa adresa = adresaDAO.readById(id);
        if (adresa != null) {
            System.out.println("Introduceti noile date pentru adresa cu id-ul " + id);
            Adresa adresaNoua = citireAdresa();
            adresaNoua.setId(id);
            adresaDAO.update(adresaNoua);
            System.out.println("Adresa actualizata cu succes!");
        } else {
            System.out.println("Nu exista nicio adresa cu id-ul " + id);
        }
    }

    private void cautareAdresa() {
        System.out.println("Introduceti id-ul adresei pe care doriti sa o cautati: ");
        int id = Integer.parseInt(scanner.nextLine());
        Adresa adresa = adresaDAO.readById(id);
        if (adresa != null) {
            System.out.println("Adresa cu id-ul " + id + " este:");
            System.out.println(adresa);
        } else {
            System.out.println("Nu exista nicio adresa cu id-ul " + id);
        }
    }

    private void stergeAdresa() {
        System.out.println("Introduceti id-ul adresei pe care doriti sa o stergeti: ");
        int id = Integer.parseInt(scanner.nextLine());
        Adresa adresa = adresaDAO.readById(id);
        if (adresa != null) {
            adresaDAO.delete(id);
            System.out.println("Adresa cu id-ul " + id + " a fost stearsa cu succes!");
        } else {
            System.out.println("Nu exista nicio adresa cu id-ul " + id);
        }
    }

}
