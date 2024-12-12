package com.siyath.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Represents a vendor in the simulation who is responsible for releasing tickets into the ticket pool at a specified rate.
 * This class implements the Runnable interface to allow multi-threading.
 */
public class Vendor implements Runnable {

    private static final Logger logger = LogManager.getLogger(Vendor.class);
    private final TicketPool ticketPool;
    private final int releaseRate;

    /**
     * Constructs a Vendor object with the given ticket pool and release rate.
     *
     * @param ticketPool  The shared ticket pool where tickets will be added.
     * @param releaseRate The number of tickets released by the vendor in each cycle.
     */
    public Vendor(TicketPool ticketPool, int releaseRate) {
        this.ticketPool = ticketPool;
        this.releaseRate = releaseRate;
    }

    /**
     * The run method defines the vendor's behavior when executed in a thread.
     * It continuously releases tickets into the ticket pool at the specified rate,
     * until the thread is interrupted.
     */
    @Override
    public void run() {
        logger.info("Vendor thread started.");
        while (!Thread.currentThread().isInterrupted()) {
            ticketPool.addTickets(releaseRate);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error("Vendor thread interrupted: {}", e.getMessage());
            }
        }
        logger.info("Vendor thread stopped.");
    }
}