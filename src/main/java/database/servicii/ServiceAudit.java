package database.servicii;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ServiceAudit {
    private static final String FILE_NAME = "audit.csv"; // Numele fișierului de audit
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // Formatul datei și orei

    public static void logAction(String actionName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) // Deschide fișierul pentru scriere
        {
            String timestamp = dtf.format(LocalDateTime.now()); // Obține data și ora curentă
            writer.write(actionName + "," + timestamp); // Scrie numele acțiunii și timestamp-ul în fișier
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
