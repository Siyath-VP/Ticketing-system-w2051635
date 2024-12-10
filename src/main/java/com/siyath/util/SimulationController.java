package com.siyath.util;

import com.siyath.config.Config;
import com.siyath.model.Customer;
import com.siyath.model.TicketPool;
import com.siyath.model.Vendor;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicBoolean;

@RestController
@RequestMapping("/api")
public class SimulationController {
    private Config config;
    private TicketPool ticketPool;
    private Thread[] vendors;
    private Thread[] customers;
    private final AtomicBoolean isRunning = new AtomicBoolean(false);

    @PostMapping("/start-simulation")
    public synchronized String startSimulation(@RequestBody Config newConfig) {
        if (isRunning.get()) {
            return "Simulation is already running.";
        }
        this.config = newConfig;
        this.ticketPool = new TicketPool(config.getTotalTickets(), config.getMaxTicketCapacity());
        this.vendors = new Thread[config.getNumVendors()];
        this.customers = new Thread[config.getNumCustomers()];

        // Start Vendor Threads
        for (int i = 0; i < config.getNumVendors(); i++) {
            vendors[i] = new Thread(new Vendor(ticketPool, config.getTicketReleaseRate()));
            vendors[i].start();
        }

        // Start Customer Threads
        for (int i = 0; i < config.getNumCustomers(); i++) {
            customers[i] = new Thread(new Customer(ticketPool, config.getCustomerRetrievalRate()));
            customers[i].start();
        }

        isRunning.set(true);
        return "Simulation started successfully.";
    }

    @PostMapping("/stop-simulation")
    public synchronized String stopSimulation() {
        if (!isRunning.get()) {
            return "No simulation is currently running.";
        }

        // Interrupt Vendor Threads
        if (vendors != null) {
            for (Thread vendor : vendors) {
                if (vendor != null && vendor.isAlive()) {
                    vendor.interrupt();
                }
            }
        }

        // Interrupt Customer Threads
        if (customers != null) {
            for (Thread customer : customers) {
                if (customer != null && customer.isAlive()) {
                    customer.interrupt();
                }
            }
        }

        // Wait for all threads to finish
        if (vendors != null) {
            for (Thread vendor : vendors) {
                if (vendor != null) {
                    try {
                        vendor.join();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        if (customers != null) {
            for (Thread customer : customers) {
                if (customer != null) {
                    try {
                        customer.join();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }

        isRunning.set(false);
        return "Simulation stopped successfully.";
    }

    @PostMapping("/reset-simulation")
    public synchronized String resetSimulation() {
        // If running, stop first
        if (isRunning.get()) {
            stopSimulation();
        }

        this.config = null;
        this.ticketPool = null;
        this.vendors = null;
        this.customers = null;

        return "Simulation reset successfully.";
    }
}
