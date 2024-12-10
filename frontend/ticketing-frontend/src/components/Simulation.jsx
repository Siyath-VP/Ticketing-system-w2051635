import React from 'react';
import { Button, Container, Typography } from '@mui/material';
import { startSimulation, fetchSimulationResults } from '../services/api';

const Simulation = () => {
    const handleStart = async () => {
        try {
            await startSimulation();
            alert('Simulation started successfully!');
        } catch (error) {
            alert('Failed to start simulation.');
        }
    };

    const handleViewResults = async () => {
        try {
            const results = await fetchSimulationResults();
            alert(JSON.stringify(results.data, null, 2)); // Show results in JSON format
        } catch (error) {
            alert('Failed to fetch simulation results.');
        }
    };

    return (
        <Container>
            <Typography variant="h4" gutterBottom>
                Simulation
            </Typography>
            <Button variant="contained" color="primary" onClick={handleStart} sx={{ mt: 2 }}>
                Start Simulation
            </Button>
            <Button variant="outlined" color="secondary" onClick={handleViewResults} sx={{ mt: 2, ml: 2 }}>
                View Results
            </Button>
        </Container>
    );
};

export default Simulation;