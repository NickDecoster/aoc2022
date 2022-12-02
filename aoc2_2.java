import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class aoc2_2 {
    public static void main(String[] args) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\Gebruiker\\git\\aoc2022\\sample2.txt"));
            String line = reader.readLine();
            int score = 0;
            while (line != null) {
                List<Integer> players = Arrays.stream(line.split(" ")).map(input -> mapStringToInt(input)).collect(Collectors.toList());
                int player = -1;
                if(players.get(1) == 0){
                    player = (2 + players.get(0))%3;
                    score += 0;
                } else if(players.get(1) == 1){
                    player = (players.get(0))%3;
                    score += 3;
                }else if(players.get(1) == 2){
                    player = (1 + players.get(0))%3;
                    score += 6;
                }
                score += player+1;
                line = reader.readLine();
            }
            reader.close();
            System.out.println(score);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Integer mapStringToInt(String input){
        if(Objects.equals(input, "A") || Objects.equals(input, "X")){ return 0;}
        if(Objects.equals(input, "B") || Objects.equals(input, "Y")){ return 1;}
        if(Objects.equals(input, "C") || Objects.equals(input, "Z")){ return 2;}
        return 0;
    }
}

