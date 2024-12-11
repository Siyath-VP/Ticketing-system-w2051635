import axios from "axios";

const API_BASE_URL = "http://localhost:8080/api";

export const loadConfiguration = async () => {
  const response = await axios.get(`${API_BASE_URL}/load-configuration`);
  return response.data;
};

export const saveConfiguration = async (config) => {
  const response = await axios.post(`${API_BASE_URL}/save-configuration`, config);
  return response.data;
};

export const startSimulation = async (config) => {
  const response = await axios.post(`${API_BASE_URL}/start-simulation`, config);
  return response.data;
};

export const stopSimulation = async () => {
  const response = await axios.post(`${API_BASE_URL}/stop-simulation`);
  return response.data;
};

export const resetSimulation = async () => {
    const response = await axios.post(`${API_BASE_URL}/reset-simulation`);
    return response.data;
  };
  

export const getLogs = async () => {
  const response = await axios.get(`${API_BASE_URL}/logs`);
  return response.data;
};
