package advent23.day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day4Part2 {

    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Paths.get("src", "main", "resources", "Day4_input.txt"));
        Map<Integer, Set<Integer>> winningCards = new HashMap<>();
        Map<Integer, Integer> cardCopies = new HashMap<>();

        for (String line : input) {
            Set<Integer> winningNumberSet = new HashSet<>();
            Set<Integer> scratchCardNumbersSet = new HashSet<>();
            String[] split = line.split(":");
            String[] winningTokens = split[1].split(" | ");
            String[] cardToken = split[0].split("\\s+");
            Integer cardNumber = Integer.valueOf(cardToken[1]);
            boolean foundSeparator = false;
            for (String s : winningTokens) {
                if (s.equals("|")) {
                    foundSeparator = true;
                    continue;
                }
                if (!s.isEmpty()) {
                    if (foundSeparator) {
                        scratchCardNumbersSet.add(Integer.parseInt(s));
                        cardCopies.putIfAbsent(cardNumber, 1);
                    } else {
                        winningNumberSet.add(Integer.parseInt(s));
                    }
                }
            }
            Set<Integer> result = new HashSet<>(scratchCardNumbersSet);
            result.retainAll(winningNumberSet);
            if (!result.isEmpty()) {
                winningCards.put(cardNumber, result);
            }
        }

        for (var entry : winningCards.entrySet()) {
            Integer cardNumber = entry.getKey();
            for (int copies = 1; copies <= cardCopies.get(cardNumber); copies++) {
                int nextCard = cardNumber + 1;
                for (int i = 1; i <= entry.getValue().size(); i++, nextCard++) {
                    if (cardCopies.containsKey(nextCard)) {
                        Integer amountOfCopies = cardCopies.get(nextCard);
                        cardCopies.put(nextCard, ++amountOfCopies);
                    }
                }
            }
        }

        int result = 0;
        for (var values : cardCopies.values()) {
            result += values;
        }

        System.out.println(result);
    }


}
