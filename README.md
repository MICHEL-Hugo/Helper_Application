# Volunteer Assistance Platform - Microservices Architecture

This project is a platform designed to connect people in need with volunteers who are ready to assist them. The application is built using different service-oriented architectures, focusing on **SOAP**, **REST**, and **Microservices**. This README provides an overview of the project structure, services, and their configuration.

## Table of Contents

1. [Project Context](#project-context)
2. [Project Overview](#project-overview)
3. [Technologies Used](#technologies-used)
4. [Project Structure](#project-structure)
    - [RESTful Service - `demandManagementREST`](#restful-service---demandmanagementrest)
    - [Microservices](#microservices)
    - [Discovery and Configuration Services](#discovery-and-configuration-services)
    - [Service Configuration Files](#service-configuration-files)
    - [Database Configuration](#database-configuration)
5. [Service Workflow](#service-workflow)
6. [Configuration](#configuration)
7. [Getting Started](#getting-started)

---

## Project Context

This project was developed in an academic context by two 5th-year students from **INSA Toulouse** as part of a school assignment. The main objective was to explore and compare different architectural styles (SOAP, REST, and Microservices) by implementing a real-world application.

---

## Project Overview

The main objective of this project is to explore and demonstrate the differences and advantages of **SOAP**, **REST**, and **Microservices** architectures. The application allows users to:

- Post requests for help.
- Accept requests posted by other users.

The microservices architecture was implemented to offer modularity, scalability, and flexibility. It leverages RESTful APIs for communication between services.

---

## Technologies Used
- **Microservices** architecture
- **Spring Boot** for service development
- **REST APIs** for communication
- **Tomcat** for server configuration
- **MySQL** for database management

---

## Project Structure

The project is organized into several folders, each containing a specific microservice and its associated configurations.

### RESTful Services - Step in the Project (Not Necessary in Final Architecture)

As part of the development of this project, RESTful services were implemented to handle various aspects of demand management. However, it is important to note that these services were only a step in the process and are **not required in the final microservices architecture**. 

**Demand Management**:
  - `POST /demandes` - This endpoint allows users to create a new request for assistance.
  - `GET /demandes` - Retrieves a list of all requests available in the system.
  - `GET /demandes/en-attente`- Retrieves all requests that are in the "waiting" status.
  - `PUT /demandes/{id}/resolver`- Assign a volunteer to a specific request by updating the request with the volunteer's ID.
  - `PUT /demandes/{id}/terminer`- Marks a specific request as "completed" once the volunteer has finished the task.
  - `DELETE /demandes/{id}`- Deletes a specific request from the system.

### Microservices

1. **catalogDemandMS**  
   Manages the demands (requests for help). This service handles the creation, listing, and deletion of requests.  
   API Endpoints:
   - `POST /api/demand` - Create a new request.
   - `GET /api/demand` - List all requests.
   - `GET /api/demand/{id}` - Get details of a specific request.
   - `PUT /api/demand/{id}` - Update a request.
   - `DELETE /api/demand/{id}` - Delete a request.

2. **statusDemandMS**  
   Manages the statuses of requests (e.g., "waiting", "taken", "completed").  
   API Endpoints:
   - `POST /api/status/{id}` - Create a new waiting request.
   - `PUT /api/status` - Update the status of a request.
   - `GET /api/status/{id}` - Get all the request.

4. **userManagementMS**  
   Manages the users (volunteers and requesters).  
   API Endpoints:
   - `POST /api/user` - Add a new user.
   - `GET /api/user` - List all users.
   - `GET /api/user/{id}` - Get user details by ID.
   - `PUT /api/user/{id}` - Update user information.
   - `DELETE /api/user/{id}` - Delete a user.

5. **whoIsTheResolverMS**  
   Manages volunteer assignments.  
   API Endpoints:
   - `GET /api/resolvers/{id}` - list resolver's demands
   - `POST /api/resolvers/{id}` - Assign a volunteer to a request.

### Discovery and Configuration Services

- **Discovery Service**  
   Facilitates the discovery of available services within the microservices architecture.

- **Config Service**  
   Centralizes the configuration for all services in the system.

### Service Configuration Files

Each microservice has associated `.properties` files for configuration. These files contain necessary service settings, including database connections and other environmental configurations.

### Database Configuration

The database configuration is located in the **BDD** file, where the database connection details and schemas are specified for each service.

---

## Service Workflow

The application workflow involves several steps as users interact with the system:

1. **Creating a Request**:  
   - Users first send a `POST` request to the **statusDemandMS** service to set the status of a new request as "waiting."
   - A `POST` request is then sent to the **catalogDemandMS** service to create the request with relevant attributes.

2. **Viewing Pending Requests**:  
   - Users can view all requests that are in the "waiting" status by sending a `GET` request to **statusDemandMS**, which fetches the corresponding requests from **catalogDemandMS**.

3. **Taking Ownership of a Request**:  
   - When a volunteer wishes to take a request, they send a `PUT` request to **whoIsTheResolverMS**, associating their ID with the request ID.
   - A `PUT` request is sent to **statusDemandMS** to update the status of the request to "taken."

4. **Completing a Request**:  
   - After completing the task, the volunteer can send a `PUT` request to **statusDemandMS** to change the status of the request to "completed."

---

## Configuration

1. **Service Configuration Files**:  
   Each service has a `.properties` file that defines essential configuration settings like database connections, port numbers, and other environment-specific parameters.

2. **Database Configuration**:  
   The database settings are specified in the **BDD** file.

3. **Tomcat Configuration**:  
   The configuration for the Tomcat server is available in the **Servers** folder, specifically for the `demandManagementREST` service.

---

## Getting Started

Follow the steps below to set up and run the **Volunteer Assistance Platform** application.

### Prerequisites

Make sure you have the following installed on your system:

- **Java 11** or later
- **Maven** (for building and running the services)
- **MySQL** or any database that fits the configuration specified in the **BDD** file
- **Postman** (or any other API testing tool) for making HTTP requests

### Steps to Run the Application

1. **Clone the Repository**
   First, clone the repository to your local machine:
   ```bash
   git clone https://github.com/your-username/volunteer-assistance-platform.git
   cd volunteer-assistance-platform

2. Set Up the Database

   *Open the **BDD** file and configure the database connection (e.g., MySQL).
   *Create the necessary tables and schemas based on the instructions in the **BDD** file.

## Configure the Services

Each service has a corresponding configuration file (`.properties`) located in its folder. Adjust the configuration for each service to your environment, including:

- Database connections
- Service ports
- Other relevant settings

## Start the Services

The services need to be started in a specific order:

### Start the Discovery Service

The **Discovery Service** must be started first to allow the other services to discover each other.

```bash
mvn spring-boot:run -f discovery-service/pom.xml
```

### Start the Config Service

After the **Discovery Service**, start the **Config Service**, which handles the centralized configuration for all services.

```bash
mvn spring-boot:run -f config-service/pom.xml
```

### Start the Microservices

Once the **Discovery** and **Config Services** are up, start each of the following microservices:

- **catalogDemandMS** (Handles requests for assistance)
  ```bash
  mvn spring-boot:run -f catalogDemandMS/pom.xml
  ```

- **statusDemandMS** (Handles the statuses of requests)
  ```bash
  mvn spring-boot:run -f statusDemandMS/pom.xml
  ```

- **userManagementMS** (Manages users)
  ```bash
  mvn spring-boot:run -f userManagementMS/pom.xml
  ```

- **whoIsTheResolverMS** (Handles volunteer assignments)
  ```bash
  mvn spring-boot:run -f whoIsTheResolverMS/pom.xml
  ```

## Accessing the Services

After all services are up and running, you can test the application using Postman or any other API client. Below are some key endpoints you can use to interact with the platform:

- **Create a Request**:
  - `POST /api/status`  
    Creates a new request for assistance.

- **Get Pending Requests**:
  - `GET /api/status`  
    Retrieves all requests.

- **Assign a Volunteer to a Request**:
  - `PUT /api/resolvers`  
    Assign a volunteer to a specific request.

- **Update Request Status**:
  - `PUT /api/status`  
    Updates the status of a request (e.g., from "waiting" to "taken" or "completed").

- **List All Users**:
  - `GET /api/user`  
    Retrieves a list of all users (volunteers and requesters).

## Testing the Workflow

To verify the system is working correctly, you can perform the following actions using Postman:

1. Create a new request for assistance.
2. Retrieve the list of pending requests.
3. Assign a volunteer to one of the requests.
4. Mark the request as "completed" after the volunteer finishes their task.

## Notes

- Make sure to start the **Discovery** and **Config Services** before starting the other microservices.
- Ensure that the **BDD** file is correctly configured to match your database settings.

