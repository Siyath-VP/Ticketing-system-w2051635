package model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.CustomLogger;

public class Customer implements Runnable {

    private static final Logger logger = LogManager.getLogger(Customer.class);
    private final TicketPool ticketPool;
    private final int purchaseRate;

    public Customer(TicketPool ticketPool, int purchaseRate) {
        this.ticketPool = ticketPool;
        this.purchaseRate = purchaseRate;
    }

    @Override
    public void run() {
        logger.info("Customer thread started.");
        while (!Thread.currentThread().isInterrupted()) {
            ticketPool.purchaseTickets(purchaseRate);
            try {
                Thread.sleep(1500); // Attempt to purchase tickets every 1.5 seconds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error("Customer thread interrupted.{}", e.getMessage());
            }
        }
        logger.info("Customer thread stopped.");
    }
}
