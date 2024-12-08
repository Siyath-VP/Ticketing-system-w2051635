package com.siyath.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Vendor implements Runnable {

    private static final Logger logger = LogManager.getLogger(Vendor.class);
    private final TicketPool ticketPool;
    private final int releaseRate;

    public Vendor(TicketPool ticketPool, int releaseRate) {
        this.ticketPool = ticketPool;
        this.releaseRate = releaseRate;
    }

    @Override
    public void run() {
        logger.info("Vendor thread started.");
        while (!Thread.currentThread().isInterrupted()) {
            ticketPool.addTickets(releaseRate);
            try {
                Thread.sleep(2000); // Release tickets every 2 seconds
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error("Vendor thread interrupted.{}", e.getMessage());
            }
        }
        logger.info("Vendor thread stopped.");
    }
}
