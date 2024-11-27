import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class Main {
    private static TicketPool ticketPool;
    private static ExecutorService executor;
    private static int totalTickets;
    private static int ticketReleaseRate;
    private static int customerRetrievalRate;
    private static int maxTicketCapacity;

    public static void main(String[] args) {
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
            scanner.nextLine();

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
                    stopSystem();
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
        System.out.print("Enter total tickets available: ");
        totalTickets = scanner.nextInt();

        System.out.print("Enter ticket release rate: ");
        ticketReleaseRate = scanner.nextInt();

        System.out.print("Enter customer retrieval rate: ");
        customerRetrievalRate = scanner.nextInt();

        System.out.print("Enter max ticket pool capacity: ");
        maxTicketCapacity = scanner.nextInt();

        if (totalTickets > maxTicketCapacity) {
            System.out.println("Error: Initial total tickets cannot exceed max ticket pool capacity.");
            totalTickets = maxTicketCapacity;
        }

        ticketPool = new TicketPool(totalTickets, maxTicketCapacity);
        System.out.println("Configuration updated successfully.");
    }

    private static void startTicketingSystem() {
        if (ticketPool == null) {
            System.out.println("System is not configured. Please configure the system first.");
            return;
        }

        executor.execute(new Vendor(ticketPool, ticketReleaseRate));
        executor.execute(new Customer(ticketPool, customerRetrievalRate));
        System.out.println("Ticketing system started.");
    }

    private static void viewSystemStatus() {
        if (ticketPool == null) {
            System.out.println("System is not configured. Please configure the system first.");
            return;
        }

        System.out.println("Current Status:");
        System.out.println("Total Tickets Available: " + ticketPool.getAvailableTickets());
        System.out.println("Max Ticket Pool Capacity: " + ticketPool.getMaxCapacity());
    }

    private static void stopSystem() {
        if (!executor.isShutdown()) {
            executor.shutdownNow();
            System.out.println("Ticketing system stopped.");
        }
    }
}
