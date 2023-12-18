package org.lecture.day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;

public class Day1 {

    public static void main(String[] args) throws IOException {
        var input = Files.readAllLines(Paths.get("src", "main", "resources", "Day1_input.txt"));

        long sum = 0;
        for (String line : input) {
            var pattern = Pattern.compile("\\d");
            var matcher = pattern.matcher(line);
            StringBuilder s = new StringBuilder();
            while(matcher.find())  {
               s.append(matcher.group());
            }
            var numbers = s.toString();
            sum += Long.parseLong(String.valueOf(numbers.charAt(0)) + numbers.charAt(s.length() - 1));
        }

        System.out.println("Sum: " + sum);
    }
}