import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class aoc2_1 {
    public static void main(String[] args) {
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\Gebruiker\\git\\aoc2022\\sample2.txt"));
            String line = reader.readLine();
            int score = 0;
            while (line != null) {
                List<Integer> players = Arrays.stream(line.split(" ")).map(input -> mapStringToInt(input)).collect(Collectors.toList());
                int outcome = ((players.get(0) - players.get(1))%3 + 3)%3;
                //(a % b + b) % b
                System.out.println(outcome);
                score += players.get(1);
                if(outcome == 0){
                    score += 3;
                }else if(outcome == 1){
                    score += 0;
                }else if(outcome == 2){
                    score += 6;
                }
                line = reader.readLine();
            }
            reader.close();
            System.out.println(score);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Integer mapStringToInt(String input){
        if(Objects.equals(input, "A") || Objects.equals(input, "X")){ return 1;}
        if(Objects.equals(input, "B") || Objects.equals(input, "Y")){ return 2;}
        if(Objects.equals(input, "C") || Objects.equals(input, "Z")){ return 3;}
        return 0;
    }
}

