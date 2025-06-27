package edu.eci.arsw.calculator_mvc.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CalculatorServiceTest {

    private CalculatorService calculatorService;

    @BeforeEach
    void setUp() {
        calculatorService = new CalculatorService();
    }

    @Test
    void testAdd() {
        assertEquals(5.0, calculatorService.add(2.0, 3.0));
        assertEquals(0.0, calculatorService.add(-5.0, 5.0));
        assertEquals(-8.0, calculatorService.add(-3.0, -5.0));
        assertEquals(7.5, calculatorService.add(3.5, 4.0));
    }

    @Test
    void testSubtract() {
        assertEquals(-1.0, calculatorService.subtract(2.0, 3.0));
        assertEquals(0.0, calculatorService.subtract(5.0, 5.0));
        assertEquals(2.0, calculatorService.subtract(-3.0, -5.0));
        assertEquals(-0.5, calculatorService.subtract(3.5, 4.0));
    }

    @Test
    void testMultiply() {
        assertEquals(6.0, calculatorService.multiply(2.0, 3.0));
        assertEquals(0.0, calculatorService.multiply(0.0, 5.0));
        assertEquals(15.0, calculatorService.multiply(-3.0, -5.0));
        assertEquals(14.0, calculatorService.multiply(3.5, 4.0));
    }

    @Test
    void testDivide() {
        assertEquals(2.0, calculatorService.divide(6.0, 3.0));
        assertEquals(0.0, calculatorService.divide(0.0, 5.0));
        assertEquals(0.6, calculatorService.divide(-3.0, -5.0));
        assertEquals(0.875, calculatorService.divide(3.5, 4.0));
    }

    @Test
    void testDivideByZero() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> calculatorService.divide(5.0, 0.0)
        );
        assertEquals("Cannot divide by zero", exception.getMessage());
    }

    @Test
    void testClear() {
        assertEquals(0.0, calculatorService.clear());
    }

    @Test
    void testPercentage() {
        assertEquals(50.0, calculatorService.percentage(200.0, 25.0));
        assertEquals(0.0, calculatorService.percentage(0.0, 50.0));
        assertEquals(15.0, calculatorService.percentage(100.0, 15.0));
        assertEquals(-25.0, calculatorService.percentage(-100.0, 25.0));
    }

    @Test
    void testSqrt() {
        assertEquals(2.0, calculatorService.sqrt(4.0));
        assertEquals(0.0, calculatorService.sqrt(0.0));
        assertEquals(5.0, calculatorService.sqrt(25.0));
        assertEquals(Math.sqrt(2), calculatorService.sqrt(2.0), 0.0001);
    }

    @Test
    void testSqrtNegativeNumber() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> calculatorService.sqrt(-4.0)
        );
        assertEquals("Cannot calculate square root of negative number", exception.getMessage());
    }

    @Test
    void testPower() {
        assertEquals(8.0, calculatorService.power(2.0, 3.0));
        assertEquals(1.0, calculatorService.power(5.0, 0.0));
        assertEquals(0.25, calculatorService.power(2.0, -2.0));
        assertEquals(27.0, calculatorService.power(3.0, 3.0));
    }
}
