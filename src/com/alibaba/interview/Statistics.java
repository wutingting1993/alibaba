package com.alibaba.interview;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Statistics {

	private static ExecutorService executor;
	private boolean readFinish = false;
	private boolean finish = false;
	private int count = 0;
	private Stack<Item> stack = new Stack();
	private Map<String, Map<String, Item>> minMap = new ConcurrentHashMap<>();
	private CountDownLatch latch;
	private List<String> fileNames;

	public Statistics(String fileName, int threadCount) {
		this.getFileNames(fileName);
		this.init(threadCount);
	}

	private void init(int threadCount) {
		finish = false;
		readFinish = false;
		minMap.clear();
		latch = new CountDownLatch(fileNames.size());
		if (Objects.isNull(executor)) {
			Statistics.initPool(threadCount);
		}
	}

	private static synchronized void initPool(int threadCount) {
		if (Objects.isNull(executor)) {
			executor = Executors.newFixedThreadPool(threadCount);
		}
	}

	public void read() throws InterruptedException {
		if (fileNames.isEmpty()) {
			return;
		}
		for (int i = 0; i < fileNames.size(); i++) {
			String fileName = fileNames.get(i);
			executor.execute(new Thread(() -> {
				Closeable:
				try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
					reader.lines().forEach(line -> {
						String[] lineValues = line.split(",");
						if (lineValues.length == 3) {
							stack.push(new Item(lineValues[0].trim(), lineValues[1].trim(), lineValues[2].trim()));
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					latch.countDown();
					count();
				}
			}));
		}

		latch.await();

	}

	private void getFileNames(String fileName) {
		fileNames = new ArrayList<>();
		File file = new File(fileName);
		if (file.isDirectory()) {
			Arrays.stream(file.listFiles()).forEach(item -> {
				if (item.isDirectory()) {
					getFileNames(item.getPath());
				} else {
					fileNames.add(item.getPath());
				}
			});
		} else {
			fileNames.add(fileName);
		}
	}

	public void findMinQuota() {
		while (!readFinish || !stack.empty()) {
			if (stack.empty()) {
				continue;
			}
			Item item = stack.pop();
			Map<String, Item> min = minMap.get(item.getGroupId());
			if (Objects.isNull(min)) {
				min = new TreeMap<>();
				min.put(item.getId(), item);
				minMap.put(item.getGroupId(), min);
			} else {
				String quota = min.get(min.keySet().iterator().next()).getQuota();
				if (quota.compareTo(item.getQuota()) == 0) {
					min.put(item.getId(), item);
				} else if (quota.compareTo(item.getQuota()) > 0) {
					min.clear();
					min.put(item.getId(), item);
				}
			}
		}
		finish = true;
		executor.shutdown();
	}

	private synchronized void count() {
		count++;
		if (count >= fileNames.size()) {
			readFinish = true;
		}
	}

	public Map<String, Map<String, Item>> getMinMap() {
		return minMap;
	}

	public boolean isFinish() {
		return finish;
	}
}
