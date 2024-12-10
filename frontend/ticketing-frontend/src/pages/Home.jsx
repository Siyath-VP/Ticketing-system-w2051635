import React from 'react';
import { Container, Typography } from '@mui/material';

const Home = () => (
    <div className="home-container page-content">
      <Typography variant="h3" gutterBottom>
        Welcome to the Ticket Simulation App
      </Typography>
      <Typography>
        Use the navigation bar to configure the simulation, start it, and view results.
      </Typography>
    </div>
  );
  
  export default Home;
  