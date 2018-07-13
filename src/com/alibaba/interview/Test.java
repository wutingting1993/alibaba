package com.alibaba.interview;

public class Test {
	public static void main(String[] args) throws Exception {
		//		testAntMinStack();
		testBigFileReader();
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

	/**
	 * Test two.
	 */
	public static void testBigFileReader() throws Exception {
		long startTime = System.nanoTime();
		StringBuilder builder = new StringBuilder();
		try (BigFileReader reader = new BigFileReader(
			"src\\com\\alibaba\\interview\\AntMinStack.java")) {
			while (reader.readBytes() != -1) {
				builder.append(new String(reader.getBytes(), "UTF-8"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(builder.toString());
		System.out.println("spend time " + (System.nanoTime() - startTime));
	}

}
