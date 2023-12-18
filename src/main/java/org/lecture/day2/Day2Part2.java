package org.lecture.day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day2Part2 {

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
        Map<Integer, Map<Colour, Integer>> minCubes = new HashMap<>();
        for (String line : input) {
            String[] fullLine = line.split(":");
            int gameId = Integer.parseInt(fullLine[0].substring("Game ".length()));
            String[] sets = fullLine[1].split(";");
            for (String set : sets) {
                var game = minCubes.getOrDefault(gameId, new HashMap<>());
                String[] gameSetSplit = set.replace(",", "").trim().split("\\s+");
                for (int i = 0; i < gameSetSplit.length - 1; i = i + 2) {
                    int totalCubes = Integer.parseInt(gameSetSplit[i]);
                    Colour colour = Colour.valueOf(gameSetSplit[i + 1].toUpperCase());
                    if (game.containsKey(colour)) {
                        if (game.get(colour) < totalCubes) {
                            game.put(colour, totalCubes);
                            minCubes.put(gameId, game);
                        }
                    } else {
                        game.put(colour, totalCubes);
                        minCubes.put(gameId, game);
                    }
                }
            }

        }

        long sum = 0;
        for (var game : minCubes.values()) {
            sum += game.values().stream().mapToLong(x->x).reduce(1, Math::multiplyExact);
        }
        System.out.println(sum);

    }

}