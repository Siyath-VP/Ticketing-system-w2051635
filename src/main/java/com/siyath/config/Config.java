package com.siyath.config;

import com.google.gson.annotations.Expose;

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

    private static final String DEFAULT_CONFIG_FILE = "config/system_config.json";
    private static final String CONFIG_FILE = System.getProperty("com.siyath.config.file.path", DEFAULT_CONFIG_FILE);

    public Config(int totalTickets, int ticketReleaseRate, int customerRetrievalRate,
                  int maxTicketCapacity, int numVendors, int numCustomers) {
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
        this.numVendors = numVendors;
        this.numCustomers = numCustomers;
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public int getNumVendors() {
        return numVendors;
    }

    public void setNumVendors(int numVendors) {
        this.numVendors = numVendors;
    }

    public int getNumCustomers() {
        return numCustomers;
    }

    public void setNumCustomers(int numCustomers) {
        this.numCustomers = numCustomers;
    }

}