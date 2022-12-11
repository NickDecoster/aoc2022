import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class aoc7 {

    public static void main(String[] args) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("C:\\Users\\Gebruiker\\git\\aoc2022\\sample7.txt"));
            String line = reader.readLine();
            Directory topLeveldirectory = new Directory("/", null);
            Directory currentDirectory = topLeveldirectory;
            while (line != null) {
                String[] lineParts = line.split(" ");
                if(lineParts[0].equals("$")){
                    if(lineParts[1].equals("cd")){
                        if(lineParts[2].equals("..")){
                            currentDirectory = currentDirectory.parent;
                        }else if(lineParts[2].equals("/")){
                            currentDirectory = topLeveldirectory;
                        }else{
                            currentDirectory = (Directory) currentDirectory.retrieveContent(lineParts[2]);
                        }
                        line = reader.readLine();
                    }else if(lineParts[1].equals("ls")){
                        line = reader.readLine();
                        while (line != null && !line.startsWith("$")){
                            lineParts = line.split(" ");
                            if(lineParts[0].equals("dir")){
                                currentDirectory.addContent(new Directory(lineParts[1], currentDirectory));
                            }else{
                                currentDirectory.addContent(new File(Integer.parseInt(lineParts[0]), lineParts[1], currentDirectory));
                            }
                            line = reader.readLine();
                        }
                    }
                }
            }

            int unusedSpace = 70000000 - topLeveldirectory.getSize();
            int spaceNeeded = 30000000 - unusedSpace;
            System.out.println(topLeveldirectory.getSolutionPart2(spaceNeeded, 70000000));
            //System.out.println(topLeveldirectory.getSolutionPart1());
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

 abstract class Item{
    public String name;
    public Directory parent;
    public Item(String name, Directory parent){
        this.name = name;
        this.parent = parent;
    }
    abstract int getSize();

}

 class Directory extends Item{
    private List<Item> contents =  new ArrayList<Item>();
    public Directory(String name, Directory parent) {
        super(name, parent);
    }
     public void addContent(Item item){
        this.contents.add(item);
     }
     public Item retrieveContent(String name){
        return contents.stream().filter(c -> c.name.equals(name)).findFirst().get();
     }
     @Override
     int getSize() {
         int size = 0;
         for(Item item : contents){
             size += item.getSize();
         }
         return size;
     }
     int getSolutionPart1() {
        int size = this.getSize();
        if(size >= 100000){
            size = 0;
        }
        for(Item item : contents){
            if(item instanceof Directory) {
                size += ((Directory) item).getSolutionPart1();
            }
        }
        return size;
     }
     int getSolutionPart2(int spaceNeeded, int smallestOption){
         int sizeSmallestOption = smallestOption;
         for(Item item : contents){
             if(item instanceof Directory) {
                 int dirSize = item.getSize();
                 if(dirSize > spaceNeeded){
                     if(dirSize< sizeSmallestOption){
                         sizeSmallestOption = dirSize;
                     }
                     sizeSmallestOption = ((Directory) item).getSolutionPart2(spaceNeeded, sizeSmallestOption);
                 }
             }
         }
         return sizeSmallestOption;
     }
 }

 class File extends Item{
    private final int size;
    public File(int size, String name, Directory parent){
        super(name, parent);
        this.size = size;

    }
    @Override
    int getSize() {
        return size;
    }
}
