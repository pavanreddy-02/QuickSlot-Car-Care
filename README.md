# Car Service Scheduler API

This project is a Spring Boot application that provides a REST API for scheduling car service appointments with service operators.

## Features

- Book appointments with a specific service operator or any available operator.
- Reschedule existing appointments.
- Cancel appointments.
- View all booked appointments for a specific operator.
- View open (available) time slots for a specific operator, with consecutive slots merged.

## Prerequisites

- Java 17 or later
- Maven
- MySQL Server

## Setup and Running

1.  **Database Setup:**
    *   Ensure you have MySQL server running.
    *   Create a database named `car_service_scheduler`. You can use the following SQL command:
        ```sql
        CREATE DATABASE car_service_scheduler;
        ```
    *   The application uses JPA with `hibernate.ddl-auto=update`, so tables will be created/updated automatically on startup.
    *   Update the database username and password in `src/main/resources/application.properties` if they differ from the defaults (`root`/`P@1r3d2y@`).
        ```properties
        spring.datasource.username=your_mysql_username
        spring.datasource.password=your_mysql_password
        ```

2.  **Build the Application:**
    Open a terminal in the project root directory and run:
    ```bash
    ./mvnw clean package
    ```
    (or `mvnw.cmd clean package` on Windows)

3.  **Run the Application:**
    After a successful build, run the application using:
    ```bash
    java -jar target/carservicescheduler-0.0.1-SNAPSHOT.jar
    ```
    The application will start on port 8080 by default.

## API Endpoints

If no service operators exist (e.g., on first run with a new database), you will need to create them using the `POST /serviceoperators` endpoint. For example, you can create operators named `ServiceOperator0`, `ServiceOperator1`, and `ServiceOperator2`. You can then list them and find their IDs using `GET /serviceoperators`.

### Service Operators

-   `GET /serviceoperators`: Get all service operators.
-   `GET /serviceoperators/{id}`: Get a specific service operator by ID.
-   `POST /serviceoperators`: Create a new service operator. (Body: `ServiceOperator` JSON)
    ```json
    {
        "name": "ServiceOperatorNew"
    }
    ```
-   `PUT /serviceoperators/{id}`: Update a service operator. (Body: `ServiceOperator` JSON)
-   `DELETE /serviceoperators/{id}`: Delete a service operator.

### Scheduler

-   `POST /book`: Book an appointment for a specific operator.
    *   Request Body (`AppointmentRequestDTO`):
        ```json
        {
            "operatorId": 1,
            "startHour": 9
        }
        ```
-   `POST /book-any`: Book an appointment with any available operator for the given start hour.
    *   Request Body (`BookAnyRequestDTO`):
        ```json
        {
            "startHour": 10
        }
        ```
-   `PUT /reschedule/{appointmentId}`: Reschedule an existing appointment.
    *   Request Body (`AppointmentRequestDTO` - specifies the new operator and time):
        ```json
        {
            "operatorId": 2,
            "startHour": 14
        }
        ```
-   `DELETE /cancel?appointmentId={appointmentId}`: Cancel an appointment by its ID.
-   `GET /appointments?id={operatorId}`: Get all booked appointments for a specific operator.
-   `GET /open-slots/?oid={operatorId}`: Get all open (available) time slots for a specific operator. Slots are presented as merged consecutive hours (e.g., "9-12").
-   `GET /appointment`: Get all appointments booked in the system (for all operators).


## Notes

-   Time is represented in 24-hour format (0-23).
-   Appointments are for a single day context (no dates).
-   Each appointment is 1 hour long.
