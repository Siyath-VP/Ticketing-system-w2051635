class Vendor implements Runnable {
    private final TicketPool ticketPool;

    public Vendor(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                int ticketsToRelease = (int) (Math.random() * 10) + 1; // Release 1-10 tickets
                ticketPool.releaseTickets(ticketsToRelease);
                Thread.sleep(2000); // Wait 2 seconds before next release
            } catch (InterruptedException e) {
                System.out.println("Vendor thread interrupted.");
                Thread.currentThread().interrupt();
            }
        }
    }
}