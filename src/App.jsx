import React, { useState, useEffect } from 'react';
import Navbar from './components/Navbar';
import ConfigurationForm from './components/ConfigurationForm';
import SimulationLog from './components/SimulationLog';
import { stopSimulation, resetSimulation, getLogs } from './services/api';

function App() {
  const [logs, setLogs] = useState([]);

  useEffect(() => {
    const fetchLogs = async () => {
      try {
        const data = await getLogs();
        setLogs(data);
      } catch (error) {
        console.error('Error fetching logs:', error);
      }
    };

    const interval = setInterval(fetchLogs, 2000); // Fetch logs every 2 seconds
    return () => clearInterval(interval);
  }, []);

  const handleSaveConfiguration = (formData) => {
    console.log('Saving configuration:', formData);
  };

  const handleRunSimulation = () => {
    console.log('Running simulation...');
  };

  const handleStopSimulation = () => {
    console.log('Stopping simulation...');
  };

  const handleResetSimulation = () => {
    console.log('Resetting simulation...');
    setLogs([]); // Clear logs on reset
  };

  return (
    <div style={styles.container}>
      <Navbar />
      <div style={styles.content}>
        <ConfigurationForm
          onSave={handleSaveConfiguration}
          onRun={handleRunSimulation}
          onStop={handleStopSimulation}
          onReset={handleResetSimulation}
        />
        <SimulationLog logs={logs} />
      </div>
    </div>
  );
}

const styles = {
  container: {
    width: '100%',
    height: '100%',
    margin: 0,
    padding: 0,
    boxSizing: 'border-box',
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    justifyContent: 'flex-start',
    backgroundColor: '#f5f5f5',
    overflowX: 'hidden',
  },
  content: {
    width: '100%',
    maxWidth: '1200px',
    margin: '0 auto',
    padding: '20px',
    boxSizing: 'border-box',
  },
};

export default App;