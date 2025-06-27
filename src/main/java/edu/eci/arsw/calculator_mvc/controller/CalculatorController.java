package edu.eci.arsw.calculator_mvc.controller;

import edu.eci.arsw.calculator_mvc.dto.CalculatorRequest;
import edu.eci.arsw.calculator_mvc.dto.CalculatorResponse;
import edu.eci.arsw.calculator_mvc.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for calculator operations
 */
@RestController
@RequestMapping("/api/calculator")
@CrossOrigin(origins = "*")
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;

    /**
     * Performs calculator operations
     * @param request Calculator request with operands and operation
     * @return Calculator response with result
     */
    @PostMapping("/calculate")
    public ResponseEntity<CalculatorResponse> calculate(@RequestBody CalculatorRequest request) {
        try {
            double result;
            String operation = request.getOperation();

            switch (operation.toLowerCase()) {
                case "add":
                case "+":
                    result = calculatorService.add(request.getOperand1(), request.getOperand2());
                    break;
                case "subtract":
                case "-":
                    result = calculatorService.subtract(request.getOperand1(), request.getOperand2());
                    break;
                case "multiply":
                case "*":
                    result = calculatorService.multiply(request.getOperand1(), request.getOperand2());
                    break;
                case "divide":
                case "/":
                    result = calculatorService.divide(request.getOperand1(), request.getOperand2());
                    break;
                case "clear":
                case "ac":
                    result = calculatorService.clear();
                    break;
                case "sqrt":
                    result = calculatorService.sqrt(request.getOperand1());
                    break;
                case "power":
                case "pow":
                case "^":
                    result = calculatorService.power(request.getOperand1(), request.getOperand2());
                    break;
                default:
                    return ResponseEntity.badRequest()
                            .body(new CalculatorResponse("Invalid operation: " + operation));
            }

            CalculatorResponse response = new CalculatorResponse(result, operation, true);
            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new CalculatorResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CalculatorResponse("An error occurred: " + e.getMessage()));
        }
    }

    /**
     * Health check endpoint
     * @return Status message
     */
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Calculator service is running");
    }

    /**
     * Get all supported operations
     * @return List of supported operations
     */
    @GetMapping("/operations")
    public ResponseEntity<CalculatorResponse> getSupportedOperations() {
        String[] operations = {"add (+)", "subtract (-)", "multiply (*)", "divide (/)", "clear (ac)", "sqrt", "power (^)", "percentage (%)"};
        CalculatorResponse response = new CalculatorResponse();
        response.setSuccess(true);
        response.setErrorMessage("Supported operations: " + String.join(", ", operations));
        return ResponseEntity.ok(response);
    }

    /**
     * Calculate percentage
     * @param request Calculator request for percentage calculation
     * @return Calculator response with percentage result
     */
    @PostMapping("/percentage")
    public ResponseEntity<CalculatorResponse> calculatePercentage(@RequestBody CalculatorRequest request) {
        try {
            double result = calculatorService.percentage(request.getOperand1(), request.getOperand2());
            CalculatorResponse response = new CalculatorResponse(result, "percentage", true);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new CalculatorResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CalculatorResponse("An error occurred: " + e.getMessage()));
        }
    }
}
