import React, { useState, useEffect, useRef } from 'react';
import { TextField, Button, Container, Typography } from '@mui/material';

const ConfigForm = () => {
  const [config, setConfig] = useState({
    totalTickets: '',
    ticketReleaseRate: '',
    customerRetrievalRate: '',
    maxTicketPoolCapacity: '',
    numVendors: '',
    numCustomers: '',
  });
  const [logs, setLogs] = useState([]); // State to hold log messages
  const terminalRef = useRef(null); // Ref to manage scrolling in the terminal

  const handleChange = (e) => {
    setConfig({ ...config, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      // Send configuration to the backend
      await fetch('http://localhost:8080/api/config', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(config),
      });

      alert('Configuration saved and simulation started!');
    } catch (error) {
      alert('Failed to start simulation.');
    }
  };

  useEffect(() => {
    // WebSocket connection
    const socket = new WebSocket('ws://localhost:8080/logs');

    // Handle incoming messages
    socket.onmessage = (event) => {
      setLogs((prevLogs) => [...prevLogs, event.data]); // Append new log to the list
    };

    // Cleanup WebSocket on component unmount
    return () => {
      socket.close();
    };
  }, []);

  useEffect(() => {
    // Auto-scroll to the bottom of the terminal when new logs are added
    if (terminalRef.current) {
      terminalRef.current.scrollTop = terminalRef.current.scrollHeight;
    }
  }, [logs]);

  return (
    <Container>
      <Typography variant="h4" gutterBottom align="center">
        Configure Simulation
      </Typography>
      <form onSubmit={handleSubmit}>
        <TextField
          label="Total Tickets"
          name="totalTickets"
          value={config.totalTickets}
          onChange={handleChange}
          fullWidth
          margin="normal"
          required
        />
        <TextField
          label="Ticket Release Rate"
          name="ticketReleaseRate"
          value={config.ticketReleaseRate}
          onChange={handleChange}
          fullWidth
          margin="normal"
          required
        />
        <TextField
          label="Customer Retrieval Rate"
          name="customerRetrievalRate"
          value={config.customerRetrievalRate}
          onChange={handleChange}
          fullWidth
          margin="normal"
          required
        />
        <TextField
          label="Max Ticket Pool Capacity"
          name="maxTicketPoolCapacity"
          value={config.maxTicketPoolCapacity}
          onChange={handleChange}
          fullWidth
          margin="normal"
          required
        />
        <TextField
          label="Number of Vendors"
          name="numVendors"
          value={config.numVendors}
          onChange={handleChange}
          fullWidth
          margin="normal"
          required
        />
        <TextField
          label="Number of Customers"
          name="numCustomers"
          value={config.numCustomers}
          onChange={handleChange}
          fullWidth
          margin="normal"
          required
        />
        <Button type="submit" variant="contained" color="primary" sx={{ mt: 2 }}>
          Save Configuration
        </Button>
      </form>

      {/* Terminal-like area for logs */}
      <div
        ref={terminalRef}
        style={{
          backgroundColor: 'black',
          color: 'lime',
          fontFamily: 'monospace',
          padding: '10px',
          marginTop: '20px',
          height: '300px',
          overflowY: 'scroll',
          borderRadius: '5px',
          border: '1px solid #333',
        }}
      >
        {logs.map((log, index) => (
          <div key={index}>{log}</div>
        ))}
      </div>
    </Container>
  );
};

export default ConfigForm;
