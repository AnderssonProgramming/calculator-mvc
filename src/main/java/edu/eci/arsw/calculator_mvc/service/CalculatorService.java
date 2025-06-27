package edu.eci.arsw.calculator_mvc.service;

import org.springframework.stereotype.Service;

/**
 * Service class that implements the calculator operations
 */
@Service
public class CalculatorService {

    /**
     * Performs addition operation
     * @param a first operand
     * @param b second operand
     * @return result of a + b
     */
    public double add(double a, double b) {
        return a + b;
    }

    /**
     * Performs subtraction operation
     * @param a first operand
     * @param b second operand
     * @return result of a - b
     */
    public double subtract(double a, double b) {
        return a - b;
    }

    /**
     * Performs multiplication operation
     * @param a first operand
     * @param b second operand
     * @return result of a * b
     */
    public double multiply(double a, double b) {
        return a * b;
    }

    /**
     * Performs division operation
     * @param a dividend
     * @param b divisor
     * @return result of a / b
     * @throws IllegalArgumentException if divisor is zero
     */
    public double divide(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return a / b;
    }

    /**
     * Clears all values (All Clear function)
     * @return 0
     */
    public double clear() {
        return 0;
    }

    /**
     * Calculates percentage
     * @param value the value to calculate percentage of
     * @param percentage the percentage to calculate
     * @return result of (value * percentage) / 100
     */
    public double percentage(double value, double percentage) {
        return (value * percentage) / 100;
    }

    /**
     * Calculates square root
     * @param value the value to calculate square root of
     * @return square root of the value
     * @throws IllegalArgumentException if value is negative
     */
    public double sqrt(double value) {
        if (value < 0) {
            throw new IllegalArgumentException("Cannot calculate square root of negative number");
        }
        return Math.sqrt(value);
    }

    /**
     * Calculates power
     * @param base the base number
     * @param exponent the exponent
     * @return result of base^exponent
     */
    public double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }
}
