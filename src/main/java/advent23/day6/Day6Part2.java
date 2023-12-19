package advent23.day6;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day6Part2 {

    private static final int SPEED_PER_MILLISECOND = 1;

    public static void main(String[] args) throws IOException {
        List<String> input = Files.readAllLines(Paths.get("src", "main", "resources", "Day6_input.txt"));


        Race entry = null;
        Race withTime = null;
        for (String line : input) {
            String[] split = line.split("\\s+");
            if (split[0].equals("Time:")) {
                withTime = getTime(split);
            } else if (split[0].equals("Distance:")) {
                entry = getDistance(split, withTime);
            }
        }

        assert entry != null;
        BigInteger farthestDistance = BigInteger.valueOf(entry.distance());
        BigInteger totalRaceTime = BigInteger.valueOf(entry.time());
        Map<BigInteger, Race> travelledPerMS = new HashMap<>();
        for (BigInteger ms = BigInteger.valueOf(13); ms.compareTo(totalRaceTime) < 0; ms = ms.add(BigInteger.ONE)) {
            BigInteger travellingMilliSeconds = totalRaceTime.subtract(ms);
            BigInteger travelledDistance = travellingMilliSeconds.multiply(ms.multiply(BigInteger.valueOf(SPEED_PER_MILLISECOND)));
            System.out.println(travelledDistance);
            if (travelledDistance.compareTo(farthestDistance) > 0) {
                travelledPerMS.put(ms, entry);
            }
        }


        System.out.println(travelledPerMS.size());  //28101347
    }


    private static Race getTime(String[] split) {
        List<String> input = new ArrayList<>();
        for (String s : split) {
            if (!s.trim().isEmpty()) {
                input.add(s);
            }
        }

        StringBuilder timeKerning = new StringBuilder();
        String distanceKerning = "-1";
        for (String s : input) {
            String trimmed = s.trim();
            if (!trimmed.isEmpty()) {
                if (trimmed.equals("Time:")) {
                    continue;
                }
                timeKerning.append(trimmed);
            }
        }
        return new Race(Long.parseLong(timeKerning.toString()), Long.parseLong(distanceKerning));
    }

    private static Race getDistance(String[] split, Race race) {
        List<String> input = new ArrayList<>();
        for (String s : split) {
            if (!s.trim().isEmpty()) {
                input.add(s);
            }
        }

        StringBuilder distanceKerning = new StringBuilder();
        boolean isTime = false;
        for (String s : input) {
            String trimmed = s.trim();
            if (!trimmed.isEmpty()) {
                if (trimmed.equals("Time:")) {
                    isTime = true;
                    break;
                } else if (trimmed.equals("Distance:")) {
                    continue;
                }
                distanceKerning.append(trimmed);
            }

        }
        if (!isTime) {
            return new Race(race.time(), Long.parseLong(distanceKerning.toString()));
        }

        return race;
    }
}

