package com.siyath.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Vector;

/**
 * Manages a shared pool of tickets in the simulation.
 * It handles the addition of tickets by vendors and their purchase by customers while ensuring thread-safe operations using synchronized methods.
 */
public class TicketPool {

    private static final Logger logger = LogManager.getLogger(TicketPool.class);
    private int totalTickets;
    private final int maxCapacity;
    private final Vector<Integer> ticketPool;
    private boolean simulationComplete = false; // Indicate if the simulation is complete

    /**
     * Constructs a TicketPool with the specified total tickets and maximum capacity.
     *
     * @param totalTickets The total number of tickets available for the simulation.
     * @param maxCapacity  The maximum number of tickets the pool can hold at any time.
     */
    public TicketPool(int totalTickets, int maxCapacity) {
        this.totalTickets = totalTickets;
        this.maxCapacity = maxCapacity;
        this.ticketPool = new Vector<>(maxCapacity); // Initialize the ticket pool
    }

    /**
     * Checks if the simulation is complete.
     * The simulation is considered complete when all tickets have been released and purchased.
     *
     * @return true if the simulation is complete and the ticket pool is empty, false otherwise.
     */
    public synchronized boolean isSimulationComplete() {
        return simulationComplete && ticketPool.isEmpty();
    }

    /**
     * Adds tickets to the ticket pool.
     * This method is called by vendors to release tickets into the pool.
     * If all tickets are released and the pool is empty, the simulation is marked as complete.
     *
     * @param count The number of tickets to add to the pool.
     */
    public synchronized void addTickets(int count) {
        // If no tickets are left and the pool is empty, mark simulation as complete
        if (totalTickets == 0 && ticketPool.isEmpty()) {
            simulationComplete = true;
            logger.info("All tickets released.");
            notifyAll();
            return;
        }

        // Calculate the number of tickets to add
        int ticketsToAdd = Math.min(count, Math.min(totalTickets, maxCapacity - ticketPool.size()));
        for (int i = 0; i < ticketsToAdd; i++) {
            ticketPool.add(1);
        }
        totalTickets -= ticketsToAdd;//Decrease from the total count

        logger.info("Vendor released " + ticketsToAdd + " tickets. Tickets in pool: "
                + ticketPool.size() + ", Tickets remaining: " + totalTickets);

        notifyAll();
    }

    /**
     * Allows customers to purchase tickets from the pool.
     * If no tickets are available, customers wait until tickets are added.
     *
     * @param count The number of tickets the customer wants to purchase.
     */
    public synchronized void purchaseTickets(int count) {
        // Wait if no tickets are available and the simulation is not complete
        while (ticketPool.isEmpty() && !simulationComplete) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        // Exit if simulation is complete and no tickets are left to purchase
        if (simulationComplete && ticketPool.isEmpty()) {
            return;
        }

        // Calculate the number of tickets the customer can buy
        int ticketsToBuy = Math.min(count, ticketPool.size());
        for (int i = 0; i < ticketsToBuy; i++) {
            ticketPool.remove(0);
        }

        logger.info("Customer purchased " + ticketsToBuy + " tickets. Tickets left in pool: "
                + ticketPool.size());

        notifyAll();
    }
}