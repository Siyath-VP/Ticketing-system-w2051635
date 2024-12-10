import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar';
import ConfigForm from './components/ConfigForm';

const App = () => {
  return (
    <Router>
      <Navbar />
      <Routes>
        <Route path="/" element={<ConfigForm />} /> {/* Set ConfigForm as the default page */}
        <Route path="*" element={<ConfigForm />} /> {/* Redirect unknown paths to ConfigForm */}
      </Routes>
    </Router>
  );
};

export default App;
