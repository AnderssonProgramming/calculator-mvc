# 🚀 Spring MVC Calculator - Deployment Guide

This guide provides step-by-step instructions for deploying the Spring MVC Calculator application locally and preparing it for AWS deployment.

## 📋 Prerequisites

Before deploying the calculator application, ensure you have the following installed:

### Required Software
- **Java Development Kit (JDK) 17+**
  - Download from: [Oracle JDK](https://www.oracle.com/java/technologies/javase-downloads.html) or [OpenJDK](https://openjdk.org/)
  - Verify installation: `java -version`

- **Apache Maven 3.6+**
  - Download from: [Maven Official Website](https://maven.apache.org/download.cgi)
  - Verify installation: `mvn -version`

- **Git** (for cloning the repository)
  - Download from: [Git Official Website](https://git-scm.com/downloads)
  - Verify installation: `git --version`

### System Requirements
- **RAM**: Minimum 2GB, Recommended 4GB+
- **Disk Space**: At least 500MB free space
- **Network**: Internet connection for downloading dependencies

## 🔧 Local Deployment

### Method 1: Quick Start (Recommended)

#### Windows Users:
```batch
# Navigate to project directory
cd calculator-mvc

# Run the start script
start-calculator.bat
```

#### Linux/Mac Users:
```bash
# Navigate to project directory
cd calculator-mvc

# Make script executable and run
chmod +x start-calculator.sh
./start-calculator.sh
```

### Method 2: Manual Deployment

1. **Clone and Setup**
   ```bash
   git clone <your-repository-url>
   cd calculator-mvc
   ```

2. **Install Dependencies**
   ```bash
   mvn clean install
   ```

3. **Run Tests** (Optional but recommended)
   ```bash
   mvn test
   ```

4. **Start the Application**
   ```bash
   mvn spring-boot:run
   ```

5. **Access the Application**
   - Open your browser
   - Navigate to: http://localhost:8081
   - Start using the calculator! 🧮

### Method 3: JAR Deployment

1. **Build the JAR**
   ```bash
   mvn clean package
   ```

2. **Run the JAR**
   ```bash
   java -jar target/calculator-mvc-0.0.1-SNAPSHOT.jar
   ```

3. **Access the Application**
   - Browser URL: http://localhost:8081

## 🧪 Testing the Deployment

### Automated API Testing
Run the provided test script to verify all endpoints:

#### Windows:
```batch
test-api.bat
```

#### Linux/Mac:
```bash
chmod +x test-api.sh
./test-api.sh
```

### Manual Testing Checklist

#### ✅ UI Testing
- [ ] Calculator loads properly
- [ ] All buttons are clickable
- [ ] Display shows numbers correctly
- [ ] Operations work (addition, subtraction, multiplication, division)
- [ ] All Clear (AC) button resets calculator
- [ ] Keyboard input works
- [ ] Responsive design on different screen sizes

#### ✅ API Testing
- [ ] Health check: `GET /api/calculator/health`
- [ ] Basic operations: `POST /api/calculator/calculate`
- [ ] Error handling (e.g., division by zero)
- [ ] Percentage calculation: `POST /api/calculator/percentage`
- [ ] Supported operations: `GET /api/calculator/operations`

### Sample cURL Commands
```bash
# Health check
curl http://localhost:8081/api/calculator/health

# Addition
curl -X POST http://localhost:8081/api/calculator/calculate \
  -H "Content-Type: application/json" \
  -d '{"operand1": 10, "operand2": 5, "operation": "add"}'

# Division by zero (error case)
curl -X POST http://localhost:8081/api/calculator/calculate \
  -H "Content-Type: application/json" \
  -d '{"operand1": 10, "operand2": 0, "operation": "divide"}'
```

## 🐛 Troubleshooting

### Common Issues and Solutions

#### ❌ Port Already in Use
**Error**: `Web server failed to start. Port 8081 was already in use.`

**Solutions**:
1. **Kill the process using the port**:
   ```bash
   # Windows
   netstat -ano | findstr 8081
   taskkill /PID <PID_NUMBER> /F
   
   # Linux/Mac
   lsof -ti:8081 | xargs kill -9
   ```

2. **Change the port** (edit `src/main/resources/application.properties`):
   ```properties
   server.port=8082
   ```

#### ❌ Java Version Issues
**Error**: `UnsupportedClassVersionError` or version compatibility issues

**Solution**: Ensure you're using Java 17 or higher:
```bash
java -version
javac -version
```

#### ❌ Maven Build Failures
**Error**: Dependencies cannot be downloaded or build fails

**Solutions**:
1. **Clear Maven cache**:
   ```bash
   mvn dependency:purge-local-repository
   ```

2. **Update Maven dependencies**:
   ```bash
   mvn clean install -U
   ```

3. **Check internet connection** and proxy settings

#### ❌ Application Won't Start
**Error**: Various Spring Boot startup errors

**Solutions**:
1. **Check logs** for specific error messages
2. **Verify Java version** compatibility
3. **Clean and rebuild**:
   ```bash
   mvn clean compile
   ```

## 📊 Performance Optimization

### JVM Tuning for Production
```bash
java -Xms512m -Xmx1024m -XX:+UseG1GC \
  -jar target/calculator-mvc-0.0.1-SNAPSHOT.jar
```

### Application Properties for Production
```properties
# Production settings
server.port=8081
logging.level.root=WARN
logging.level.edu.eci.arsw.calculator_mvc=INFO

# Performance settings
server.tomcat.max-threads=200
server.tomcat.min-spare-threads=10
```

## 🔒 Security Considerations

### Basic Security Setup
1. **Change default port** for production
2. **Enable HTTPS** (SSL/TLS)
3. **Add authentication** if needed
4. **Configure CORS** properly for production domains
5. **Use environment variables** for sensitive configurations

### Environment Variables
```bash
export SERVER_PORT=8081
export LOGGING_LEVEL=INFO
```

## 📈 Monitoring and Health Checks

### Built-in Endpoints
- **Health Check**: `GET /api/calculator/health`
- **Application Info**: Available through Spring Boot Actuator (if enabled)

### Adding Spring Boot Actuator (Optional)
Add to `pom.xml`:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

Access endpoints:
- `/actuator/health` - Application health
- `/actuator/info` - Application information
- `/actuator/metrics` - Application metrics

## 🌐 Preparing for AWS Deployment

### Prerequisites for AWS
- AWS CLI installed and configured
- AWS account with appropriate permissions
- Basic understanding of AWS services (EC2, Elastic Beanstalk, or ECS)

### Recommended AWS Services

#### Option 1: AWS Elastic Beanstalk (Easiest)
1. Create application package (JAR file)
2. Upload to Elastic Beanstalk
3. Configure environment settings
4. Deploy and monitor

#### Option 2: AWS EC2 (More Control)
1. Launch EC2 instance with Java 17+
2. Install Maven and Git
3. Clone and deploy application
4. Configure security groups
5. Set up load balancer if needed

#### Option 3: AWS ECS/Fargate (Container-based)
1. Create Dockerfile
2. Build and push Docker image to ECR
3. Create ECS task definition
4. Deploy to ECS cluster

### Docker Support (Future Enhancement)
Create `Dockerfile`:
```dockerfile
FROM openjdk:17-jdk-slim
COPY target/calculator-mvc-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

## 📝 Deployment Checklist

### Pre-Deployment
- [ ] All tests pass (`mvn test`)
- [ ] Application builds successfully (`mvn clean package`)
- [ ] Configuration files are properly set
- [ ] Environment variables are configured
- [ ] Security settings are reviewed

### Post-Deployment
- [ ] Application starts without errors
- [ ] Health endpoint responds correctly
- [ ] UI loads and functions properly
- [ ] API endpoints work as expected
- [ ] Error handling works correctly
- [ ] Performance is acceptable
- [ ] Logs are being generated properly

## 🆘 Support and Documentation

### Getting Help
1. **Check the README.md** for basic information
2. **Review logs** in the console or log files
3. **Test API endpoints** using provided scripts
4. **Check Spring Boot documentation** for advanced configurations

### Useful Resources
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring MVC Guide](https://spring.io/guides/gs/serving-web-content/)
- [Maven Documentation](https://maven.apache.org/guides/)
- [AWS Deployment Guides](https://docs.aws.amazon.com/elasticbeanstalk/)
