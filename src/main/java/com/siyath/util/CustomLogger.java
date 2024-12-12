package com.siyath.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class provides custom logging functionality for the application.
 * It writes log entries to the console and a log file.
 * The log entries include a timestamp, log type (e.g., Info, Error, Warning), and the message.
 */
public class CustomLogger {
    private static final String LOGGER = "ticketingSystem.log"; // Log file name
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Writes a log entry to both the console and the log file.
     * This method is synchronized to ensure thread safety.
     *
     * @param type    The type of log entry (e.g., Info, Error, Warning).
     * @param message The message to log.
     */
    public static synchronized void log(String type, String message) {
        // Format the log entry with a timestamp, type, and message
        String timeStamp = LocalDateTime.now().format(DATE_TIME_FORMATTER);
        String logEntry = String.format("[%s] [%s] %s", timeStamp, type, message);

        System.out.println(logEntry);

        // Write the log entry to the log file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOGGER, true))) { // Append mode enabled
            writer.write(logEntry);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error while logging: " + e.getMessage());
        }
    }

    /**
     * Logs an informational message.
     *
     * @param message The informational message to log.
     */
    public static void infoMessage(String message) {
        log("Info", message);
    }

    /**
     * Logs an error message.
     *
     * @param message The error message to log.
     */
    public static void error(String message) {
        log("Error", message);
    }

    /**
     * Logs a warning message.
     *
     * @param message The warning message to log.
     */
    public static void warning(String message) {
        log("Warning", message);
    }
}