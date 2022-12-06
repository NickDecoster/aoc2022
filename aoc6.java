import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class aoc6 {

    public static void main(String[] args) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("/home/nick/IdeaProjects/aoc2022/sample6.txt"));
            String line = reader.readLine();
            int part1 = solution(line, 4);
            int part2 = solution(line, 14);
            System.out.printf("part1:%s\npart2:%s", part1, part2);
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int solution(String line, int messageSize){
        for(int i = messageSize; i < line.length(); i++){
            if(line.substring(i-messageSize, i).chars().distinct().toArray().length == messageSize){
                return i;
            }
        }
        return -1;
    }
}
