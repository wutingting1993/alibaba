package com.alibaba.interview;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class CalculatorUtils {
	public static final int FORMAT_MAX_LENGTH = 500;// 表达式最大长度限制
	public static final Map<Character, Integer> symLvMap = new HashMap<Character, Integer>();// 符号优先级map

	static {
		symLvMap.put('=', 0);
		symLvMap.put('-', 1);
		symLvMap.put('+', 1);
		symLvMap.put('*', 2);
		symLvMap.put('/', 2);
		symLvMap.put('(', 3);
		symLvMap.put(')', 1);
	}

	public static String check(String str) {
		if (null == str || "".equals(str)) {
			return null;
		}
		str = str.replaceAll(" ", "");
		if (str.length() > FORMAT_MAX_LENGTH) {
			str = null;
		} else {
			str = '-' == str.charAt(0) ? 0 + str : str;
			str = CalculatorUtils.checkFormat(str) ? str : null;
		}

		if (str == null) {
			throw new RuntimeException("表达式错误！");
		} else if ('=' != str.charAt(str.length() - 1)) {
			str += "=";
		}

		return CalculatorUtils.change2StandardFormat(str);

	}

	public static boolean checkFormat(String str) {
		if (!(Character.isDigit(str.charAt(0)) || str.charAt(0) == '(')) {
			return false;
		}

		for (int i = 1; i < str.length() - 1; i++) {
			char c = str.charAt(i);
			if (!validateChar(c)) {// 字符不合法
				return false;
			}
			if (!(Character.isDigit(c))) {
				if (c == '-' || c == '+' || c == '*' || c == '/') {
					if (c == '-' && str.charAt(i - 1) == '(') {// 1*(-2+3)的情况
						continue;
					}
					if (!(Character.isDigit(str.charAt(i - 1)) || str.charAt(i - 1) == ')')) {// 若符号前一个不是数字或者“）”时
						return false;
					}
				}
				if (c == '.') {
					if (!Character.isDigit(str.charAt(i - 1)) || !Character.isDigit(
						str.charAt(i + 1))) {// 校验“.”的前后是否位数字
						return false;
					}
				}
			}
		}
		return validateBracket(str);
	}

	public static String change2StandardFormat(String str) {
		StringBuilder sb = new StringBuilder();
		char c;
		for (int i = 0; i < str.length(); i++) {
			c = str.charAt(i);
			if (i != 0 && c == '(' && (Character.isDigit(str.charAt(i - 1)) || str.charAt(i - 1) == ')')) {
				sb.append("*(");
				continue;
			}
			if (c == '-' && str.charAt(i - 1) == '(') {
				sb.append("0-");
				continue;
			}
			sb.append(c);
		}
		return sb.toString();
	}

	public static boolean validateBracket(String str) {
		LinkedList<Character> stack = new LinkedList<>();
		for (char item : str.toCharArray()) {
			if (item == '(') {
				stack.add(item);
			} else if (item == ')') {
				if (stack.isEmpty()) {
					return false;
				}
				stack.removeLast();
			}
		}
		return stack.isEmpty();
	}

	public static boolean validateChar(Character c) {
		return Character.isDigit(c) || symLvMap.keySet().contains(c) || c == '.';
	}

	public static double plus(double num1, double num2) {
		return num1 + num2;
	}

	public static double reduce(double num1, double num2) {
		return num1 - num2;
	}

	public static double multiply(double num1, double num2) {
		return num1 * num2;

	}

	public static double divide(double num1, double num2) {
		return num1 / num2;
	}
}
