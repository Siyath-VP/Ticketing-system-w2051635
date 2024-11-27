class Vendor implements Runnable {
    private final TicketPool ticketPool;
    private final int ticketReleaseRate;

    public Vendor(TicketPool ticketPool, int ticketReleaseRate) {
        this.ticketPool = ticketPool;
        this.ticketReleaseRate = ticketReleaseRate;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                ticketPool.releaseTickets(ticketReleaseRate);
                Thread.sleep(2000); // Release every 2 secs
            } catch (InterruptedException e) {
                System.out.println("Vendor thread interrupted.");
                Thread.currentThread().interrupt();
            }
        }
    }
}
