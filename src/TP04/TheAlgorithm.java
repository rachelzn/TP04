package TP04;

import java.util.EmptyStackException;
import java.util.StringTokenizer;
import java.util.Stack;

/**
 * @author Rachel Heningtyas Zanetta Erari
 * NPM: 2206081944
 * Tugas Pemrograman 4: Infix to Postfix Expression
 * https://github.com/rachelzn/TP04.git
 */

public class TheAlgorithm {
    private static String errorHandling = ""; // Declare errorHandling variable

    // Method that converts infix to postfix
    public static String infixToPostfix(String enterInput) {
        // Create a StringTokenizer object
        StringTokenizer tokens = new StringTokenizer(enterInput, "*/^+-() ", true);
        // Create a StringBuilder object
        StringBuilder postfixResult = new StringBuilder();
        // Create a Stack object
        Stack<Character> stack = new Stack<>();

        while (tokens.hasMoreTokens()) {  // Loop through the tokens
            String token = tokens.nextToken();  // Get the next token

            switch (token) {
                case " ":
                    // Ignore whitespace tokens
                    break;
                case "(":
                    stack.push(token.charAt(0));  // Push the opening parenthesis onto the stack
                    break;
                case ")":
                    try {
                        while (stack.peek() != '(') {
                            // Append the popped element to the postfixResult StringBuilder
                            postfixResult.append(stack.pop()).append(" ");
                        }
                        stack.pop();  // Pop the opening parenthesis
                    } catch (Exception e) {
                        setErrorHandling("[Missing open parenthesis]");  // Set the errorHandling variable
                    }
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                    while (!stack.isEmpty() && getOrder(token.charAt(0)) <= getOrder(stack.peek())) {
                        // Append the popped element to the postfixResult StringBuilder
                        postfixResult.append(stack.pop()).append(" ");
                    }
                    stack.push(token.charAt(0));  // Push the operator onto the stack
                    break;
                case "^":
                    while (!stack.isEmpty() && getOrder(token.charAt(0)) < getOrder(stack.peek())) {
                        // Append the popped element to the postfixResult StringBuilder
                        postfixResult.append(stack.pop()).append(" ");
                    }
                    stack.push(token.charAt(0));  // Push the exponentiation operator onto the stack
                    break;
                default:
                    // Append the operand to the postfixResult StringBuilder
                    postfixResult.append(token).append(" ");
                    break;
            }
        }

        while (!stack.isEmpty()) {
            if (stack.peek() == '(') {
                setErrorHandling("[Missing close parenthesis]");  // Set the errorHandling variable
            }
            // Append the popped element to the postfixResult StringBuilder
            postfixResult.append(stack.pop()).append(" ");
        }

        return postfixResult.toString();  // Convert the StringBuilder to a String and return it
    }

    // Determine the priority scale of the operators
    public static int getOrder(char operator) {
        return switch (operator) {
            case '+', '-' -> 1;  // For addition and subtraction operators, return 1
            case '*', '/' -> 2;  // For multiplication and division operators, return 2
            case '^' -> 3;  // For exponentiation operator, return 3
            default -> -1;  // For invalid operators, return -1
        };
    }

    // Method that performs calculations on the postfix expression
    public static Long calculation(String postfixValue) {
        String temp = "";  // Temporary variable to store operands
        Stack<Long> stack = new Stack<>();  // Create a Stack object
        long result = 0;  // Variable to store the result

        for (int i = 0; i < postfixValue.length(); i++) {  // Loop through the characters in the postfixValue string
            char postfixChar = postfixValue.charAt(i);  // Get the character at the current index

            try {
                switch (postfixChar) {
                    // Concatenate the operand to the temp variable
                    case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' -> temp = temp.concat(postfixChar + "");
                    case ' ' -> {
                        if (!temp.equals("")) {
                            // Convert the temp variable to Long and push it onto the stack
                            stack.push(Long.parseLong(temp));
                            temp = "";  // Reset the temp variable
                        }
                    }
                    case '+' -> {
                        result = stack.pop();
                        result += stack.pop();
                        stack.push(result);
                    }
                    case '/' -> {
                        result = stack.pop();
                        if (result == 0) {
                            throw new Exception("[Division by zero]"); // Return [Division by zero]
                        }
                        result = stack.pop() / result;
                        stack.push(result);
                    }
                    case '-' -> {
                        result = stack.pop();
                        result = stack.pop() - result;
                        stack.push(result);
                    }
                    case '*' -> {
                        result = stack.pop();
                        result *= stack.pop();
                        stack.push(result);
                    }
                    case '^' -> {
                        result = stack.pop();
                        double power = Math.pow(stack.pop(), result);
                        if (Double.isInfinite(power)) {
                            throw new Exception("[Division by zero]"); // Return [Division by zero]
                        }
                        stack.push((long) power);
                    }
                    default -> throw new Exception("[Invalid operand]"); // Return [Invalid operand]
                }
            } catch (EmptyStackException e) {
                setErrorHandling("[Missing operand]");  // Return [Missing operand]
            } catch (Exception e) {
                setErrorHandling(e.getMessage());  // Return message
            }
        }

        if (stack.isEmpty()) {
            setErrorHandling("[Missing operand]");  // Return [Missing operand]
            return result;
        } else if (stack.size() > 1) {
            setErrorHandling("[Missing operator]");  // Return [Missing operator]
            return result;
        } else
            return stack.pop();  // Return the final result
    }

    // Method that resets the error handling
    public static void resetError() {
        TheAlgorithm.errorHandling = "";  // Reset the errorHandling variable
    }

    // Setter for error handling
    private static void setErrorHandling(String errorHandling) {
        if (!TheAlgorithm.errorHandling.equals("")) {
            return;  // If the errorHandling variable is already set, return
        }
        TheAlgorithm.errorHandling = errorHandling;  // Set the errorHandling variable
    }

    // Getter for error handling
    public static String getErrorHandling() {
        if (errorHandling.equals("")) {
            return "[]";  // If the errorHandling variable is empty, return "[]"
        }
        return errorHandling;  // Return the errorHandling variable
    }
}
