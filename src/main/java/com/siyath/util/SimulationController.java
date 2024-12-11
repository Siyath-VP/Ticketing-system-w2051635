package com.siyath.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.siyath.config.Config;
import com.siyath.model.Customer;
import com.siyath.model.TicketPool;
import com.siyath.model.Vendor;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*") // Enable CORS for all origins and headers
public class SimulationController {
    private static final String CONFIG_FILE = "config.json";
    private Config config;
    private TicketPool ticketPool;
    private Thread[] vendors;
    private Thread[] customers;
    private final AtomicBoolean isRunning = new AtomicBoolean(false);

    @PostMapping("/start-simulation")
    public synchronized String startSimulation(@RequestBody Config newConfig) {
        if (isRunning.get()) {
            return "Simulation is already running.";
        }
        this.config = newConfig;
        this.ticketPool = new TicketPool(config.getTotalTickets(), config.getMaxTicketCapacity());
        this.vendors = new Thread[config.getNumVendors()];
        this.customers = new Thread[config.getNumCustomers()];

        // Start Vendor Threads
        for (int i = 0; i < config.getNumVendors(); i++) {
            vendors[i] = new Thread(new Vendor(ticketPool, config.getTicketReleaseRate()));
            vendors[i].start();
        }

        // Start Customer Threads
        for (int i = 0; i < config.getNumCustomers(); i++) {
            customers[i] = new Thread(new Customer(ticketPool, config.getCustomerRetrievalRate()));
            customers[i].start();
        }

        isRunning.set(true);
        return "Simulation started successfully.";
    }

    @PostMapping("/stop-simulation")
    public synchronized String stopSimulation() {
        if (!isRunning.get()) {
            return "No simulation is currently running.";
        }

        // Interrupt Vendor Threads
        if (vendors != null) {
            for (Thread vendor : vendors) {
                if (vendor != null && vendor.isAlive()) {
                    vendor.interrupt();
                }
            }
        }

        // Interrupt Customer Threads
        if (customers != null) {
            for (Thread customer : customers) {
                if (customer != null && customer.isAlive()) {
                    customer.interrupt();
                }
            }
        }

        // Wait for all threads to finish
        if (vendors != null) {
            for (Thread vendor : vendors) {
                if (vendor != null) {
                    try {
                        vendor.join();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        if (customers != null) {
            for (Thread customer : customers) {
                if (customer != null) {
                    try {
                        customer.join();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        isRunning.set(false);
        return "Simulation stopped successfully.";
    }

    @PostMapping("/reset-simulation")
    public synchronized String resetSimulation() {
        // If running, stop first
        if (isRunning.get()) {
            stopSimulation();
        }

        this.config = null;
        this.ticketPool = null;
        this.vendors = null;
        this.customers = null;

        return "Simulation reset successfully.";
    }

    @PostMapping("/save-configuration")
    public synchronized String saveConfiguration(@RequestBody Config newConfig) {
        // Save the provided configuration to the JSON file
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            gson.toJson(newConfig, writer);
            return "Configuration saved successfully.";
        } catch (IOException e) {
            return "Failed to save configuration: " + e.getMessage();
        }
    }

    @GetMapping("/load-configuration")
    public synchronized Config loadConfiguration() {
        // Load configuration from the JSON file
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(CONFIG_FILE)) {
            this.config = gson.fromJson(reader, Config.class);
            return this.config;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration: " + e.getMessage());
        } catch (JsonSyntaxException e) {
            throw new RuntimeException("Invalid configuration format: " + e.getMessage());
        }
    }

    @GetMapping("/logs")
    public List<String> getLogs() {
        List<String> logs = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("ticketingSystem.log"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                logs.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read logs: " + e.getMessage());
        }
        return logs;
    }

}
