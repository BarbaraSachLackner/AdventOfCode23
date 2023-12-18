package org.lecture.day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day4Part1 {

    public static void main(String[] args) throws IOException {

        List<String> input = Files.readAllLines(Paths.get("src", "main", "resources", "Day4_input.txt"));
        List<Set<Integer>> winningNumbers = new ArrayList<>();

        for (String line : input) {
            Set<Integer> winningNumberSet = new HashSet<>();
            Set<Integer> scratchCardNumbersSet = new HashSet<>();
            String[] split = line.split(":");
            String[] winningTokens = split[1].split(" | ");
            boolean foundSeparator = false;
            for (String s : winningTokens) {
                if (s.equals("|")) {
                    foundSeparator = true;
                    continue;
                }
                if (!s.isEmpty()) {
                    if (foundSeparator) {
                        scratchCardNumbersSet.add(Integer.parseInt(s));
                    } else {
                        winningNumberSet.add(Integer.parseInt(s));
                    }
                }
            }
            Set<Integer> result = new HashSet<>(scratchCardNumbersSet);
            result.retainAll(winningNumberSet);
            if (!result.isEmpty()) {
                winningNumbers.add(result);
            }
        }
        winningNumbers.forEach(System.out::println);
        int result = 0;
        for (Set<Integer> set : winningNumbers) {
            result += doubleNumbers(set.size());
        }
        System.out.println(result);
    }


    private static int doubleNumbers(int n) {
        int result = 1;
        if (n == 1) {
            result = 1;
        } else if (n == 2) {
            result = 2;
        } else {
            for (int i = 1; i <= n; i++) {
                result *= 2;
            }
            result = result / 2;
        }

        return result;
    }
}
