import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class Main {
    private static TicketPool ticketPool;
    private static ExecutorService executor;

    public static void main(String[] args) {
        ticketPool = new TicketPool(100); // Initial total tickets
        executor = Executors.newCachedThreadPool();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\nWelcome to the Movie Ticket Booking System");
            System.out.println("1. Configure System");
            System.out.println("2. Start Ticketing System");
            System.out.println("3. View System Status");
            System.out.println("4. Stop System");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    configureSystem(scanner);
                    break;
                case 2:
                    startTicketingSystem();
                    break;
                case 3:
                    viewSystemStatus();
                    break;
                case 4:
                    stopSystem();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
        executor.shutdown();
        System.out.println("System exited. Goodbye!");
    }

    private static void configureSystem(Scanner scanner) {
        System.out.print("Enter total number of tickets: ");
        int totalTickets = scanner.nextInt();
        ticketPool.setTotalTickets(totalTickets);
        System.out.println("Configuration updated successfully.");
    }

    private static void startTicketingSystem() {
        executor.execute(new Vendor(ticketPool));
        executor.execute(new Customer(ticketPool));
        System.out.println("Ticketing system started.");
    }

    private static void viewSystemStatus() {
        System.out.println("Current Status:");
        System.out.println("Total Tickets Available: " + ticketPool.getAvailableTickets());
    }

    private static void stopSystem() {
        executor.shutdownNow();
        System.out.println("Ticketing system stopped.");
    }
}
