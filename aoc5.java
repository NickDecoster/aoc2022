import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class aoc5 {

    public static void main(String[] args) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\Gebruiker\\git\\aoc2022\\sample5.txt"));
            String line = reader.readLine();
            List<List<Character>> lines = new ArrayList<>();
            while(!line.equals("")){
                List<Character> processedLine = line.chars().mapToObj(c -> (char) c).toList();
                lines.add(processedLine);
                line = reader.readLine();
            }
            int numberOfStacks = (lines.get(0).size()+1)/4;
            String[] crates= new String[numberOfStacks];

            for(List<Character> charLine : lines){
                for (int i = 0; i < numberOfStacks; i++){
                    crates[i] += (charLine.get(1 + 4 * i));
                }
            }

            for(int j = 0; j < numberOfStacks; j++){
                crates[j] = new StringBuilder(crates[j].substring(0, crates[j].length() - 1).replaceAll("null", "").replaceAll(" ", "")).reverse().toString();
            }

            line = reader.readLine();
            while(line != null){
                System.out.println(Arrays.toString(crates));
                List<Integer> instruction = Arrays.stream(line.split("move|from|to| ")).filter(k -> !Objects.equals(k, "")).map(Integer::parseInt).toList();
                int from = instruction.get(1)-1;
                int to = instruction.get(2) - 1;
                if(from == -1 || to == -1){
                    System.out.println("wrong");
                }
                StringBuilder cratesToMove = new StringBuilder(crates[from].substring(crates[from].length()-instruction.get(0)));
                crates[from] = crates[from].substring(0, crates[from].length()-instruction.get(0));
                //crates[to] += cratesToMove.reverse().toString(); //part1
                crates[to] += cratesToMove.toString(); //part2
                line = reader.readLine();
            }
            StringBuilder output = new StringBuilder();
            for(int j = 0; j < numberOfStacks; j++){
                output.append(crates[j].substring(crates[j].length()-1));
            }

            System.out.println(output);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
