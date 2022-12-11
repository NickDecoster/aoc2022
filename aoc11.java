import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

public class aoc11 {
    public static void main(String[] args) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\Gebruiker\\git\\aoc2022\\sample.txt"));
            List<Monkey> monkeys = new ArrayList<>();
            while (true) {
                int monkeyNumber = Integer.parseInt(reader.readLine().split("[ :]")[1]);
                List<MonkeyItem> items = new ArrayList<>(Arrays.stream(reader.readLine().substring(18).split(", ")).map(c -> new MonkeyItem(Integer.parseInt(c))).toList());
                System.out.println(items);
                String operation = reader.readLine().substring(19);
                int testValue = Integer.parseInt(reader.readLine().substring(21));
                int targetTrue = Integer.parseInt(reader.readLine().substring(29));
                int targetFalse = Integer.parseInt(reader.readLine().substring(30));
                monkeys.add(new Monkey(monkeyNumber, items, operation, testValue, targetTrue, targetFalse));
                String line = reader.readLine();
                if(line == null){
                    break;
                }

            }
            int numberOfRounds = 999;
            for(int i = 0; i < numberOfRounds; i++){
                for(Monkey monkey : monkeys){
                    for(MonkeyItem item : monkey.getItems()){
                        int target = monkey.decideTarget(item);
                        monkey.throwItem(item);
                        monkeys.get(target).catchItem(item);
                    }
                }
            }
            int result = monkeys.stream().map(Monkey::getInspectCount).sorted(Comparator.reverseOrder()).limit(2).reduce(1, (a, b) -> a*b);
            System.out.println(monkeys.stream().map(Monkey::getInspectCount).sorted(Comparator.reverseOrder()).toList().toString());
            reader.close();
            System.out.println(result);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    public static void printMonkeys(List<Monkey> monkeys){
//        String output = "\n";
//        for(Monkey monkey : monkeys){
//            System.out.print(monkey.printMonkey());
//        }
//        System.out.print(output);
//
//    }
}

class Monkey{
    private int monkey;
    private List<MonkeyItem> items;
    private String operation;
    private int testValue;
    private int targetTrue;
    private int targetFalse;
    private int inspectCount = 0;

    public Monkey(int monkey, List<MonkeyItem> items, String operation, int testValue, int targetTrue, int targetFalse) {
        this.monkey = monkey;
        this.items = items;
        this.operation = operation;
        this.testValue = testValue;
        this.targetTrue = targetTrue;
        this.targetFalse = targetFalse;

    }

    public List<MonkeyItem> getItems() {
        return items;
    }

    public int getInspectCount() {
        return inspectCount;
    }

    public long executeOperation(MonkeyItem oldWorry){
        String[] opParts = this.operation.split(" ");
        BigInteger secondPart;
        if(Objects.equals(opParts[2], "old")){
            secondPart = BigInteger.valueOf(oldWorry.getWorry());
        }else{
            secondPart = BigInteger.valueOf(Long.parseLong(opParts[2]));
        }
        if(opParts[1].equals("+")){
            return secondPart.add(BigInteger.valueOf(oldWorry.getWorry())).longValue();
        }else{
            BigInteger moduloMagic = BigInteger.valueOf(9699690);
            long modMagic = 9699690;
            System.out.println(modMagic*modMagic%9699692);
            return secondPart.multiply(BigInteger.valueOf(oldWorry.getWorry())).mod(moduloMagic).longValue();
        }
    }

    public int decideTarget(MonkeyItem worry){
        inspectCount += 1;
        worry.setWorry(executeOperation(worry));
        boolean testResult = worry.getWorry() % this.testValue == 0;
        if(testResult){
            return this.targetTrue;
        }
        return this.targetFalse;
    }

    public void catchItem(MonkeyItem worry){
        this.items.add(worry);
    }

    public void throwItem(MonkeyItem worry){
        this.items = new ArrayList<>(this.items.stream().filter(c -> c.getWorry() !=worry.getWorry()).toList());
    }

//    public String printMonkey(){
//        return Integer.valueOf(this.monkey) + printItems();
//    }
//
//    public String printItems(){
//        String output = "";
//        for(MonkeyItem item : this.items){
//            output += Integer.valueOf(item.getWorry()) + " ";
//        }
//        return output;
//    }

}

class MonkeyItem{
    private long worry;
    public MonkeyItem(int worry){
        this.worry = worry;
    }

    public long getWorry() {
        return worry;
    }

    public void setWorry(long worry) {
        this.worry = worry;
    }
}