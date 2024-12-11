import { useState } from 'react';
import { saveConfiguration, startSimulation, resetSimulation, stopSimulation, loadConfiguration } from '../services/api';

const ConfigurationForm = ({ onRun, onStop, onReset }) => {
  const [formData, setFormData] = useState({
    totalTickets: '',
    ticketReleaseRate: '',
    customerRetrievalRate: '',
    maxTicketCapacity: '',
    numVendors: '',
    numCustomers: '',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSave = async () => {
    try {
      const response = await saveConfiguration(formData);
      console.log('Configuration saved:', response);
    } catch (error) {
      console.error('Error saving configuration:', error);
    }
  };

  const handleRun = async () => {
    try {
      const response = await startSimulation(formData);
      console.log('Simulation started:', response);
    } catch (error) {
      console.error('Error starting simulation:', error);
    }
  };

  const handleStop = async () => {
    try {
      const response = await stopSimulation(formData);
      console.log('Simulation started:', response);
    } catch (error) {
      console.error('Error starting simulation:', error);
    }
  };

  const handleReset = async () => {
    try {
      const response = await resetSimulation(formData);
      console.log('Simulation started:', response);
    } catch (error) {
      console.error('Error starting simulation:', error);
    }
  };

  const handleLoad = async () => {
    try {
      const response = await loadConfiguration(formData);
      console.log('Configuration loaded:', response);
    } catch (error) {
      console.error('Error loading configuration:', error);
    }
  };

  return (
    <div style={styles.container}>
      <h2 style={styles.header}>Configure Simulation</h2>
      <div style={styles.formFields}>
        {Object.keys(formData).map((key) => (
          <input
            key={key}
            style={styles.input}
            name={key}
            placeholder={`Enter ${key.replace(/([A-Z])/g, ' $1')}`}
            value={formData[key]}
            onChange={handleChange}
          />
        ))}
      </div>
      <div style={styles.buttonRow}>
        <button style={styles.saveBtn} onClick={handleSave}>Save Configuration</button>
        <button style={styles.loadBtn} onClick={handleLoad}>Load Configuration</button>
        <button style={styles.runBtn} onClick={handleRun}>Run Simulation</button>
        <button style={styles.stopBtn} onClick={handleStop}>Stop Simulation</button>
        <button style={styles.resetBtn} onClick={handleReset}>Reset Simulation</button>
      </div>
    </div>
  );
};

const styles = {
  container: {
    backgroundColor: '#ffffff',
    padding: '20px',
    borderRadius: '8px',
    boxShadow: '0 4px 6px rgba(0,0,0,0.1)',
    margin: '20px auto',
    width: '100%',
    maxWidth: '600px',
  },
  header: {
    fontSize: '1.5rem',
    fontWeight: 'bold',
    marginBottom: '20px',
    textAlign: 'center',
  },
  formFields: {
    display: 'grid',
    gridTemplateColumns: '1fr',
    gap: '10px',
    marginBottom: '20px',
  },
  input: {
    padding: '10px',
    fontSize: '1rem',
    border: '1px solid #ccc',
    borderRadius: '4px',
    width: '100%',
  },
  buttonRow: {
    display: 'flex',
    flexWrap: 'wrap',
    gap: '10px',
    justifyContent: 'center',
  },
  saveBtn: {
    backgroundColor: '#2196f3',
    color: '#ffffff',
    padding: '10px 20px',
    border: 'none',
    cursor: 'pointer',
  },
  runBtn: {
    backgroundColor: '#4caf50',
    color: '#ffffff',
    padding: '10px 20px',
    border: 'none',
    cursor: 'pointer',
  },
  stopBtn: {
    backgroundColor: '#f44336',
    color: '#ffffff',
    padding: '10px 20px',
    border: 'none',
    cursor: 'pointer',
  },
  resetBtn: {
    backgroundColor: '#9c27b0',
    color: '#ffffff',
    padding: '10px 20px',
    border: 'none',
    cursor: 'pointer',
  },
  loadBtn: {
    backgroundColor: '#7a790c',
    color: '#ffffff',
    padding: '10px 20px',
    border: 'none',
    cursor: 'pointer',
  },
};

export default ConfigurationForm;