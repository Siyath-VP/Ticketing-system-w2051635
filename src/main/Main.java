package main;

import main.model.Customer;
import main.model.TicketPool;
import main.model.Vendor;
import main.util.CustomLogger;

import java.util.Scanner;

public class Main {
    /**
     * main.Main method to start the ticket pool simulation.
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get user inputs
        System.out.print("Enter total tickets available (maximum tickets for the event): ");
        int totalTickets = getValidIntegerInput(scanner);

        System.out.print("Enter ticket release rate: ");
        int ticketReleaseRate = getValidIntegerInput(scanner);

        System.out.print("Enter customer retrieval rate: ");
        int customerRetrievalRate = getValidIntegerInput(scanner);

        System.out.print("Enter max ticket pool capacity: ");
        int maxTicketCapacity = getValidIntegerInput(scanner);

        System.out.print("Number of vendors: ");
        int numVendors = getValidIntegerInput(scanner);

        System.out.print("Number of customers: ");
        int numCustomers = getValidIntegerInput(scanner);

        // Initialize main.model.TicketPool
        TicketPool ticketPool = new TicketPool(totalTickets, maxTicketCapacity);

        // Start vendor and customer threads
        Thread[] vendors = new Thread[numVendors];
        Thread[] customers = new Thread[numCustomers];

        for (int i = 0; i < numVendors; i++) {
            vendors[i] = new Thread(new Vendor(ticketPool, ticketReleaseRate));
            vendors[i].start();
        }

        for (int i = 0; i < numCustomers; i++) {
            customers[i] = new Thread(new Customer(ticketPool, customerRetrievalRate));
            customers[i].start();
        }

        // Wait for all tickets to be purchased
        while (!ticketPool.isSimulationComplete()) {
            try {
                Thread.sleep(500); // Check every 500ms
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Stop all vendor and customer threads
        for (Thread vendor : vendors) {
            vendor.interrupt();
        }
        for (Thread customer : customers) {
            customer.interrupt();
        }

        CustomLogger.infoMessage("All tickets have been purchased. Simulation complete.");
    }

    private static int getValidIntegerInput(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                CustomLogger.error("Invalid input. Please enter a valid number: ");
            }
        }
    }
}
