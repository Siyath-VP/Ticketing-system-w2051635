class Customer implements Runnable {
    private final TicketPool ticketPool;
    private final int customerRetrievalRate;

    public Customer(TicketPool ticketPool, int customerRetrievalRate) {
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = customerRetrievalRate;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                ticketPool.purchaseTickets(customerRetrievalRate);
                Thread.sleep(1500); // purchase every 1.5 secs
            } catch (InterruptedException e) {
                System.out.println("Customer thread interrupted.");
                Thread.currentThread().interrupt();
            }
        }
    }
}
