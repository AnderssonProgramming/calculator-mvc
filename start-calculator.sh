#!/bin/bash

echo "Starting Spring MVC Calculator Application..."
echo

# Check if Java is installed
if ! command -v java &> /dev/null; then
    echo "ERROR: Java is not installed or not in PATH"
    echo "Please install Java 17 or higher and try again"
    exit 1
fi

# Check if Maven is installed
if ! command -v mvn &> /dev/null; then
    echo "ERROR: Maven is not installed or not in PATH"
    echo "Please install Maven 3.6+ and try again"
    exit 1
fi

echo "Java and Maven found successfully!"
echo

# Clean and compile the project
echo "Cleaning and compiling the project..."
mvn clean compile
if [ $? -ne 0 ]; then
    echo "ERROR: Failed to compile the project"
    exit 1
fi

echo
echo "Starting the application on http://localhost:8081"
echo "Press Ctrl+C to stop the application"
echo

# Start the Spring Boot application
mvn spring-boot:run
