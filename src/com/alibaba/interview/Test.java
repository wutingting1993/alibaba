package com.alibaba.interview;

public class Test {
	public static void main(String[] args) {
		testAntMinStack();
	}

	/**
	 * Test 0ne.
	 */
	public static void testAntMinStack() {

		System.out.println("AntMinStack test start......");
		System.out.println();

		AntMinStack antMinStack = new AntMinStack();
		antMinStack.push(100);
		antMinStack.push(200);
		antMinStack.push(99);
		antMinStack.push(250);
		antMinStack.push(800);

		System.out.println("stack after push:" + antMinStack);
		System.out.println("stack min value:" + antMinStack.min());
		System.out.println();

		System.out.println("stack pop " + antMinStack.pop());
		System.out.println("stack after pop:" + antMinStack);
		System.out.println("stack min value:" + antMinStack.min());
		System.out.println();

		System.out.println("stack pop " + antMinStack.pop());
		System.out.println("stack after pop:" + antMinStack);
		System.out.println("stack min value:" + antMinStack.min());
		System.out.println();

		System.out.println("stack pop " + antMinStack.pop());
		System.out.println("stack after pop:" + antMinStack);
		System.out.println("stack min value:" + antMinStack.min());
		System.out.println();

		System.out.println("AntMinStack test end......");

	}

}
