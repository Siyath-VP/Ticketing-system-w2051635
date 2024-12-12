package com.siyath.config;

import com.google.gson.annotations.Expose;

/**
 * Used to store the configuration parameters required for the ticket simulation system.
 * It includes the total number of tickets, release rates, and customer/vendor counts.
 * This class is also designed to support JSON serialization using the Gson library.
 */
public class Config {

    @Expose
    private int totalTickets;
    @Expose
    private int ticketReleaseRate;
    @Expose
    private int customerRetrievalRate;
    @Expose
    private int maxTicketCapacity;
    @Expose
    private int numVendors;
    @Expose
    private int numCustomers;

    // Path to the default configuration file
    private static final String DEFAULT_CONFIG_FILE = "config/system_config.json";

    /**
     * Constructor to initialize the configuration parameters.
     *
     * @param totalTickets          Total number of tickets available.
     * @param ticketReleaseRate     Rate at which vendors release tickets.
     * @param customerRetrievalRate Rate at which customers purchase tickets.
     * @param maxTicketCapacity     Maximum capacity of the ticket pool.
     * @param numVendors            Number of vendors in the simulation.
     * @param numCustomers          Number of customers in the simulation.
     */
    public Config(int totalTickets, int ticketReleaseRate, int customerRetrievalRate,
                  int maxTicketCapacity, int numVendors, int numCustomers) {
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
        this.numVendors = numVendors;
        this.numCustomers = numCustomers;
    }

    /**
     * Gets the total number of tickets available.
     *
     * @return Total tickets.
     */
    public int getTotalTickets() {
        return totalTickets;
    }

    /**
     * Sets the total number of tickets available.
     *
     * @param totalTickets Total tickets.
     */
    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    /**
     * Gets the rate at which tickets are released by vendors.
     *
     * @return Ticket release rate.
     */
    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    /**
     * Sets the rate at which tickets are released by vendors.
     *
     * @param ticketReleaseRate Ticket release rate.
     */
    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    /**
     * Gets the rate at which customers purchase tickets.
     *
     * @return Customer retrieval rate.
     */
    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    /**
     * Sets the rate at which customers purchase tickets.
     *
     * @param customerRetrievalRate Customer retrieval rate.
     */
    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    /**
     * Gets the maximum capacity of the ticket pool.
     *
     * @return Maximum ticket pool capacity.
     */
    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    /**
     * Sets the maximum capacity of the ticket pool.
     *
     * @param maxTicketCapacity Maximum ticket pool capacity.
     */
    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    /**
     * Gets the number of vendors in the simulation.
     *
     * @return Number of vendors.
     */
    public int getNumVendors() {
        return numVendors;
    }

    /**
     * Sets the number of vendors in the simulation.
     *
     * @param numVendors Number of vendors.
     */
    public void setNumVendors(int numVendors) {
        this.numVendors = numVendors;
    }

    /**
     * Gets the number of customers in the simulation.
     *
     * @return Number of customers.
     */
    public int getNumCustomers() {
        return numCustomers;
    }

    /**
     * Sets the number of customers in the simulation.
     *
     * @param numCustomers Number of customers.
     */
    public void setNumCustomers(int numCustomers) {
        this.numCustomers = numCustomers;
    }
}