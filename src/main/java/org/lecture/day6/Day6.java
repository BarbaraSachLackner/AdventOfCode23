package org.lecture.day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day6 {

    private static int SPEED_PER_MILLISECOND = 1;

    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Paths.get("src", "main", "resources", "Day6_input.txt"));
        Map<Integer, Integer> travelledPerMilliseconds = new HashMap<>();
        Map<Integer, Race> races = new HashMap<>();
        for (String line : input) {
            String[] split = line.split("\\s+");
            createRaces(split, races);
        }
        Map<Integer, Map<Integer, Race>> wonRaces = new HashMap<>();
        for (var entry : races.entrySet()) {
            var farthestDistance = entry.getValue().distance();
            int totalRaceTime = entry.getValue().time();
            for (int seconds = 1; seconds< totalRaceTime; seconds++ ) {
                int travelledDistance = (totalRaceTime-seconds) * seconds*SPEED_PER_MILLISECOND;
                if (travelledDistance > farthestDistance) {
                    Map<Integer, Race> travelledPerSeconds = wonRaces.getOrDefault(entry.getKey(), new HashMap<>());
                    travelledPerSeconds.put(seconds, entry.getValue());
                    wonRaces.put(entry.getKey(), travelledPerSeconds);
                }
            }
        }

        int sumOfRaces = 1;
        for(var value : wonRaces.values()) {
            sumOfRaces *= value.size();
        }
        System.out.println();
        System.out.println(sumOfRaces);

    }



    private static void createRaces(String[] split, Map<Integer, Race> races) {


        List<String> input = new ArrayList<>();
        for (String s : split) {
            if (!s.trim().isEmpty()) {
                input.add(s);
            }
        }

        boolean distance = false;
        boolean time = false;
        int i = 1;
        for (String s : input) {
            String trimmed = s.trim();
            if (!trimmed.isEmpty()) {
                if (trimmed.equals("Time:")) {
                    time = true;
                    continue;
                } else if (trimmed.equals("Distance:")) {
                    distance = true;
                    time = false;
                    i = 1;
                    continue;
                }

                if (time) {
                    Race r = new Race(Integer.parseInt(trimmed), -1);
                    races.put(i, r);
                } else if (distance) {
                    Race race = races.get(i);
                    Race newRace = new Race(race.time(), Integer.parseInt(trimmed));
                    races.replace(i, newRace);
                }
                i++;
            }
        }
        races.forEach((k, v) -> System.out.printf("%s", v));

    }
}

