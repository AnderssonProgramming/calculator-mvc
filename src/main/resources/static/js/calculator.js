/**
 * Spring MVC Calculator JavaScript
 * Handles the calculator UI interactions and API calls
 */

let currentExpression = '';
let currentResult = '0';
let lastOperator = '';
let waitingForOperand = false;

// DOM elements
const expressionDisplay = document.getElementById('expression');
const resultDisplay = document.getElementById('result');

/**
 * Updates the display with current expression and result
 */
function updateDisplay() {
    expressionDisplay.textContent = currentExpression;
    resultDisplay.textContent = currentResult;
}

/**
 * Adds a character to the current expression
 * @param {string} value - The character to add
 */
function addToExpression(value) {
    if (waitingForOperand && !isOperator(value)) {
        currentExpression = '';
        waitingForOperand = false;
    }

    if (isOperator(value)) {
        if (currentExpression === '' && value === '-') {
            // Allow negative numbers
            currentExpression = '-';
        } else if (currentExpression !== '' && !isOperator(currentExpression.slice(-1))) {
            currentExpression += ' ' + value + ' ';
            lastOperator = value;
        } else if (currentExpression !== '' && isOperator(currentExpression.slice(-1))) {
            // Replace the last operator
            currentExpression = currentExpression.slice(0, -3) + ' ' + value + ' ';
            lastOperator = value;
        }
    } else {
        currentExpression += value;
    }
    
    updateDisplay();
}

/**
 * Checks if a character is an operator
 * @param {string} char - The character to check
 * @returns {boolean} True if the character is an operator
 */
function isOperator(char) {
    return ['+', '-', '*', '/', '×'].includes(char);
}

/**
 * Clears all values (All Clear)
 */
function clearAll() {
    currentExpression = '';
    currentResult = '0';
    lastOperator = '';
    waitingForOperand = false;
    updateDisplay();
    
    // Also call the backend AC function
    callCalculatorAPI('0', '0', 'ac');
}

/**
 * Deletes the last character from the expression
 */
function deleteLast() {
    if (currentExpression.length > 0) {
        // If the last character is a space (part of an operator), remove the whole operator
        if (currentExpression.slice(-1) === ' ') {
            currentExpression = currentExpression.slice(0, -3);
        } else {
            currentExpression = currentExpression.slice(0, -1);
        }
        updateDisplay();
    }
}

/**
 * Evaluates the current expression
 */
function calculate() {
    if (currentExpression === '') {
        return;
    }

    try {
        // Parse the expression to extract operands and operator
        const tokens = currentExpression.split(' ');
        
        if (tokens.length === 1) {
            // Single number, just display it
            currentResult = parseFloat(tokens[0]).toString();
            updateDisplay();
            return;
        }

        // Handle complex expressions by evaluating them step by step
        if (tokens.length >= 3) {
            let result = parseFloat(tokens[0]);
            
            for (let i = 1; i < tokens.length; i += 2) {
                const operator = tokens[i];
                const operand = parseFloat(tokens[i + 1]);
                
                if (isNaN(operand)) {
                    throw new Error('Invalid operand');
                }
                
                // Call the backend API for each operation
                await callCalculatorAPI(result, operand, operator);
                result = parseFloat(currentResult);
            }
        }
    } catch (error) {
        showError('Invalid expression');
        console.error('Calculation error:', error);
    }
}

/**
 * Calls the calculator API
 * @param {number} operand1 - First operand
 * @param {number} operand2 - Second operand
 * @param {string} operation - Operation to perform
 */
async function callCalculatorAPI(operand1, operand2, operation) {
    try {
        // Convert display operator to API operation
        let apiOperation = operation;
        if (operation === '×') {
            apiOperation = '*';
        }

        const requestBody = {
            operand1: parseFloat(operand1),
            operand2: parseFloat(operand2),
            operation: apiOperation
        };

        const response = await fetch('/api/calculator/calculate', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(requestBody)
        });

        const data = await response.json();

        if (data.success) {
            currentResult = formatResult(data.result);
            waitingForOperand = true;
            updateDisplay();
        } else {
            showError(data.errorMessage || 'Calculation error');
        }
    } catch (error) {
        showError('Network error - please check if the server is running');
        console.error('API call error:', error);
    }
}

/**
 * Formats the result for display
 * @param {number} result - The result to format
 * @returns {string} Formatted result
 */
function formatResult(result) {
    // Handle very large or very small numbers
    if (Math.abs(result) > 1e10 || (Math.abs(result) < 1e-6 && result !== 0)) {
        return result.toExponential(6);
    }
    
    // Round to avoid floating point precision issues
    return Math.round(result * 1e10) / 1e10;
}

/**
 * Shows an error message
 * @param {string} message - Error message to display
 */
function showError(message) {
    currentResult = message;
    resultDisplay.classList.add('error');
    updateDisplay();
    
    // Clear error after 3 seconds
    setTimeout(() => {
        resultDisplay.classList.remove('error');
        currentResult = '0';
        currentExpression = '';
        updateDisplay();
    }, 3000);
}

/**
 * Handles keyboard input
 */
document.addEventListener('keydown', function(event) {
    const key = event.key;
    
    // Prevent default behavior for calculator keys
    if ('0123456789+-*/.='.includes(key) || key === 'Enter' || key === 'Escape' || key === 'Backspace') {
        event.preventDefault();
    }
    
    // Handle number keys
    if ('0123456789'.includes(key)) {
        addToExpression(key);
    }
    // Handle operator keys
    else if (key === '+') {
        addToExpression('+');
    }
    else if (key === '-') {
        addToExpression('-');
    }
    else if (key === '*') {
        addToExpression('*');
    }
    else if (key === '/') {
        addToExpression('/');
    }
    else if (key === '.') {
        addToExpression('.');
    }
    // Handle special keys
    else if (key === 'Enter' || key === '=') {
        calculate();
    }
    else if (key === 'Escape') {
        clearAll();
    }
    else if (key === 'Backspace') {
        deleteLast();
    }
});

/**
 * Check API health on page load
 */
window.addEventListener('load', async function() {
    try {
        const response = await fetch('/api/calculator/health');
        const data = await response.text();
        console.log('API Health:', data);
    } catch (error) {
        console.warn('API health check failed:', error);
        showError('Server connection failed');
    }
});

// Initialize display
updateDisplay();
