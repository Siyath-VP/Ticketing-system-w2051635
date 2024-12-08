package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CustomLogger {
    private static final String LOGGER = "ticketingSystem.log";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static synchronized void log (String type, String message){
        String timeStamp = LocalDateTime.now().format(DATE_TIME_FORMATTER);
        String logEntry = String.format("[%s] [%s] %s",timeStamp, type, message);
        System.out.println(logEntry);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOGGER))){
            writer.write(logEntry);
            writer.newLine();

        }catch (IOException e){
            System.err.println("Error while logging " + e.getMessage());
        }

    }

    public static void infoMessage (String message){
        log("Info", message);
    }

    public static void error (String message){
        log("Error", message);
    }

    public static void warning (String message){
        log("Warning", message);
    }
}
