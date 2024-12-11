import React, { useRef, useEffect } from 'react';

const SimulationLog = ({ logs }) => {
  const logRef = useRef();

  useEffect(() => {
    if (logRef.current) {
      // Automatically scroll to the bottom when new logs are added
      logRef.current.scrollTop = logRef.current.scrollHeight;
    }
  }, [logs]);

  return (
    <div style={styles.logContainer} ref={logRef}>
      {logs.map((log, index) => (
        <div key={index} style={styles.logLine}>
          {log}
        </div>
      ))}
    </div>
  );
};

const styles = {
  logContainer: {
    backgroundColor: '#000000',
    color: '#ffffff',
    width: '100%',
    height: '30vh',
    overflowY: 'scroll',
    marginTop: '20px',
    padding: '10px',
    fontFamily: 'monospace',
    borderRadius: '4px',
    border: '1px solid #444',
  },
  logLine: {
    whiteSpace: 'pre-wrap',
    wordWrap: 'break-word',
    margin: '2px 0',
  },
};

export default SimulationLog;