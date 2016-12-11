package es.baki.dsp2;

import java.util.Scanner;

public class LispInterp {

	static Stack stack = new Stack();

	public static void main(String... strings) {
		System.out.print("Enter Expression: ");
		Scanner scan = new Scanner(System.in);
		String expr = scan.nextLine();
		scan.close();
		// (+ (- 6) (* 2 3 4) (/ (+ 3) (*) (- 2 3 1)))
		// 16.5

		String[] split = expr.split(" ");

		for (int x = 0; x < split.length; x++) {
			if (split[x].startsWith("(")) {
				stack.push("(");
				if (split[x].substring(1).length() == 1)
					stack.push(split[x].substring(1));
				else {
					stack.push(split[x].substring(1, 2));
					stack.push(split[x].substring(2));
					reduce();
				}
				continue;
			}
			;
			if (split[x].contains(")")) {
				stack.push(split[x].substring(0, split[x].indexOf(')')));
				split[x] = split[x].substring(split[x].indexOf(')'));
				for (int y = 0; y < split[x].length(); y++) {
					stack.push(")");
					reduce();
				}
			} else
				stack.push(split[x]);

		}

		System.out.println(stack);

	}

	private static void reduce() {
		Stack numStack = new Stack();
		if (stack.peek().equals(")"))
			stack.pop();
		while (!stack.isEmpty() && (stack.peek().toString().charAt(0) != '('))
			numStack.push(stack.pop());
		stack.pop();

		char oper = numStack.pop().toString().charAt(0);

		if (numStack.isEmpty()) {
			stack.push("1.0");
			return;
		}

		double ans;
		ans = Double.parseDouble(numStack.pop().toString());
		switch (oper) {
		case '+':
			while (!numStack.isEmpty())
				ans += Double.parseDouble(numStack.pop().toString());
			break;
		case '-':
			if (numStack.isEmpty())
				ans *= -1;
			while (!numStack.isEmpty())
				ans -= Double.parseDouble(numStack.pop().toString());
			break;
		case '*':
			while (!numStack.isEmpty())
				ans *= Double.parseDouble(numStack.pop().toString());
			break;
		case '/':
			while (!numStack.isEmpty())
				ans /= Double.parseDouble(numStack.pop().toString());
			break;
		}
		stack.push(ans + "");
	}
}