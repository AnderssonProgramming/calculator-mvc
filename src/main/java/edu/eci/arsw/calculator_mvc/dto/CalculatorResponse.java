package edu.eci.arsw.calculator_mvc.dto;

/**
 * Data Transfer Object for calculator response
 */
public class CalculatorResponse {
    private double result;
    private String operation;
    private boolean success;
    private String errorMessage;

    public CalculatorResponse() {}

    public CalculatorResponse(double result, String operation, boolean success) {
        this.result = result;
        this.operation = operation;
        this.success = success;
    }

    public CalculatorResponse(String errorMessage) {
        this.success = false;
        this.errorMessage = errorMessage;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
