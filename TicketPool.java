import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.ReentrantLock;
class TicketPool {
    private int totalTickets;
    private final ReentrantLock lock = new ReentrantLock();

    public TicketPool(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public int getAvailableTickets() {
        lock.lock();
        try {
            return totalTickets;
        } finally {
            lock.unlock();
        }
    }

    public void setTotalTickets(int totalTickets) {
        lock.lock();
        try {
            this.totalTickets = totalTickets;
        } finally {
            lock.unlock();
        }
    }

    public boolean purchaseTicket() {
        lock.lock();
        try {
            if (totalTickets > 0) {
                totalTickets--;
                System.out.println("Ticket purchased successfully. Remaining tickets: " + totalTickets);
                return true;
            } else {
                System.out.println("No tickets available.");
                return false;
            }
        } finally {
            lock.unlock();
        }
    }

    public void releaseTickets(int numberOfTickets) {
        lock.lock();
        try {
            totalTickets += numberOfTickets;
            System.out.println(numberOfTickets + " tickets released. Total tickets now: " + totalTickets);
        } finally {
            lock.unlock();
        }
    }
}
