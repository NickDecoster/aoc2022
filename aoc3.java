import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class aoc3 {
    public static void main(String[] args) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\Gebruiker\\git\\aoc2022\\sample3.txt"));
            int prioritySum = 0;
            while (true) {
                String line1 = reader.readLine();
                String line2 = reader.readLine();//comment for part1
                String line3 = reader.readLine();//comment for part1
                if(line1 == null){
                    break;
                } else{
                    //prioritySum += part1(line1);
                    prioritySum += part2(line1, line2, line3);
                }
            }
            reader.close();
            System.out.println(prioritySum);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int part1(String line){
        String backpack1 = line.substring(0, line.length()/2);
        String backpack2 = line.substring(line.length()/2);
        for (int i = 0; i < line.length()/2; i++){
            char item = backpack1.charAt(i);
            int index = backpack2.indexOf(item);
            if(index != -1){
                return charToInt(item);
            }
        }
        return 100000000;
    }

    public static int part2(String line1, String line2, String line3){
        int lengthA = line1.length();
        int lengthB = line2.length();
        int lengthC = line3.length();
        if( lengthA >= lengthB && lengthA >= lengthC){
            return findItem(line1, line2, line3);
        }else if(lengthB >= lengthA && lengthB >= lengthC){
            return findItem(line2, line1, line3);
        }else{
            return findItem(line3, line2, line1);
        }
    }

    public static int findItem(String shortest, String backpack1, String backpack2){
        for (int i = 0; i < shortest.length(); i++){
            char item = shortest.charAt(i);
            int index1 = backpack1.indexOf(item);
            int index2 = backpack2.indexOf(item);
            if(index1 != -1 && index2 != -1){
                return charToInt(item);
            }
        }
        return 1000000000;
    }

    public static int charToInt(char item){
        int itemPriority = Character.getNumericValue(item);
        if(Character.isLowerCase(item)){
            return itemPriority - 9;
        }
        if(Character.isUpperCase(item)){
            return itemPriority + 17;
        }
        return 10000000;
    }
}
