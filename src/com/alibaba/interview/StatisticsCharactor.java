package com.alibaba.interview;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StatisticsCharactor {


    public static long count(String fileName, String charValue) {
        StringBuilder builder = new StringBuilder();
        try (BigFileReader reader = new BigFileReader(fileName, 2000000)) {
            while (reader.readBytes() != -1) {
                builder.append(new String(reader.getBytes(), "UTF-8"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Pattern pattern = Pattern.compile("\\W" + charValue + "\\W");
        Matcher matcher = pattern.matcher(builder.toString());
        int count = 0;
        while (matcher.find()) {
            count++;
        }

        return count;
    }

    public static long count2(String fileName, String charValue) {
        Pattern pattern = Pattern.compile("\\W" + charValue + "\\W");
        long count = 0L;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            count = reader.lines().map(line -> {
                Matcher matcher = pattern.matcher(line);
                int i = 0;
                while (matcher.find()) {
                    i++;
                }
                return i;
            }).reduce((a, b) -> a + b).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }
}
