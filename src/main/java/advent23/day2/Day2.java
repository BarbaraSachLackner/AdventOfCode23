package advent23.day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day2 {

    enum Colour {
        RED("red", 12),
        GREEN("green", 13),
        BLUE("blue", 14);

        final String colour;
        private final int maxCubes;

        Colour(String color, int maxCubes) {
            this.colour = color;
            this.maxCubes = maxCubes;
        }
    }

    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Paths.get("src", "main", "resources", "Day2_input.txt"));
        Set<Integer> possibleGames = new HashSet<>();
        for (String line : input) {
            String[] fullLine = line.split(":");
            int gameId = Integer.parseInt(fullLine[0].substring("Game ".length()));
            String[] sets = fullLine[1].split(";");
            for (String set : sets) {
                String[] splitWord = set.replace(",", "").trim().split("\\s+");
                for (int i = 0; i < splitWord.length - 1; i = i + 2) {
                    int totalCubes = Integer.parseInt(splitWord[i]);
                    Colour colour = Colour.valueOf(splitWord[i + 1].toUpperCase());
                    if (colour.maxCubes < totalCubes) {
                        possibleGames.remove(gameId);
                        break;
                    } else {
                        possibleGames.add(gameId);
                    }
                }
                if (!possibleGames.contains(gameId)) {
                    break;
                }
            }

        }
        Integer gameIdSum = possibleGames.stream().reduce(0, Integer::sum);
        System.out.println(gameIdSum);
    }

}