package stack;

import java.util.Stack;

/**
 * Hard
 * 
 * Implement a basic calculator to evaluate a simple expression string.
 * 
 * The expression string may contain open ( and closing parentheses ), the plus
 * + or minus sign -, non-negative integers and empty spaces .
 * 
 * You may assume that the given expression is always valid.
 * 
 * Some examples: "1 + 1" = 2 " 2-1 + 2 " = 3 "(1+(4+5+2)-3)+(6+8)" = 23 Note:
 * Do not use the eval built-in library function.
 *
 * Attempted: 4
 *
 * Percentile: 11.7%
 *
 * Process string from right to left. Take care of numbers >= 10
 */
public class BasicCalculator {

	public int calculate(String s) {
		if (s == null || s.trim().length() == 0) {
			return 0;
		}
		int len = s.length();
		Stack<Integer> numStack = new Stack<>();
		Stack<Character> opStack = new Stack<>();
		for (int i = len - 1; i >= 0; i--) {
			char c = s.charAt(i);
			if ('0' <= c && c <= '9') {
				Stack<Integer> num = new Stack<>();
				num.push(c - '0');
				while (i - 1 >= 0 && '0' <= s.charAt(i - 1) && s.charAt(i - 1) <= '9') {
					i--;
					num.push(s.charAt(i) - '0');
				}
				int sum = 0;
				while (!num.isEmpty()) {
					sum = sum * 10 + num.pop();
				}
				numStack.push(sum);
			} else if (c == '+' || c == '-' || c == ')') {
				opStack.push(c);
			} else if (c == '(') {
				while (opStack.peek() != ')') {
					int a = numStack.pop();
					int b = numStack.pop();
					char op = opStack.pop();
					if (op == '+') {
						numStack.push(a + b);
					} else if (op == '-') {
						numStack.push(a - b);
					}
				}
				opStack.pop();
			}
		}
		while (!opStack.isEmpty()) {
			int a = numStack.pop();
			int b = numStack.pop();
			char op = opStack.pop();
			if (op == '+') {
				numStack.push(a + b);
			} else if (op == '-') {
				numStack.push(a - b);
			}
		}
		return numStack.pop();
	}

	public static void main(String[] args) {
		BasicCalculator obj = new BasicCalculator();
		String s = "(1+(4+5+2)-3)+(6+8)";
		System.out.println(obj.calculate(s));

	}

}
