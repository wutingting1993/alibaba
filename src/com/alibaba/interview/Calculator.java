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

		LinkedList<Character> symbolStack = new LinkedList<>();
		LinkedList<Double> numStack = new LinkedList<>();

		int i = 0;
		int j = 0;
		while (symbolStack.isEmpty() || !(symbolStack.getLast() == '=' && symbols.charAt(j) == '=')) {
			if (symbolStack.isEmpty()) {
				symbolStack.add('=');
				numStack.add(nums.get(i++));
			}
			if (CalculatorUtils.symbolMap.get(symbols.charAt(j)) > CalculatorUtils.symbolMap.get(
				symbolStack.getLast())) {
				if (symbols.charAt(j) == '(') {
					symbolStack.add(symbols.charAt(j++));
					continue;
				}
				numStack.add(nums.get(i++));
				symbolStack.add(symbols.charAt(j++));
			} else {
				if (symbols.charAt(j) == ')' && symbolStack.getLast() == '(') {
					j++;
					symbolStack.removeLast();
					continue;
				}
				if (symbolStack.getLast() == '(') {
					numStack.add(nums.get(i++));
					symbolStack.add(symbols.charAt(j++));
					continue;
				}

				this.calculate(symbolStack, numStack);
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
