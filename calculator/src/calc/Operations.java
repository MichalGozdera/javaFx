package calc;

import javafx.event.Event;
import javafx.scene.control.Button;

import java.math.BigDecimal;
import java.math.MathContext;

public class Operations {
    private static BigDecimal firstNumber;
    private static BigDecimal secondNumber;
    private static BigDecimal result;

    public static String getOperation() {
        return operation;
    }

    public static void setOperation(String operation) {
        Operations.operation = operation;
    }

    private static String operation;

    public static BigDecimal getFirstNumber() {
        return firstNumber;
    }

    public static void setFirstNumber(BigDecimal firstNumber) {
        Operations.firstNumber = firstNumber;
    }

    public static BigDecimal getSecondNumber() {
        return secondNumber;
    }

    public static void setSecondNumber(BigDecimal secondNumber) {
        Operations.secondNumber = secondNumber;
    }


    public static BigDecimal add(BigDecimal a, BigDecimal b) {
        a = a == null ? BigDecimal.ZERO : a;
        b = b == null ? BigDecimal.ZERO : b;
        result = a.add(b).round(MathContext.DECIMAL64);
        result = result.stripTrailingZeros().scale() > 0 ? result.stripTrailingZeros() : result;
        return result;
    }

    public static BigDecimal substract(BigDecimal a, BigDecimal b) {
        a = a == null ? BigDecimal.ZERO : a;
        b = b == null ? BigDecimal.ZERO : b;
        result = a.subtract(b).round(MathContext.DECIMAL64);
        result = result.stripTrailingZeros().scale() > 0 ? result.stripTrailingZeros() : result;
        return result;
    }

    public static BigDecimal multiply(BigDecimal a, BigDecimal b) {
        a = a == null ? BigDecimal.ZERO : a;
        b = b == null ? BigDecimal.ONE : b;
        result = a.multiply(b).round(MathContext.DECIMAL64);
        result = result.stripTrailingZeros().scale() > 0 ? result.stripTrailingZeros() : result;
        return result;
    }

    public static BigDecimal divide(BigDecimal a, BigDecimal b) {
        a = a == null ? BigDecimal.ZERO : a;
        result = a.divide(b,5,BigDecimal.ROUND_CEILING);
        result = result.stripTrailingZeros().scale() >= 0 ? result.stripTrailingZeros() : result;
        return result;
    }

    public static BigDecimal factorial(BigDecimal a) {
        if (a.compareTo(new BigDecimal("100000")) > 0) {
            return new BigDecimal("2137");
        }
        a = a == null ? BigDecimal.ONE : a;
        BigDecimal result = BigDecimal.ONE;
        for (BigDecimal bd = BigDecimal.ONE; bd.compareTo(a) <= 0; bd = bd.add(BigDecimal.ONE)) {
            result = result.multiply(bd);
        }
        firstNumber = result.round(MathContext.DECIMAL64);
        return firstNumber;
    }

    public static BigDecimal negate(BigDecimal a) {
        a = a == null ? BigDecimal.ZERO : a;
        firstNumber = a.negate().round(MathContext.DECIMAL64);
        return firstNumber;
    }

    public static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
