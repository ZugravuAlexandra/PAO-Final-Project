package database.servicii;


import database.repository.AngajatRepo;
import database.entitati.Angajat;

import java.util.List;
import java.util.Scanner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServiceAngajat {
    private AngajatRepo angajatRepo = new AngajatRepo();
    private Scanner scanner = new Scanner(System.in);

    public void meniuAngajati() {
        while (true) {
            System.out.println("----------------------------------------");
            System.out.println("Bine ati venit in meniul angajati");
            System.out.println("Cu ce va pot ajuta?");
            System.out.println("1. Adauga un nou angajat");
            System.out.println("2. Afiseaza toti angajatii");
            System.out.println("3. Actualizeaza datele unui angajat");
            System.out.println("4. Cauta un angajat dupa id-ul sau");
            System.out.println("5. Sterge un angajat");
            System.out.println("0. Iesire!");

            int p = citireOP(5);
            switch (p) {
                case 1: {
                    adaugaAngajat();
                    break;
                }
                case 2: {
                    afiseazaAngajati();
                    break;
                }
                case 3: {
                    actualizareAngajat();
                    break;
                }
                case 4: {
                    cautareAngajat();
                    break;
                }
                case 5: {
                    stergeAngajat();
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
        while(true) {
            try {
                p = Integer.parseInt(scanner.nextLine());
                if (p >= 0 && p <=maxIn) {
                    break;
                }
                else {
                    System.out.println("Trebuie sa selectati o optiune valida!");
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Selectati o optiune valida:");
            }
        }
        return p;
    }

    private Angajat citireDate() {
        int id;
        while (true) {
            System.out.println("Id: ");
            id = Integer.parseInt(scanner.nextLine());
            if (angajatRepo.getById(id) != null) {
                System.out.println("Id-ul trebuie să fie unic! Încercați din nou.");
            } else {
                break;
            }
        }
        System.out.println("Nume: ");
        String nume = scanner.nextLine();
        System.out.println("Prenume: ");
        String prenume = scanner.nextLine();

        // Verificare email
        String email;
        while (true) {
            System.out.println("Email: ");
            email = scanner.nextLine();
            if (!isValidEmail(email)) {
                System.out.println("Introduceți o adresă de email validă!");
            } else {
                break;
            }
        }

        // Verificare telefon
        String telefon;
        while (true) {
            System.out.println("Telefon: ");
            telefon = scanner.nextLine();
            if (!isValidPhoneNumber(telefon)) {
                System.out.println("Introduceți un număr de telefon valid!");
            } else {
                break;
            }
        }

        System.out.println("Data angajarii (yyyy-mm-dd): ");
        String data_angajare = scanner.nextLine();
        System.out.println("Pozitie: ");
        String pozitie = scanner.nextLine();
        return new Angajat(id, nume, prenume, email, telefon, data_angajare, pozitie);
    }


    // Metoda pentru verificarea adresei de email folosind expresii regulate
    private boolean isValidEmail(String email) {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // Metoda pentru verificarea numărului de telefon folosind expresii regulate
    private boolean isValidPhoneNumber(String telefon) {
        // Se poate ajusta în funcție de formatul specific dorit pentru numărul de telefon
        String regex = "^\\d{10}$"; // Se presupune că numărul de telefon are 10 cifre
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(telefon);
        return matcher.matches();
    }

    private void adaugaAngajat() {
        System.out.println("Adauga un nou angajat:");
        Angajat angajat = citireDate();
        angajatRepo.create(angajat);
        System.out.println("Angajatul a fost adaugat cu succes");
    }

    private void afiseazaAngajati() {
        System.out.println("Detalii despre angajati: ");
        List<Angajat> angajati = angajatRepo.readAll();
        if(angajati.isEmpty())
            System.out.println("Nu exista angajati inregistrati");
        else
            angajati.forEach(System.out::println);
    }

    private void actualizareAngajat() {
        System.out.println("Actualizeaza datele unui angajat:");
        System.out.println("Introdu id-ul angajatului: ");
        int id = citireOP(Integer.MAX_VALUE);
        Angajat angajat = angajatRepo.getById(id);
        if (angajat != null) {
            System.out.println("Introdu noile date despre angajat:");
            Angajat angajatNou = citireDate();
            angajat.setNume(angajatNou.getNume());
            angajat.setPrenume(angajatNou.getPrenume());
            angajat.setEmail(angajatNou.getEmail());
            angajat.setTelefon(angajatNou.getTelefon());
            angajat.setDataAngajare(angajatNou.getDataAngajare());
            angajat.setPozitie(angajatNou.getPozitie());
            angajatRepo.update(angajat);
            System.out.println("Angajatul a fost actualizat cu succes");
        } else {
            System.out.println("Angajatul nu a fost gasit");
        }
    }

    private void cautareAngajat() {
        System.out.println("Cauta angajatul dupa ID:");
        int id = citireOP(Integer.MAX_VALUE);
        Angajat angajat = angajatRepo.getById(id);
        if (angajat != null) {
            System.out.println(angajat);
        } else {
            System.out.println("Angajatul nu a fost gasit");
        }
    }

    private void stergeAngajat() {
        System.out.println("Sterge angajatul cu ID-ul: ");
        int id = citireOP(Integer.MAX_VALUE);
        boolean succes = angajatRepo.delete(id);
        if (succes) {
            System.out.println("Angajatul a fost sters cu succes!");
        } else {
            System.out.println("Ne pare rau! Nu exista niciun angajat cu acest id!");
        }
    }



}
