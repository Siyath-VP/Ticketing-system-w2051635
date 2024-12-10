package com.siyath;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.siyath.config.Config;
import com.siyath.model.Customer;
import com.siyath.model.TicketPool;
import com.siyath.model.Vendor;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
//last
//    private static final String CONFIG_FILE = "config.json";
//    private static final String INFO_FILE = "info.txt";

    public static void main(String[] args) {
        SpringApplication.run(ApplicationCLI.class, args);

//        Scanner scanner = new Scanner(System.in);
//        Config config = null;
//
//        System.out.println("Do you want to load saved configurations? (yes/no): ");
//        String choice = scanner.nextLine().trim().toLowerCase();
//
//        if (choice.equals("yes")) {
//            config = loadConfig();
//            if (config != null) {
//                System.out.println("Config loaded successfully.");
//                System.out.println("Do you want to start the program? (yes/no): ");
//                String startChoice = scanner.nextLine().trim().toLowerCase();
//                if (startChoice.equals("no")) {
//                    System.out.println("Program exited.");
//                    return;
//                }
//            } else {
//                System.out.println("Failed to load configurations. Please enter new configurations.");
//            }
//        }
//
//        if (config == null) {
//            config = getUserConfigurations(scanner);
//            System.out.println("Do you want to save these configurations? (yes/no): ");
//            String saveChoice = scanner.nextLine().trim().toLowerCase();
//            if (saveChoice.equals("yes")) {
//                saveConfig(config);
//            }
//        }
//
//        TicketPool ticketPool = new TicketPool(config.getTotalTickets(), config.getMaxTicketCapacity());
//        runSimulation(config, ticketPool);
    }

//    private static Config getUserConfigurations(Scanner scanner) {
//        System.out.print("Enter total tickets available: ");
//        int totalTickets = getValidIntegerInput(scanner);
//
//        System.out.print("Enter ticket release rate: ");
//        int ticketReleaseRate = getValidIntegerInput(scanner);
//
//        System.out.print("Enter customer retrieval rate: ");
//        int customerRetrievalRate = getValidIntegerInput(scanner);
//
//        System.out.print("Enter max ticket pool capacity: ");
//        int maxTicketCapacity = getValidIntegerInput(scanner);
//
//        System.out.print("Enter number of vendors: ");
//        int numVendors = getValidIntegerInput(scanner);
//
//        System.out.print("Enter number of customers: ");
//        int numCustomers = getValidIntegerInput(scanner);
//
//        return new Config(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity, numVendors, numCustomers);
//    }
//
//    private static void saveConfig(Config config) {
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
//            gson.toJson(config, writer);
//            System.out.println("Configurations saved to " + CONFIG_FILE);
//        } catch (IOException e) {
//            System.err.println("Failed to save configurations: " + e.getMessage());
//        }
//    }
//
//    private static Config loadConfig() {
//        Gson gson = new Gson();
//        try (FileReader reader = new FileReader(CONFIG_FILE)) {
//            return gson.fromJson(reader, Config.class);
//        } catch (IOException e) {
//            System.err.println("Failed to load configurations: " + e.getMessage());
//            return null;
//        }
//    }
//
//    private static void runSimulation(Config config, TicketPool ticketPool) {
//        Thread[] vendors = new Thread[config.getNumVendors()];
//        Thread[] customers = new Thread[config.getNumCustomers()];
//
//        for (int i = 0; i < config.getNumVendors(); i++) {
//            vendors[i] = new Thread(new Vendor(ticketPool, config.getTicketReleaseRate()));
//            vendors[i].start();
//        }
//
//        for (int i = 0; i < config.getNumCustomers(); i++) {
//            customers[i] = new Thread(new Customer(ticketPool, config.getCustomerRetrievalRate()));
//            customers[i].start();
//        }
//
//        while (!ticketPool.isSimulationComplete()) {
//            try {
//                Thread.sleep(500); // Check every 500ms
//            } catch (InterruptedException e) {
//                Thread.currentThread().interrupt();
//            }
//        }
//
//        for (Thread vendor : vendors) {
//            vendor.interrupt();
//        }
//
//        for (Thread customer : customers) {
//            customer.interrupt();
//        }
//
//        saveSimulationInfo("All tickets have been purchased. Simulation complete.");
//        System.out.println("Simulation complete. Information saved to " + INFO_FILE);
//    }
//
//    private static void saveSimulationInfo(String info) {
//        try (FileWriter writer = new FileWriter(INFO_FILE, true)) {
//            writer.write(info + System.lineSeparator());
//        } catch (IOException e) {
//            System.err.println("Failed to save simulation info: " + e.getMessage());
//        }
//    }
//
//    private static int getValidIntegerInput(Scanner scanner) {
//        while (true) {
//            try {
//                return Integer.parseInt(scanner.nextLine());
//            } catch (NumberFormatException e) {
//                System.out.println("Invalid input. Please enter a valid number.");
//            }
//        }
//    }
}
