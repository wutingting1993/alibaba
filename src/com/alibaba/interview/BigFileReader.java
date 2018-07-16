package com.alibaba.interview;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Objects;

public class BigFileReader implements AutoCloseable {

	private static final int defaultReadSize = Integer.MAX_VALUE;
	private static final int defaultByteSize = 1024;

	private MappedByteBuffer buffer[];
	private FileInputStream inputStream;
	private Long length;

	private byte[] bytes;
	private int splitNum;
	private int bufferIndex = 0;

	public BigFileReader(String name) throws Exception {
		this(name, defaultReadSize);
	}

	public BigFileReader(String name, long readSize) throws Exception {

		if (readSize <= 0) {
			throw new RuntimeException("read size cannot less than 0");
		}
		if (readSize > Integer.MAX_VALUE) {
			throw new RuntimeException("read size cannot more than " + Integer.MAX_VALUE);
		}

		inputStream = new FileInputStream(name);
		FileChannel channel = inputStream.getChannel();
		length = channel.size();
		splitNum = (int)Math.ceil((double)length / readSize);
		buffer = new MappedByteBuffer[splitNum];
		long readLen = 0;
		for (int i = 0; i < splitNum; i++) {
			if (length - readLen < Integer.MAX_VALUE) {
				readSize = length - readLen;
			}
			buffer[i] = channel.map(FileChannel.MapMode.READ_ONLY, readLen, readSize);
			readLen += readSize;
		}

	}

	public int readBytes() {
		return readBytes(defaultByteSize);
	}

	public int readBytes(int byteSize) {
		if (bufferIndex >= splitNum) {
			return -1;
		}
		if (byteSize <= 0) {
			throw new RuntimeException("read size cannot less than 0");
		}
		int limit = buffer[bufferIndex].limit();
		int position = buffer[bufferIndex].position();
		if (limit - position > byteSize) {
			bytes = new byte[byteSize];
			buffer[bufferIndex].get(bytes);
			return byteSize;
		} else {
			bytes = new byte[limit - position];
			buffer[bufferIndex].get(bytes);
			if (bufferIndex < splitNum) {
				bufferIndex++;
			}
			return limit - position;
		}
	}

	public Long getLength() {
		return length;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void close() throws IOException {
		if (Objects.nonNull(inputStream)) {
			inputStream.close();
			System.out.println("reader closed");
		}
		buffer = null;
	}
}
