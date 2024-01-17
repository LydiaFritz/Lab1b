
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//LYDIA
public class PatternMatcher {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner kb = new Scanner(System.in);
        String filename, patt = "";
        ArrayList<String> wordList = new ArrayList<String>();

        System.out.printf("Enter a file name to search in : ");

        // *********************************************//
        // must use next line, must detect empty string
        // *********************************************//
        filename = kb.nextLine();

        try {
            wordList = getWordList(filename);
            System.out.println("File read successfully, initiating pattern matcher...");
            // get pattern from user
            System.out.print("Enter a pattern to match (or press Enter to exit): ");
            patt = kb.nextLine();
            while (!patt.equals("")) {

                // get the matches
                ArrayList<String> matches = getMatches(wordList, patt);

                // display a list of all the words in the text file that match the pattern.
                System.out.println(matches.toString());

                // ask for next pattern
                System.out.print("Enter a pattern to match (or press Enter to exit): ");
                patt = kb.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found, exiting...");
            // System.exit(20); POSSIBLE PROBLEM? IT IS.
            // System.exit(0);
        }

        kb.close();
    }

    public static ArrayList<String> getMatches(ArrayList<String> words, String patt) {
        ArrayList<String> result = new ArrayList<String>();

        // for ea word
        for (String s : words) {
            // see if this word (s) matches the patt
            if (isMatch(s, patt)) {
                result.add(s);
            }
        }

        return result;
    }

    public static boolean isMatch(String word, String pattern) {

        // check length
        if (word.length() != pattern.length())
            return false;
        // length must be the same, so check each letter
        for (int i = 0; i < word.length(); i++) {
            if (!isMatchAtIndex(word, pattern, i))
                return false;
        }
        return true;
    }

    public static boolean isMatchAtIndex(String theWord, String pattern, int index) {
        // is the index in range?
        if (index >= theWord.length() || index >= pattern.length())
            return false;
        else if (pattern.charAt(index) == '_')
            return true;
        else if (pattern.charAt(index) == theWord.charAt(index))
            return true;
        else
            return false;
    }

    public static ArrayList<String> getWordList(String filename) throws FileNotFoundException {
        ArrayList<String> result = new ArrayList<String>();
        Scanner fin;
        fin = new Scanner(new File(filename));
        while (fin.hasNext()) {
            result.add(fin.next());
        }
        return result;
    }
}
