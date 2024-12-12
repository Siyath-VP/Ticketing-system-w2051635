package com.siyath;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.siyath.config.Config;
import com.siyath.model.TicketPool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Serves as the entry point for the Spring Boot application.
 * It manages the initialization of the application context and provides utility methods for configuration and simulation data management.
 */
@SpringBootApplication
public class Main {

    private static final String CONFIG_FILE = "config.json";
    private static final String INFO_FILE = "info.txt";

    /**
     * The main method initializes the Spring Boot application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class, args);
    }


}