import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class aoc8 {
    public static void main(String[] args) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\Gebruiker\\git\\aoc2022\\sample.txt"));
            List<List<Integer>> forest = new ArrayList<>();
            String line = reader.readLine();
            while(line != null) {
                String[] arr = new String[] {line};
                System.out.println(arr[0]);
                forest.add(Arrays.stream(arr).map(Integer::parseInt).toList());
                line = reader.readLine();
            }
            System.out.println(forest.toString());
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
