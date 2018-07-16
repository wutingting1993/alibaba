package com.alibaba.interview;

import java.util.EmptyStackException;
import java.util.List;
import java.util.Vector;

public class AntMinStack {

	private final List<Integer> stack = new Vector<>();

	private final List<Integer> minStack = new Vector<>();

	public synchronized Integer push(Integer item) {
		stack.add(item);

		if (minStack.isEmpty() || item <= minStack.get(minStack.size() - 1)) {
			minStack.add(item);
		} else {
			minStack.add(minStack.get(minStack.size() - 1));
		}

		return item;
	}

	public synchronized Integer pop() {
		if (stack.isEmpty()) {
			throw new EmptyStackException();
		}
		minStack.remove(minStack.size() - 1);
		return stack.remove(stack.size() - 1);
	}

	public Integer peek() {
		if (stack.isEmpty()) {
			throw new EmptyStackException();
		}
		return stack.get(stack.size() - 1);
	}

	public Integer min() {
		if (minStack.isEmpty()) {
			throw new EmptyStackException();
		}
		return minStack.get(stack.size() - 1);
	}

	public Integer size() {
		return stack.size();
	}

	public boolean isEmpty() {
		return stack.isEmpty();
	}

	@Override
	public String toString() {
		return stack.toString();
	}
}
