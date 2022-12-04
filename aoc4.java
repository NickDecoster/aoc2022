import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class aoc4 {

    public static void main(String[] args) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\Gebruiker\\git\\aoc2022\\sample4.txt"));
            String line = reader.readLine();
            int fullPairs = 0;
            while (line != null) {
                fullPairs += part2(line);
                //fullPairs += part1(line);
                line = reader.readLine();
            }
            reader.close();
            System.out.println(fullPairs);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int part1(String line) {
        List<Integer> sections = Arrays.stream(line.split("[,-]")).map(Integer::parseInt).toList();
        if (sections.get(0) <= sections.get(2) && sections.get(1) >= sections.get(3)) {
            return 1;
        }
        if (sections.get(0) >= sections.get(2) && sections.get(1) <= sections.get(3)) {
            return 1;
        }
        return 0;
    }

    public static int part2(String line) {
        List<Integer> sections = Arrays.stream(line.split("[,-]")).map(Integer::parseInt).toList();
        if (sections.get(0) <= sections.get(2) && sections.get(1) >= sections.get(2)) {
            return 1;
        }
        if (sections.get(0) <= sections.get(3) && sections.get(1) >= sections.get(3)) {
            return 1;
        }
        if (sections.get(0) >= sections.get(2) && sections.get(1) <= sections.get(3)) {
            return 1;
        }
        return 0;
    }
}