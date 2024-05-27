package database;

import database.config.DatabaseConfiguration;
import database.servicii.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Stabilirea conexiunii la baza de date
        DatabaseConfiguration.getDatabaseConnection();

        // Crearea scanner-ului pentru citirea opțiunilor de la tastatură
        Scanner scanner = new Scanner(System.in);

        // Crearea instanțelor serviciilor necesare
        ServiceCarte serviceCarte = new ServiceCarte();
        ServiceCititor serviceCititor = new ServiceCititor(serviceCarte);
        ServiceAdresa serviceAdresa = new ServiceAdresa();
        ServiceBiblioteca serviceBiblioteca = new ServiceBiblioteca();
        ServiceAngajat serviceAngajat = new ServiceAngajat();
        ServiceEvenimente serviceEvenimente = new ServiceEvenimente();
        ServiceUtilizator serviceUtilizator = new ServiceUtilizator();

        while (true) {
            System.out.println("\033[1;34m----------------------------------------\033[0m");
            System.out.println("\033[1;32mBine ati venit in meniul principal\033[0m");
            System.out.println("\033[1;34m----------------------------------------\033[0m");
            System.out.println("\033[1;33mCe tip de utilizator sunteți?\033[0m");
            System.out.println("\033[1;35m1. Manager\033[0m");
            System.out.println("\033[1;35m2. Cititor\033[0m");
            System.out.println("\033[1;35m0. Ieșire\033[0m");
            System.out.println("\033[1;33mAlegeti optiunea: \033[0m");

            int optiune = citesteOptiune(scanner, 2);

            switch (optiune) {
                case 1:
                    meniuManager(scanner, serviceAdresa, serviceBiblioteca, serviceAngajat, serviceCititor, serviceCarte, serviceEvenimente, serviceUtilizator);
                    break;
                case 2:
                    serviceCititor.meniuCititor();
                    break;
                case 0:
                    System.out.println("La revedere!");
                    scanner.close();
                    DatabaseConfiguration.closeDatabaseConnection();
                    System.exit(0);
                    break;
            }
        }
    }

    private static void meniuManager(Scanner scanner, ServiceAdresa serviceAdresa, ServiceBiblioteca serviceBiblioteca, ServiceAngajat serviceAngajat, ServiceCititor serviceCititor, ServiceCarte serviceCarte, ServiceEvenimente serviceEvenimente, ServiceUtilizator serviceUtilizator) {
        while (true) {
            System.out.println("\033[1;34m----------------------------------------\033[0m");
            System.out.println("\033[1;32mMeniul Managerului\033[0m");
            System.out.println("\033[1;34m----------------------------------------\033[0m");
            System.out.println("\033[1;33mCe doriți să gestionați?\033[0m");
            System.out.println("\033[1;35m1. Adrese\033[0m");
            System.out.println("\033[1;35m2. Biblioteci\033[0m");
            System.out.println("\033[1;35m3. Angajati\033[0m");
            System.out.println("\033[1;35m4. Cititori\033[0m");
            System.out.println("\033[1;35m5. Carti\033[0m");
            System.out.println("\033[1;35m6. Evenimente\033[0m");
            System.out.println("\033[1;35m7. Utilizatori\033[0m");
            System.out.println("\033[1;35m0. Înapoi la meniul principal\033[0m");

            int optiune = citesteOptiune(scanner, 7);

            switch (optiune) {
                case 1:
                    serviceAdresa.meniuAdresa();
                    break;
                case 2:
                    serviceBiblioteca.meniuBiblioteca();
                    break;
                case 3:
                    serviceAngajat.meniuAngajati();
                    break;
                case 4:
                    serviceCititor.meniuCititor();
                    break;
                case 5:
                    serviceCarte.meniuCarte();
                    break;
                case 6:
                    serviceEvenimente.meniuEvenimente();
                    break;
                case 7:
                    serviceUtilizator.meniuUtilizator();
                    break;
                case 0:
                    return;
            }
        }
    }

    private static int citesteOptiune(Scanner scanner, int maxIn) {
        int optiune;
        while (true) {
            try {
                optiune = Integer.parseInt(scanner.nextLine());
                if (optiune >= 0 && optiune <= maxIn) {
                    break;
                } else {
                    System.out.println("Trebuie să selectați o opțiune validă!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Trebuie să selectați o opțiune validă!");
            }
        }
        return optiune;
    }
}
