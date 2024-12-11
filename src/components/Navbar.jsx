import React from 'react';

const Navbar = () => {
  return (
    <nav style={styles.navbar}>
      <div style={styles.navContent}>
        <span style={styles.title}>Ticket Simulation App</span>
        
      </div>
    </nav>
  );
};

const styles = {
  navbar: {
    width: '100%',
    position: 'sticky',
    top: 0,
    backgroundColor: '#2196f3',
    padding: '10px 20px',
    zIndex: 999,
  },
  navContent: {
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
  },
  title: {
    color: '#ffffff',
    fontSize: '1.5rem',
    fontWeight: 'bold',
  },
  configButton: {
    backgroundColor: '#2196f3',
    color: '#ffffff',
    border: 'none',
    cursor: 'pointer',
    fontSize: '1rem',
    padding: '8px 16px',
  },
};

export default Navbar;