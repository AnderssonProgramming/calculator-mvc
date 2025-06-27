@echo off
echo Starting Spring MVC Calculator Application...
echo.

:: Check if Java is installed
java -version >nul 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Java is not installed or not in PATH
    echo Please install Java 17 or higher and try again
    pause
    exit /b 1
)

:: Check if Maven is installed
mvn -version >nul 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Maven is not installed or not in PATH
    echo Please install Maven 3.6+ and try again
    pause
    exit /b 1
)

echo Java and Maven found successfully!
echo.

:: Clean and compile the project
echo Cleaning and compiling the project...
call mvn clean compile
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Failed to compile the project
    pause
    exit /b 1
)

echo.
echo Starting the application on http://localhost:8081
echo Press Ctrl+C to stop the application
echo.

:: Start the Spring Boot application
call mvn spring-boot:run

pause
