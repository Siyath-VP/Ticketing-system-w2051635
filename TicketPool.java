import java.util.concurrent.locks.ReentrantLock;

class TicketPool {
    private int totalTickets;
    private final int maxCapacity;
    private final ReentrantLock lock = new ReentrantLock();

    public TicketPool(int initialTickets, int maxCapacity) {
        this.totalTickets = initialTickets;
        this.maxCapacity = maxCapacity;
    }

    public int getAvailableTickets() {
        lock.lock();
        try {
            return totalTickets;
        } finally {
            lock.unlock();
        }
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void releaseTickets(int numberOfTickets) {
        lock.lock();
        try {
            if (totalTickets + numberOfTickets > maxCapacity) {
                int ticketsToAdd = maxCapacity - totalTickets;
                if (ticketsToAdd > 0) {
                    totalTickets += ticketsToAdd;
                    System.out.println(ticketsToAdd + " tickets released. Total tickets now: " + totalTickets);
                } else {
                    System.out.println("Ticket pool is at maximum capacity. No tickets released.");
                }
            } else {
                totalTickets += numberOfTickets;
                System.out.println(numberOfTickets + " tickets released. Total tickets now: " + totalTickets);
            }
        } finally {
            lock.unlock();
        }
    }

    public void purchaseTickets(int numberOfTickets) {
        lock.lock();
        try {
            if (totalTickets >= numberOfTickets) {
                totalTickets -= numberOfTickets;
                System.out.println(numberOfTickets + " tickets purchased successfully. Remaining tickets: " + totalTickets);
            } else if (totalTickets > 0) {
                System.out.println("Only " + totalTickets + " tickets available. Purchasing all remaining tickets.");
                totalTickets = 0;
            } else {
                System.out.println("No tickets available for purchase.");
            }
        } finally {
            lock.unlock();
        }
    }
}
