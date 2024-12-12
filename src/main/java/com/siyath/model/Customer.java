package com.siyath.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Represents customers in the ticket simulation system.
 * Each customer is responsible for purchasing tickets from the ticket pool at a specified rate.
 * This class implements the Runnable interface, enabling it to run as a separate thread.
 */
public class Customer implements Runnable {

    private static final Logger logger = LogManager.getLogger(Customer.class);
    private final TicketPool ticketPool;
    private final int purchaseRate;

    /**
     * Constructs a Customer object.
     *
     * @param ticketPool  The ticket pool from which tickets will be purchased.
     * @param purchaseRate The rate at which tickets are purchased by this customer.
     */
    public Customer(TicketPool ticketPool, int purchaseRate) {
        this.ticketPool = ticketPool;
        this.purchaseRate = purchaseRate;
    }

    /**
     * The run method contains the main logic for the Customer thread.
     * The customer continuously tries to purchase tickets from the ticket pool until the thread is interrupted.
     */
    @Override
    public void run() {
        logger.info("Customer thread started.");
        while (!Thread.currentThread().isInterrupted()) {
            ticketPool.purchaseTickets(purchaseRate);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error("Customer thread interrupted: {}", e.getMessage());
            }
        }
        logger.info("Customer thread stopped.");
    }
}