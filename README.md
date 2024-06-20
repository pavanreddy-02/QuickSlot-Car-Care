# Car Service Scheduler

## Introduction
Car Service Scheduler is a Java-based application designed to manage and schedule car services. The project uses Spring Boot for its backend framework and integrates with a SQL database for data persistence.

## Prerequisites
- **Java Development Kit (JDK) 8 or higher**: [Download and Install JDK](https://www.oracle.com/java/technologies/javase-downloads.html) or [OpenJDK](https://jdk.java.net/).
- **SQL Workbench**: [Download SQL Workbench](https://www.sql-workbench.eu/).
- **Apache Maven**: [Download and Install Maven](https://maven.apache.org/download.cgi).
- **IntelliJ IDEA** (or any other Java IDE): [Download IntelliJ IDEA](https://www.jetbrains.com/idea/download/).

## Installation and Setup

### Step 1: Download and Install Prerequisites
1. **Install Java Development Kit (JDK)**
    - Download and install JDK from the [Oracle JDK download page](https://www.oracle.com/java/technologies/javase-downloads.html) or [OpenJDK](https://jdk.java.net/).
    - Ensure the `JAVA_HOME` environment variable is set and `java` and `javac` are in your system's PATH.

2. **Install SQL Workbench**
    - Download and install SQL Workbench from the [SQL Workbench download page](https://www.sql-workbench.eu/).

3. **Install Maven**
    - Download Maven from the [Maven download page](https://maven.apache.org/download.cgi).
    - Follow the installation instructions for your operating system.

4. **Install IntelliJ IDEA**
    - Download and install IntelliJ IDEA from the [IntelliJ IDEA download page](https://www.jetbrains.com/idea/download/).

### Step 2: Setup the Project
1. **Extract the ZIP File**
    - Download and save the `carservicescheduler.zip` file to a directory on your computer.
    - Extract the contents of the ZIP file:
        - On Windows: Right-click the file and select "Extract All".
        - On macOS/Linux: Use a file archiver like `unzip` in the terminal.

2. **Open the Project in IntelliJ IDEA**
    - Open IntelliJ IDEA.
    - Click on `File -> Open` and navigate to the extracted `carservicescheduler` directory.
    - Select the directory and open it.

### Step 3: Configure the Database
1. **Setup Database Connection in `application.properties`**
    - Open `src/main/resources/application.properties`.
    - Configure the database connection properties:
      ```properties
      spring.datasource.url=jdbc:mysql://localhost:3306/carservice
      spring.datasource.username=root
      spring.datasource.password=password
      spring.jpa.hibernate.ddl-auto=update
      spring.jpa.show-sql=true
      spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
      ```

### Step 4: Add Maven Dependencies
1. **Open `pom.xml` and add the required dependencies:**
   ```xml
   <dependencies>
       <!-- Spring Boot Starter Data JPA -->
       <dependency>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-data-jpa</artifactId>
       </dependency>

       <!-- Spring Boot Starter Web -->
       <dependency>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-web</artifactId>
       </dependency>

       <!-- MySQL Connector -->
       <dependency>
           <groupId>mysql</groupId>
           <artifactId>mysql-connector-java</artifactId>
           <scope>runtime</scope>
       </dependency>

       <!-- Spring Boot Starter Test -->
       <dependency>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-test</artifactId>
           <scope>test</scope>
       </dependency>
   </dependencies>

Step 5: Build and Run the Project
Build the Project Using Maven

Open a terminal or use the IntelliJ terminal.
Navigate to the project directory:

cd path/to/carservicescheduler
Run the Maven build command:

mvn clean install
Run the Application

In IntelliJ IDEA, run the CarServiceSchedulerApplication class (usually found in src/main/java/com/yourcompany/carservicescheduler).