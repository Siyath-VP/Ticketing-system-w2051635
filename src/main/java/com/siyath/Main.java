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

@SpringBootApplication
public class Main {

    private static final String CONFIG_FILE = "config.json";
    private static final String INFO_FILE = "info.txt";

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class, args);

        // No automatic simulation start here. The simulation will be controlled via endpoints.
    }

    private static Config getDefaultConfig() {
        return new Config(100, 5, 5, 50, 2, 2); // Default values for testing
    }

    private static void saveConfig(Config config) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            gson.toJson(config, writer);
        } catch (IOException e) {
            System.err.println("Failed to save configurations: " + e.getMessage());
        }
    }

    private static Config loadConfig() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(CONFIG_FILE)) {
            return gson.fromJson(reader, Config.class);
        } catch (IOException e) {
            return null;
        }
    }

    // The following methods are no longer used since simulation is controlled by endpoints
    // but kept here if needed for potential file saving or further usage.

    private static void saveSimulationInfo(String info) {
        try (FileWriter writer = new FileWriter(INFO_FILE, true)) {
            writer.write(info + System.lineSeparator());
        } catch (IOException e) {
            System.err.println("Failed to save simulation info: " + e.getMessage());
        }
    }
}
