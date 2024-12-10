

# **Real-Time Ticketing System**

## **Overview**

The Real-Time Ticketing System is a multi-threaded, producer-consumer pattern-based application developed as part of the coursework specification. The project simulates a dynamic ticketing environment where vendors (producers) release tickets, and customers (consumers) purchase them concurrently while ensuring synchronization, concurrency, and data integrity.

The project is divided into three main components:

1. **CLI**: Core implementation using Java.
2. **Backend**: Spring Boot-based implementation for facilitating integration with the frontend.
3. **Frontend**: React-based web application for visualizing and interacting with the system.

**Features**

**Core Functionalities**

- **Concurrency**: Vendors release tickets concurrently while customers purchase them, with synchronized access to the shared ticket pool.
- **Producer-Consumer Pattern**: Implements advanced producer-consumer logic with vendors as producers and customers as consumers.
- **Thread Safety**: Ensures safe concurrent access to the ticket pool using thread-safe data structures.
- **Logging**: Logs all system activities (ticket releases, purchases, etc.) for debugging and auditing.
- **Real-Time Output**: For the frontend, streams backend logs in real-time to a terminal-like UI component.

**Additional Features**

- **Configuration Management**: Users can configure system parameters such as total tickets, release/purchase rates, etc., and save/load these configurations using JSON.
- **UI Integration**: Provides a React-based UI for interacting with the backend and visualizing system activities in real time.
- **Multi-threading Mastery**: Ensures synchronized operations, handles deadlocks, and manages race conditions effectively.

**Project Structure**

**Branches**

1. **Master**: Contains the Spring Boot backend implementation.
2. **CLI**: Contains the core Java implementation of the ticketing system (CLI-based).
3. **Frontend**: (To be implemented) Will contain the React-based frontend for the application.

**Folder Structure**

- **CLI**: Core Java implementation, including:
    - Multi-threading logic
    - Producer-consumer classes (Vendor, Customer)
    - Logging system
- **Backend**:
    - Spring Boot application
    - RESTful APIs for frontend integration
- **Frontend**:
    - React-based interface
    - Components for configuration and real-time output display

**Getting Started**

**Prerequisites**

- **Java**: JDK 17 or later
- **Maven**: Build and dependency management
- **Node.js**: For the React frontend
- **Spring Boot**: For the backend server

**Installation**

Here’s the content formatted entirely for direct use in an `.md` file:


### **Backend**

1. Clone the `master` branch:

   ```bash
   git clone -b master https://github.com/username/Ticketing-system.git
   ```

2. Navigate to the project directory:

   ```bash
   cd Ticketing-system
   ```

3. Build the project:

   ```bash
   mvn clean install
   ```

4. Run the backend:

   ```bash
   mvn spring-boot:run
   ```


### **Frontend**

1. Clone the frontend branch (once implemented):
```bash
git clone -b frontend https://github.com/Siyath-VP/Ticketing-system.git
```
2. Navigate to the frontend directory:
```bash
cd Ticketing-system-frontend
```
3. Install dependencies:
```bash
npm install
```
4. Start the development server:
```bash
npm run dev
```

**Usage**

**CLI (Core Java)**

1. Switch to the CLI branch:
2. git checkout CLI
3. Run the CLI-based application:
4. java -jar TicketingSystem.jar

**Backend and Frontend**

1. Start the backend server (instructions above).
2. Start the frontend development server (instructions above).
3. Open the application in your browser:
4. <http://localhost:5173>

**Technologies Used**

**Core Implementation**

- Java (OOP, Multi-threading)
- JSON for configuration persistence
- Logging using log4j

**Backend**

- Spring Boot
- RESTful API

**Frontend**

- React.js
- Real-time data updates using WebSocket integration

**Coursework Specifications Fulfilled**

1. **Concurrency and Multi-threading**: Handles multiple vendors and customers accessing a shared ticket pool concurrently.
2. **Producer-Consumer Pattern**: Implements producer-consumer logic for ticket releases and purchases.
3. **UI Integration**: Provides a frontend interface for user interaction.
4. **Configuration Management**: Allows saving/loading system configurations via JSON files.
5. **Logging**: Logs system activities for auditing and debugging purposes.
6. **Real-Time Updates**: Streams backend logs in real-time to the frontend.

**Screenshots**

**Frontend UI**

- Configuration Form
- Terminal-like real-time logs

**Backend CLI**

- Multi-threaded ticketing operations

**Future Enhancements**

1. **Dynamic Vendor/Customer Management**: Add/remove vendors/customers at runtime.
2. **Advanced Analytics**: Real-time analytics dashboard.
3. **Priority Customers**: Implement VIP customers with prioritized access.

**Author**

- **Name**: Siyath Padigama
