# 🧮 Spring MVC Calculator

A modern, responsive calculator web application built with **Spring Boot** and **Spring MVC** architecture. This multi-user calculator supports basic arithmetic operations and features a beautiful, intuitive user interface.

![Calculator Demo](https://img.shields.io/badge/Spring%20Boot-3.5.3-brightgreen) ![Java](https://img.shields.io/badge/Java-17+-orange) ![Maven](https://img.shields.io/badge/Maven-3.6+-blue) ![License](https://img.shields.io/badge/License-GPL_3.0-yellow)

## 🌟 Features

### ✨ Calculator Operations
- **➕ Addition**: Add two numbers with precision
- **➖ Subtraction**: Subtract numbers accurately  
- **✖️ Multiplication**: Multiply numbers with decimal support
- **➗ Division**: Divide numbers with zero-division protection
- **🔄 All Clear (AC)**: Reset calculator to initial state
- **⌫ Backspace**: Delete last entered character

### 🎨 User Experience
- **🎯 Modern UI**: Beautiful gradient design with glassmorphism effects
- **📱 Responsive**: Works perfectly on desktop, tablet, and mobile devices
- **⌨️ Keyboard Support**: Full keyboard navigation and shortcuts
- **⚡ Real-time Calculations**: Instant feedback and calculations
- **🚨 Error Handling**: User-friendly error messages and validations

### 🏢 Enterprise Features
- **👥 Multi-User Support**: Designed for concurrent user access
- **🔗 REST API**: Clean, documented API endpoints
- **✅ Comprehensive Testing**: Full unit and integration test coverage
- **📊 Health Monitoring**: Application health check endpoints
- **🛡️ CORS Support**: Cross-origin resource sharing enabled

## 🏗️ Architecture

This application implements the **Spring MVC (Model-View-Controller)** architectural pattern:

### 📋 Layer Structure
```
┌─────────────────────┐
│   Presentation      │  ← Static HTML/CSS/JS (View)
├─────────────────────┤
│   Controller        │  ← REST API Endpoints 
├─────────────────────┤
│   Service           │  ← Business Logic (Model)
├─────────────────────┤
│   Data Transfer     │  ← DTOs for API Communication
└─────────────────────┘
```

### 🎯 Components

- **🎮 Controller Layer**: `CalculatorController` - Handles HTTP requests and responses
- **⚙️ Service Layer**: `CalculatorService` - Contains business logic for calculations
- **📦 DTO Layer**: `CalculatorRequest`/`CalculatorResponse` - Data transfer objects
- **🖼️ View Layer**: Static HTML/CSS/JavaScript for user interface

## 🚀 Quick Start

### Prerequisites
- ☕ **Java 17+** (JDK 17 or higher)
- 📦 **Maven 3.6+**
- 🖥️ **IDE** (IntelliJ IDEA, Eclipse, or VS Code recommended)

### 🔧 Installation & Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/AnderssonProgramming/calculator-mvc.git
   cd calculator-mvc
   ```

2. **Build the project**
   ```bash
   mvn clean compile
   ```

3. **Run tests** (optional but recommended)
   ```bash
   mvn test
   ```

4. **Start the application**
   ```bash
   mvn spring-boot:run
   ```

5. **Access the calculator**
   - Open your browser and navigate to: **http://localhost:8081**
   - Start calculating! 🎉

### 🔌 Alternative Ways to Run

**Using Java directly:**
```bash
mvn clean package
java -jar target/calculator-mvc-0.0.1-SNAPSHOT.jar
```

**Using your IDE:**
- Import the project as a Maven project
- Run `CalculatorMvcApplication.java` as a Java application

## 📡 API Documentation

### Base URL: `http://localhost:8081/api/calculator`

### 🔍 Endpoints

#### 1. Calculate Operation
- **URL**: `POST /calculate`
- **Description**: Performs arithmetic calculations
- **Content-Type**: `application/json`

**Request Body:**
```json
{
  "operand1": 10.5,
  "operand2": 5.2,
  "operation": "add"
}
```

**Supported Operations:**
- `add` or `+` - Addition
- `subtract` or `-` - Subtraction  
- `multiply` or `*` - Multiplication
- `divide` or `/` - Division
- `clear` or `ac` - All Clear

**Success Response:**
```json
{
  "result": 15.7,
  "operation": "add",
  "success": true,
  "errorMessage": null
}
```

**Error Response:**
```json
{
  "result": 0,
  "operation": null,
  "success": false,
  "errorMessage": "Cannot divide by zero"
}
```

#### 2. Health Check
- **URL**: `GET /health`
- **Description**: Check if the calculator service is running
- **Response**: `"Calculator service is running"`

### 🧪 API Testing Examples

**Using cURL:**
```bash
# Addition
curl -X POST http://localhost:8081/api/calculator/calculate \
  -H "Content-Type: application/json" \
  -d '{"operand1": 15, "operand2": 25, "operation": "add"}'

# Division with error handling
curl -X POST http://localhost:8081/api/calculator/calculate \
  -H "Content-Type: application/json" \
  -d '{"operand1": 10, "operand2": 0, "operation": "divide"}'

# Health check
curl http://localhost:8081/api/calculator/health
```

## 🧪 Testing

### Running Tests
```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=CalculatorServiceTest

# Run tests with coverage report
mvn clean test jacoco:report
```

### 📊 Test Coverage
- **Service Layer**: 100% method coverage
- **Controller Layer**: Complete API endpoint testing
- **Integration Tests**: Full application context testing

### 🎯 Test Categories
- **Unit Tests**: Individual component testing
- **Integration Tests**: Spring context and HTTP request testing
- **Error Handling**: Edge cases and exception scenarios

## 📁 Project Structure

```
calculator-mvc/
├── 📄 pom.xml                           # Maven configuration
├── 📄 README.md                         # This file
├── 📁 src/
│   ├── 📁 main/
│   │   ├── 📁 java/edu/eci/arsw/calculator_mvc/
│   │   │   ├── 📄 CalculatorMvcApplication.java    # Main application class
│   │   │   ├── 📁 controller/
│   │   │   │   └── 📄 CalculatorController.java    # REST API controller
│   │   │   ├── 📁 dto/
│   │   │   │   ├── 📄 CalculatorRequest.java       # Request DTO
│   │   │   │   └── 📄 CalculatorResponse.java      # Response DTO
│   │   │   └── 📁 service/
│   │   │       └── 📄 CalculatorService.java       # Business logic
│   │   └── 📁 resources/
│   │       ├── 📄 application.properties           # App configuration
│   │       └── 📁 static/
│   │           ├── 📄 index.html                   # Main UI
│   │           └── 📁 js/
│   │               └── 📄 calculator.js            # Frontend logic
│   └── 📁 test/
│       └── 📁 java/edu/eci/arsw/calculator_mvc/
│           ├── 📄 CalculatorMvcApplicationTests.java
│           ├── 📁 controller/
│           │   └── 📄 CalculatorControllerTest.java
│           └── 📁 service/
│               └── 📄 CalculatorServiceTest.java
```

## ⚙️ Configuration

### Application Properties
```properties
# Application settings
spring.application.name=calculator-mvc
server.port=8081

# Error handling
server.error.include-message=always
server.error.include-binding-errors=always

# Logging
logging.level.edu.eci.arsw.calculator_mvc=DEBUG
logging.level.org.springframework.web=INFO
```

### 🔧 Customization Options
- **Port**: Modify `server.port` in `application.properties`
- **Logging**: Adjust logging levels for different packages
- **CORS**: Configure allowed origins in `CalculatorController`

## 🎨 User Interface

### 💡 Features
- **Glassmorphism Design**: Modern frosted glass effect
- **Responsive Layout**: Adapts to different screen sizes
- **Smooth Animations**: Button hover effects and transitions
- **Color-Coded Buttons**: Different colors for numbers, operators, and functions
- **Real-time Display**: Shows both expression and result

### ⌨️ Keyboard Shortcuts
- **Numbers**: `0-9` keys
- **Operations**: `+`, `-`, `*`, `/`
- **Calculate**: `Enter` or `=`
- **Clear**: `Escape` key
- **Delete**: `Backspace` key
- **Decimal**: `.` key

## 🐛 Troubleshooting

### Common Issues

**1. Port Already in Use**
```
Error: Web server failed to start. Port 8081 was already in use.
```
*Solution*: Change the port in `application.properties` or stop the process using port 8081.

**2. Maven Build Issues**
```
Error: Could not find or load main class
```
*Solution*: Run `mvn clean compile` and ensure Java 17+ is installed.

**3. Application Won't Start**
```
Error: APPLICATION FAILED TO START
```
*Solution*: Check logs for specific errors, verify Java version and dependencies.

### 🔧 Debug Mode
Run with debug logging:
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=debug
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

### 📝 Development Guidelines
- Follow Spring Boot best practices
- Maintain test coverage above 90%
- Use meaningful commit messages
- Update documentation for new features

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- **Spring Framework Team** - For the excellent Spring Boot framework
- **Font Awesome** - For the beautiful icons
- **Stack Overflow Community** - For countless solutions and inspiration

## 🔮 Future Enhancements

- [ ] **Scientific Calculator**: Add trigonometric and logarithmic functions
- [ ] **History Feature**: Store and display calculation history
- [ ] **Memory Functions**: M+, M-, MR, MC operations
- [ ] **Themes**: Multiple UI themes and dark mode
- [ ] **Keyboard Layout**: Virtual keyboard display
- [ ] **Unit Converter**: Length, weight, temperature conversions
- [ ] **Export Results**: Save calculations to file
- [ ] **User Profiles**: Personal calculation preferences

---

⭐ **If you found this project helpful, please give it a star!** ⭐

![Made with ❤️](https://img.shields.io/badge/Made%20with-%E2%9D%A4%EF%B8%8F-red) ![Spring Boot](https://img.shields.io/badge/Powered%20by-Spring%20Boot-brightgreen)

---

**Happy Calculating!** 🧮✨