class Customer implements Runnable {
    private final TicketPool ticketPool;

    public Customer(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                boolean success = ticketPool.purchaseTicket();
                if (success) {
                    System.out.println("Customer successfully purchased a ticket.");
                } else {
                    System.out.println("Customer attempted to purchase a ticket but none were available.");
                }
                Thread.sleep(1500); // Wait 1.5 seconds before next attempt
            } catch (InterruptedException e) {
                System.out.println("Customer thread interrupted.");
                Thread.currentThread().interrupt();
            }
        }
    }
}