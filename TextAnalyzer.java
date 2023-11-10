package lab8_map;

import java.io.*;
import java.util.*;

public class TextAnalyzer {
    // <word, its positions>
    private Map<String, ArrayList<Integer>> map = new HashMap<String, ArrayList<Integer>>();

    //	public static final String fileName = "data/short.txt";
    // tu o vi tri cuoi hang co vi tri la am
    // load words in the text file given by fileName and store into map by using add
    // method in Task 2.1.
    // Using BufferedReader reffered in file TextFileUtils.java
    public void load(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String line = null;
        int index = 1;
        while (true) {
            ArrayList<Integer> listIndex = new ArrayList<>();
            line = reader.readLine();
            if (line == null) break;
            StringTokenizer tokens = new StringTokenizer(line, " ");
            while (tokens.hasMoreTokens()) {
                String nextToken = tokens.nextToken();
                if (tokens.hasMoreTokens() == false) {
                    add(nextToken, -index);
                } else {
                    add(nextToken, index);
                }
                index++;
            }
        }
        reader.close();
    }

    // In the following method, if the word is not in the map, then adding that word
    // to the map containing the position of the word in the file. If the word is
    // already in the map, then its word position is added to the list of word
    // positions for this word.
    // Remember to negate the word position if the word is at the end of a line in
    // the text file

    public void add(String word, int position) {
        if (map.containsKey(word)) {
            map.get(word).add(position);
        } else {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(position);
            map.put(word, list);
        }
    }

    // This method should display the words of the text file along with the
    // positions of each word, one word per line, in alphabetical order
    public void displayWords() {
        TreeMap<String, ArrayList<Integer>> treeMap = new TreeMap<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        treeMap.putAll(map);
        Set<Map.Entry<String, ArrayList<Integer>>> entry = treeMap.entrySet();
//        System.out.println(Arrays.toString(entry.toArray()));
        Iterator<Map.Entry<String, ArrayList<Integer>>> iter = entry.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }

    // This method will display the content of the text file stored in the map
    public void displayText() {
        List<Integer> endRowIndex = new ArrayList<>();
        Set<String> keySet = map.keySet();
        int sizeArr = 0;
        Iterator<String> iter = keySet.iterator();
        while (iter.hasNext()) sizeArr += map.get(iter.next()).size();

        String[] words = new String[sizeArr];
        for (String key : keySet) {
            for (int i = 0; i < map.get(key).size(); i++) {
                if (map.get(key).get(i) < 0) endRowIndex.add(Math.abs(map.get(key).get(i) + 1));
                words[Math.abs(map.get(key).get(i)) - 1] = key;
            }
        }
        for (int i = 0; i < words.length; i++) {
            if (endRowIndex.contains(i)) {
                System.out.print(words[i] + " ");
                System.out.println();
            } else System.out.print(words[i] + " ");
        }
    }


    // This method will return the word that occurs most frequently in the text file
    public String mostFrequentWord() {
        Set<Map.Entry<String, ArrayList<Integer>>> entrySet = map.entrySet();
        Iterator<Map.Entry<String, ArrayList<Integer>>> iter = entrySet.iterator();
        String word = "";
        int max = 0;
        while (iter.hasNext()) {
            Map.Entry<String, ArrayList<Integer>> next = iter.next();
            if (next.getValue().size() > max) {
                max = next.getValue().size();
                word = next.getKey();
            }
        }
        return word;
    }

    public static void main(String[] args) throws IOException {
        String fileName = "data/short.txt";
        TextAnalyzer textAnalyzer = new TextAnalyzer();
        try {
            textAnalyzer.load(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        System.out.println(textAnalyzer.map.toString());

        textAnalyzer.displayWords();
        textAnalyzer.displayText();
        System.out.println(textAnalyzer.mostFrequentWord());

    }


}
