package org.anurag.basiccalculator2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Solution {

    boolean isOperator(String str) {
        if (str.length() > 1) {
            return false;
        }

        char c = str.charAt(0);
        List<Character> operators = Arrays.asList('+', '-', '*', '/');

        if (operators.contains(c)) {
            return true;
        }

        return false;
    }

    boolean isOperator(char c) {
        List<Character> operators = Arrays.asList('+', '-', '*', '/');

        if (operators.contains(c)) {
            return true;
        }

        return false;
    }

    int getOperatorPrecedence(String op) {
        if (op.equals("*") || op.equals("/")) {
            return 1;
        }

        return 0;
    }

    boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    String getNextToken(String str, int at) {
        if (isOperator(str.charAt(at))) {
            return str.charAt(at) + "";
        }

        StringBuilder sb = new StringBuilder();

        for(; at < str.length() && isDigit(str.charAt(at)); at++) {
            sb.append(str.charAt(at));
        }

        return sb.toString();
    }

    ArrayList<String> getTokens(String str) {
        str = str
                .replace(" ", "")
                .replace("\t", "");
        ArrayList<String> tokens = new ArrayList<>();
        String lastToken = null;

        for (int at = 0; at < str.length();) {
            String nextToken = getNextToken(str, at);

            if (isOperator(nextToken)) {
                if (lastToken == null || isOperator(lastToken)) {
                    // Must be a unary operator. Get the next token (which must be a
                    // number since this is assumed to be valid expression).
                    String numToken = getNextToken(str, at + 1);
                    tokens.add(nextToken.charAt(0) + numToken);

                    at += 1 + numToken.length();
                    lastToken = nextToken.charAt(0) + numToken;
                } else {
                    tokens.add("" + nextToken.charAt(0));
                    at++;
                    lastToken = nextToken;
                }
            } else {
                // Assumed to be a number
                String numToken = getNextToken(str, at);
                tokens.add(numToken);
                at += numToken.length();
                lastToken = numToken;
            }
        }

        return tokens;
    }

    int doBinaryOperation(String operator, int numL, int numR) {
        if (operator.equals("+")) {
            return numL + numR;
        }

        if (operator.equals("-")) {
            return numL - numR;
        }

        if (operator.equals("*")) {
            return numL * numR;
        }

        if (operator.equals("/")) {
            if (numL * numR < 0) {
                return -1 * (int)Math.floor(Math.abs(numL) / (double)Math.abs(numR));
            }

            return (int)Math.floor(numL / (double)numR);
        }

        return 0;
    }

    public int calculate(String s) {
        ArrayList<String> tokens = getTokens(s);
        Stack<String> operators = new Stack<>();
        Stack<Integer> operands = new Stack<>();

        if (tokens.size() == 0) {
            return 0;
        }

        if (tokens.size() == 1) {
            return Integer.parseInt(tokens.get(0));
        }

        for (String token : tokens) {
            if (isOperator(token)) {
                operators.push(token);
            } else {
                // Number
                operands.push(Integer.parseInt(token));

                if (!operators.empty() && getOperatorPrecedence(operators.peek()) == 1) {
                    // This is a "*" or "/" operation. Perform the operation.
                    int numR = operands.pop();
                    int numL = operands.pop();
                    String operator = operators.pop();
                    operands.push(doBinaryOperation(operator, numL, numR));
                }
            }
        }

        // Now if there are any operators left, the must be "+" or "-".
        // We should process the operations in the original order.
        Stack<String> reversedOperators = new Stack<>();
        Stack<Integer> reversedOperands = new Stack<>();

        while (!operators.empty()) {
            reversedOperators.push(operators.pop());
        }

        while (!operands.empty()) {
            reversedOperands.push(operands.pop());
        }

        operators = reversedOperators;
        operands = reversedOperands;

        while (!operators.empty()) {
            int numL = operands.pop();
            int numR = operands.pop();
            String operator = operators.pop();
            operands.push(doBinaryOperation(operator, numL, numR));
        }

        return operands.pop();
    }

}
