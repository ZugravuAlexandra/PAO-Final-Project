package database.servicii;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ServiceAudit {
    private static final String FILE_NAME = "audit.csv";
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void logAction(String actionName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            String timestamp = dtf.format(LocalDateTime.now());
            writer.write(actionName + "," + timestamp);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
