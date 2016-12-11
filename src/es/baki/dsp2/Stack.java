package es.baki.dsp2;

public class Stack {
	private Object[] stack;

	public Stack() {
		stack = new Object[0];
	}

	public void push(Object newEntry) {
		Object[] newStack = new Object[stack.length + 1];
		newStack[0] = newEntry;
		int x = 1;
		for (Object o : stack) {
			newStack[x] = o;
			x++;
		}

		this.stack = newStack;
	}

	public Object pop() {
		if (stack.length == 0)
			return null;
		Object toReturn = stack[0];
		Object[] newStack = new Object[stack.length - 1];
		for (int x = 1; x < stack.length; x++)
			newStack[x - 1] = stack[x];

		stack = newStack;
		return toReturn;
	}

	public Object peek() {
		if (stack.length == 0)
			return null;
		return stack[0];
	}

	public boolean isEmpty() {
		return stack.length == 0;
	}

	@Override
	public String toString() {
		if (isEmpty())
			return "";
		String s = "";

		for (int x = stack.length - 1; x >= 0; x--)
			s += stack[x] + ", ";
		return s.substring(0, s.length() - 2);
	}

	public int length() {
		return stack.length;
	}

	/**
	 * testing stack
	 *
	 * @param strings
	 */
	public static void main(String... strings) {
		String s1 = "1", s2 = "2", s3 = "3", s4 = "4", s5 = "5";
		Stack s = new Stack();

		s.push(s1);
		s.push(s2);
		s.push(s3);
		s.push(s4);
		s.push(s5);

		System.out.println(s);
		System.out.println(s.peek());
		System.out.println(s.pop());
		System.out.println(s.isEmpty());
		System.out.println(s);
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.pop());
		System.out.println(s.isEmpty());
	}
}
