package com.alibaba.interview;

public class Test {
	public static void main(String[] args) throws Exception {
		System.out.println("=================================");
		System.out.println("============Test 1===============");
		System.out.println("=================================");
		testAntMinStack();

		System.out.println("\n=================================");
		System.out.println("============Test 2===============");
		System.out.println("=================================");
		testStatisticsCharactor();

		System.out.println("\n=================================");
		System.out.println("============Test 3===============");
		System.out.println("=================================");
		testCalculator();

		System.out.println("\n=================================");
		System.out.println("============Test 4===============");
		System.out.println("=================================");
		testStatistics();
	}

	/**
	 * Test 0ne.
	 */
	public static void testAntMinStack() {
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

	}

	/**
	 * Test two.
	 */
	public static void testStatisticsCharactor() {
		long startTime = System.nanoTime();
		String charValue = "public";
		String property = System.getProperty("user.dir");
		System.out.println(charValue + " count: " + StatisticsCharactor.count2(property + "\\files\\bigFile.txt",
			charValue));
		System.out.println("spend time " + (System.nanoTime() - startTime));
	}

	/**
	 * Test 3.
	 */
	public static void testCalculator() {
		Calculator calc = new Calculator();
		String str = "2*(0-1+2)*(3+4)";
		System.out.println(calc.doCalculate(str));
	}

	/**
	 * Test 4.
	 */
	public static void testStatistics() throws InterruptedException {

		Statistics statistics = new Statistics(System.getProperty("user.dir") + "\\files\\test4", 10);
		new Thread(() -> statistics.findMinQuota()).start();
		statistics.read();
		while (true) {
			if (statistics.isFinish()) {
				statistics.getMinMap().forEach((key, value) -> value.values().forEach(item -> {
					System.out.println(item);
				}));
				break;
			}
		}

	}
}
