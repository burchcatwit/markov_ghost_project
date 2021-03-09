package myCode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

public class MyMarkovChain {
    private static Dictionary dictionary;
    private static FileManager fileManager;

    public static char markov(String dictionaryFilePath, String leading_chars) throws IOException {
        // Constructs required classes
        fileManager = new FileManager(dictionaryFilePath);
        dictionary = new Dictionary(dictionaryFilePath, fileManager);

        // Finds ALL words that begin with leading characters
        // Then convert to an ArrayList
        // Finally initialize array for possible characters
        Set<String> dict_temp = dictionary.getWordsThatStartWith(leading_chars, leading_chars.length()+2, true);
        ArrayList<String> possible_words = new ArrayList<>(dict_temp);
        int[] char_count = new int[26];

        // Count the number of times each letter proceeds leading characters in possible words
        int last_letter = leading_chars.length();
        for (String possible_word : possible_words) {
            int counter = Character.getNumericValue(possible_word.charAt(last_letter)) - 10;
            if (counter >= 0 && counter <= 25) {
                char_count[counter]++;
            }
        }

        // Chooses random char from available with a bias towards more frequent chars
        Random r = new Random();
        int rn = r.nextInt(possible_words.size());
        int sum = 0;
        for (int i = 0; i < char_count.length; i++) {
            sum += char_count[i];
            if (sum >= rn) {
                return (char) (i+97);
            }
        }
        return '_';
    }

    public static void main(String[] args) throws IOException {
        String filePath = "D:\\Users\\burch\\IdeaProjects\\Ghost_Project\\build\\libs\\ARBITER_DICTIONARY.txt";
        System.out.println(markov(filePath, "aar"));
    }
}
