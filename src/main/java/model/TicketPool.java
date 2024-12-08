package model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.CustomLogger;

import java.util.Vector;

public class TicketPool {

    private static final Logger logger = LogManager.getLogger(TicketPool.class);
    private int totalTickets; // Total tickets yet to be released
    private final int maxCapacity; // Maximum capacity of the pool
    private final Vector<Integer> ticketPool; // Vector to store tickets
    private boolean simulationComplete = false;

    public TicketPool(int totalTickets, int maxCapacity) {
        this.totalTickets = totalTickets;
        this.maxCapacity = maxCapacity;
        this.ticketPool = new Vector<>(maxCapacity); // Initialize the ticket pool
    }

    public synchronized boolean isSimulationComplete() {
        return simulationComplete && ticketPool.isEmpty();
    }

    public synchronized void addTickets(int count) {
        if (totalTickets == 0 && ticketPool.isEmpty()) {
            simulationComplete = true;
            logger.info("All tickets released.");
            notifyAll(); // Notify waiting threads
            return;
        }

        int ticketsToAdd = Math.min(count, Math.min(totalTickets, maxCapacity - ticketPool.size()));
        for (int i = 0; i < ticketsToAdd; i++) {
            ticketPool.add(1);
        }
        totalTickets -= ticketsToAdd;

        logger.info("Vendor released " + ticketsToAdd + " tickets. Tickets in pool: "
                + ticketPool.size() + ", Tickets remaining: " + totalTickets);

        notifyAll(); // Notify customers about ticket availability
    }

    public synchronized void purchaseTickets(int count) {
        while (ticketPool.isEmpty() && !simulationComplete) {
            try {
                wait(); // Wait for tickets to be added
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        if (simulationComplete && ticketPool.isEmpty()) {
            return; // No tickets left to purchase
        }

        int ticketsToBuy = Math.min(count, ticketPool.size());
        for (int i = 0; i < ticketsToBuy; i++) {
            ticketPool.remove(0); // Remove tickets from the pool
        }

        logger.info("Customer purchased " + ticketsToBuy + " tickets. Tickets left in pool: "
                + ticketPool.size());

        notifyAll(); // Notify vendors about available space
    }
}
