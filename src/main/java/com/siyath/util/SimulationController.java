package com.siyath.util;

import com.siyath.config.Config;
import com.siyath.model.TicketPool;
import com.siyath.model.Customer;
import com.siyath.model.Vendor;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api")
public class SimulationController {

    private TicketPool ticketPool;
    private Thread[] vendors;
    private Thread[] customers;

    @PostMapping("/start-simulation")
    public String startSimulation(@RequestBody Config config) {
        ticketPool = new TicketPool(config.getTotalTickets(), config.getMaxTicketCapacity());

        vendors = new Thread[config.getNumVendors()];
        customers = new Thread[config.getNumCustomers()];

        for (int i = 0; i < config.getNumVendors(); i++) {
            vendors[i] = new Thread(new Vendor(ticketPool, config.getTicketReleaseRate()));
            vendors[i].start();
        }

        for (int i = 0; i < config.getNumCustomers(); i++) {
            customers[i] = new Thread(new Customer(ticketPool, config.getCustomerRetrievalRate()));
            customers[i].start();
        }

        return "Simulation started with the provided configuration.";
    }

    @PostMapping("/stop-simulation")
    public String stopSimulation() {
        if (vendors != null) {
            for (Thread vendor : vendors) {
                vendor.interrupt();
            }
        }
        if (customers != null) {
            for (Thread customer : customers) {
                customer.interrupt();
            }
        }
        return "Simulation stopped.";
    }

    @PostMapping("/reset-simulation")
    public String resetSimulation() {
        stopSimulation();
        ticketPool = null;
        return "Simulation reset.";
    }
}
