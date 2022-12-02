import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class aoc1 {
    public static void main(String[] args) {
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader("/home/nick/skryv/projects/aoc1/src/sample1.txt"));
            String line = reader.readLine();

            int currentElfCalories = 0;
            List<Integer> Calories = new ArrayList<>();
            while (line != null) {
                if(line.isEmpty()){
                    Calories.add(currentElfCalories);
                    currentElfCalories = 0;
                }else{
                    currentElfCalories += Integer.parseInt(line);
                }
                line = reader.readLine();
            }

            Calories.add(currentElfCalories);
            Collections.sort(Calories);
            Collections.reverse(Calories);
            System.out.println(Calories.get(0) + Calories.get(1) + Calories.get(2)); //only get(0) for first part
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

//math.max(a, b) ipv if(a > b)
