package lab8_map;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class MyWordCountApp {
    // public static final String fileName = "data/hamlet.txt";
    public static final String fileName = "data/fit.txt";
    // <word, its occurences>
    private Map<String, Integer> map = new HashMap<String, Integer>();

    // Load data from fileName into the above map (containing <word, its occurences>)
    // using the guide given in TestReadFile.java
    public void loadData() throws FileNotFoundException {
        Scanner input = new Scanner(new File(fileName));

        while (input.hasNext()) {
            String word = input.next();
            map.put(word, map.getOrDefault(word, 0)+1);
        }

    }

    // Returns the number of unique tokens in the file data/hamlet.txt or fit.txt
    public int countUnique() {
        int count = 0;
        Set<String> keySet = map.keySet();
        Iterator<String> iter = keySet.iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            if (map.get(key) == 1) {
                count++;
            }
        }
        return count;
    }

    // Prints out the number of times each unique token appears in the file
    // data/hamlet.txt (or fit.txt)
    // In this method, we do not consider the order of tokens.
    public void printWordCounts() throws FileNotFoundException {
        Collection<Integer> value = map.values();
        System.out.println(value.toString());
    }

    // Prints out the number of times each unique token appears in the file
    // data/hamlet.txt (or fit.txt) according to ascending order of tokens
    // Example: An - 3, Bug - 10, ...
    public void printWordCountsAlphabet() {
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        treeMap.putAll(map);
        System.out.println(treeMap.toString());
    }

    public static void main(String[] args) throws FileNotFoundException{
        MyWordCountApp app = new MyWordCountApp();
        app.loadData();
        System.out.println(app.map.toString());
        app.printWordCountsAlphabet();
        app.printWordCounts();
        System.out.println(app.countUnique());

    }
}
