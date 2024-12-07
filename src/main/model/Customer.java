package main.model;

import main.util.CustomLogger;

public class Customer implements Runnable {
    private final TicketPool ticketPool;
    private final int purchaseRate;

    public Customer(TicketPool ticketPool, int purchaseRate) {
        this.ticketPool = ticketPool;
        this.purchaseRate = purchaseRate;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            ticketPool.purchaseTickets(purchaseRate);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        CustomLogger.infoMessage("Customer thread stopped.");
    }
}
