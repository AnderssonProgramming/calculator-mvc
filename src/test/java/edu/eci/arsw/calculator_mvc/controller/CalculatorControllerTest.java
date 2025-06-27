package edu.eci.arsw.calculator_mvc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.eci.arsw.calculator_mvc.dto.CalculatorRequest;
import edu.eci.arsw.calculator_mvc.service.CalculatorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CalculatorController.class)
class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CalculatorService calculatorService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddOperation() throws Exception {
        when(calculatorService.add(anyDouble(), anyDouble())).thenReturn(8.0);

        CalculatorRequest request = new CalculatorRequest(5.0, 3.0, "add");

        mockMvc.perform(post("/api/calculator/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(8.0))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.operation").value("add"));
    }

    @Test
    void testSubtractOperation() throws Exception {
        when(calculatorService.subtract(anyDouble(), anyDouble())).thenReturn(2.0);

        CalculatorRequest request = new CalculatorRequest(5.0, 3.0, "subtract");

        mockMvc.perform(post("/api/calculator/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(2.0))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.operation").value("subtract"));
    }

    @Test
    void testMultiplyOperation() throws Exception {
        when(calculatorService.multiply(anyDouble(), anyDouble())).thenReturn(15.0);

        CalculatorRequest request = new CalculatorRequest(5.0, 3.0, "multiply");

        mockMvc.perform(post("/api/calculator/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(15.0))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.operation").value("multiply"));
    }

    @Test
    void testDivideOperation() throws Exception {
        when(calculatorService.divide(anyDouble(), anyDouble())).thenReturn(1.67);

        CalculatorRequest request = new CalculatorRequest(5.0, 3.0, "divide");

        mockMvc.perform(post("/api/calculator/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(1.67))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.operation").value("divide"));
    }

    @Test
    void testClearOperation() throws Exception {
        when(calculatorService.clear()).thenReturn(0.0);

        CalculatorRequest request = new CalculatorRequest(0.0, 0.0, "clear");

        mockMvc.perform(post("/api/calculator/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(0.0))
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.operation").value("clear"));
    }

    @Test
    void testInvalidOperation() throws Exception {
        CalculatorRequest request = new CalculatorRequest(5.0, 3.0, "invalid");

        mockMvc.perform(post("/api/calculator/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.errorMessage").value("Invalid operation: invalid"));
    }

    @Test
    void testDivisionByZero() throws Exception {
        when(calculatorService.divide(anyDouble(), anyDouble()))
                .thenThrow(new IllegalArgumentException("Cannot divide by zero"));

        CalculatorRequest request = new CalculatorRequest(5.0, 0.0, "divide");

        mockMvc.perform(post("/api/calculator/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.errorMessage").value("Cannot divide by zero"));
    }

    @Test
    void testHealthEndpoint() throws Exception {
        mockMvc.perform(get("/api/calculator/health"))
                .andExpect(status().isOk())
                .andExpect(content().string("Calculator service is running"));
    }
}
