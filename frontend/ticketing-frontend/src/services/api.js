import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api';

export const saveConfig = async (config) => {
  return await axios.post(`${API_BASE_URL}/config`, config);
};

export const startSimulation = async () => {
  return await axios.post(`${API_BASE_URL}/simulation/start`);
};

export const stopSimulation = async () => {
  return await axios.post(`${API_BASE_URL}/simulation/stop`);
};

export const resetSimulation = async () => {
  return await axios.post(`${API_BASE_URL}/simulation/reset`);
};
    