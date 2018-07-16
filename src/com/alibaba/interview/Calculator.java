package com.alibaba.interview;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Calculator {

    public Double doCalculate(String str) {
        str = CalculatorUtils.check(str);

        List<Double> nums = Arrays.stream(str.split("[^.0-9]"))
                .filter(item -> !item.equals(""))
                .map(Double::valueOf)
                .collect(Collectors.toList());

        String symbols = str.replaceAll("[.0-9]", "");

        return BigDecimal.valueOf(doCalculate(symbols, nums))
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public Double doCalculate(String symbols, List<Double> nums) {

        LinkedList<Character> symStack = new LinkedList<>();
        LinkedList<Double> numStack = new LinkedList<>();

        int i = 0;
        int j = 0;
        while (symStack.isEmpty() || !(symStack.getLast() == '=' && symbols.charAt(j) == '=')) {// 形如：
            // =8=
            // 则退出循环，结果为8
            if (symStack.isEmpty()) {
                symStack.add('=');
                numStack.add(nums.get(i++));
            }
            if (CalculatorUtils.symbolMap.get(symbols.charAt(j)) > CalculatorUtils.symbolMap.get(symStack.getLast())) {// 比较符号优先级，若当前符号优先级大于前一个则压栈
                if (symbols.charAt(j) == '(') {
                    symStack.add(symbols.charAt(j++));
                    continue;
                }
                numStack.add(nums.get(i++));
                symStack.add(symbols.charAt(j++));
            } else {// 当前符号优先级小于等于前一个 符号的优先级
                if (symbols.charAt(j) == ')' && symStack.getLast() == '(') {// 若（）之间没有符号，则“（”出栈
                    j++;
                    symStack.removeLast();
                    continue;
                }
                if (symStack.getLast() == '(') {// “（”直接压栈
                    numStack.add(nums.get(i++));
                    symStack.add(symbols.charAt(j++));
                    continue;
                }

                this.calculate(symStack, numStack);
            }
        }
        return numStack.removeLast();
    }

    private void calculate(LinkedList<Character> symStack, LinkedList<Double> numStack) {
        double num2 = numStack.removeLast();
        double num1 = numStack.removeLast();
        switch (symStack.removeLast()) {
            case '+':
                numStack.add(CalculatorUtils.plus(num1, num2));
                break;
            case '-':
                numStack.add(CalculatorUtils.reduce(num1, num2));
                break;
            case '*':
                numStack.add(CalculatorUtils.multiply(num1, num2));
                break;
            case '/':
                numStack.add(CalculatorUtils.divide(num1, num2));
                break;
        }
    }

}
