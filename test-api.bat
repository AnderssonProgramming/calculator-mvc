@echo off
echo Testing Spring MVC Calculator API...
echo.

:: Test health endpoint
echo Testing health endpoint...
curl -s http://localhost:8081/api/calculator/health
echo.
echo.

:: Test supported operations
echo Testing supported operations endpoint...
curl -s http://localhost:8081/api/calculator/operations
echo.
echo.

:: Test addition
echo Testing addition: 15 + 25...
curl -s -X POST http://localhost:8081/api/calculator/calculate -H "Content-Type: application/json" -d "{\"operand1\": 15, \"operand2\": 25, \"operation\": \"add\"}"
echo.
echo.

:: Test subtraction
echo Testing subtraction: 100 - 45...
curl -s -X POST http://localhost:8081/api/calculator/calculate -H "Content-Type: application/json" -d "{\"operand1\": 100, \"operand2\": 45, \"operation\": \"subtract\"}"
echo.
echo.

:: Test multiplication
echo Testing multiplication: 12 * 8...
curl -s -X POST http://localhost:8081/api/calculator/calculate -H "Content-Type: application/json" -d "{\"operand1\": 12, \"operand2\": 8, \"operation\": \"multiply\"}"
echo.
echo.

:: Test division
echo Testing division: 144 / 12...
curl -s -X POST http://localhost:8081/api/calculator/calculate -H "Content-Type: application/json" -d "{\"operand1\": 144, \"operand2\": 12, \"operation\": \"divide\"}"
echo.
echo.

:: Test division by zero
echo Testing division by zero: 10 / 0...
curl -s -X POST http://localhost:8081/api/calculator/calculate -H "Content-Type: application/json" -d "{\"operand1\": 10, \"operand2\": 0, \"operation\": \"divide\"}"
echo.
echo.

:: Test square root
echo Testing square root: sqrt(25)...
curl -s -X POST http://localhost:8081/api/calculator/calculate -H "Content-Type: application/json" -d "{\"operand1\": 25, \"operand2\": 0, \"operation\": \"sqrt\"}"
echo.
echo.

:: Test power
echo Testing power: 2^3...
curl -s -X POST http://localhost:8081/api/calculator/calculate -H "Content-Type: application/json" -d "{\"operand1\": 2, \"operand2\": 3, \"operation\": \"power\"}"
echo.
echo.

:: Test percentage
echo Testing percentage: 25% of 200...
curl -s -X POST http://localhost:8081/api/calculator/percentage -H "Content-Type: application/json" -d "{\"operand1\": 200, \"operand2\": 25, \"operation\": \"percentage\"}"
echo.
echo.

echo All API tests completed!
pause
