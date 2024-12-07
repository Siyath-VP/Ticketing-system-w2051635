package main.model;

import main.util.CustomLogger;

import java.util.Vector;

public class TicketPool {
    private int totalTickets;
    private final int maxCapacity;
    private final Vector<Integer> ticketPool;
    private boolean simulationComplete = false;

    public TicketPool(int totalTickets, int maxCapacity) {
        this.totalTickets = totalTickets;
        this.maxCapacity = maxCapacity;
        this.ticketPool = new Vector<>(maxCapacity); // Initialize vector with max capacity
    }

    public synchronized boolean isSimulationComplete() {
        return simulationComplete;
    }

    public void addTickets(int count) {
        synchronized (this) {
            if (totalTickets == 0) {
                simulationComplete = true;
                return;
            }

            int ticketsToAdd = Math.min(count, Math.min(totalTickets, maxCapacity - ticketPool.size()));
            for (int i = 0; i < ticketsToAdd; i++) {
                ticketPool.add(1);
            }
            totalTickets -= ticketsToAdd;

            CustomLogger.infoMessage(
                    ticketsToAdd + " tickets released. Tickets in pool: " + ticketPool.size() + ", Tickets remaining: " + totalTickets
            );

            notifyAll(); // Notify customers that tickets are available
        }
    }

    public void purchaseTickets(int count) {
        synchronized (this) {
            while (ticketPool.isEmpty() && !simulationComplete) {
                try {
                    wait(); // Wait for tickets to be released
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }

            if (simulationComplete && ticketPool.isEmpty()) {
                return; // No more tickets to be purchased
            }

            int ticketsToBuy = Math.min(count, ticketPool.size());
            for (int i = 0; i < ticketsToBuy; i++) {
                ticketPool.remove(0); // Remove tickets from the pool
            }

            CustomLogger.infoMessage(
                    ticketsToBuy + " tickets purchased. Tickets left in pool: " + ticketPool.size()
            );

            notifyAll(); // Notify vendors that space is available in the pool
        }
    }
}
