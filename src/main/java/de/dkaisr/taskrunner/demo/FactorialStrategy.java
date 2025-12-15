package de.dkaisr.taskrunner.demo;

import de.dkaisr.taskrunner.Strategy;

public class FactorialStrategy implements Strategy<Integer, Integer> {

    @Override
    public Integer execute(Integer data) {
        if (data < 0) {
            throw new ArithmeticException("Factorial is only defined for positive values");
        } else {
            return factorial(data);
        }
    }

    private Integer factorial(Integer n) {
        int fact = 1;
        for (int i = 2; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }
}
