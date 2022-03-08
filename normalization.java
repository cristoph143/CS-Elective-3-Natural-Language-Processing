import java.util.Scanner;

public class normalization {
    public static void main(String[] args) {
        try (// enter a word
                Scanner input = new Scanner(System.in)) {
            System.out.print("Enter a word: ");
            String word = input.nextLine();
            //call stem and save it to variable
            String stem = stem(word);
            //print the word and the stem
            System.out.println("Word: " + word);
            System.out.println("Stem: " + stem);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }
    //return the stem word using porter stemmer algorithm without using library
    public static String stem(String word) {
        //convert the word to lower case
        word = word.toLowerCase();
        System.out.println("Lower case: " + word);
        //remove and recode the plurals
        word = removePlurals(word);
        System.out.println("Plurals removed: " + word);
        //remove the 'ed' or ing if found
        // word = removeEd(word);
        // System.out.println("'ed' or 'ing' removed: " + word);
        //if there is no 'ed' or 'ing' found then recode remaining stem else recode 'y' to 'i' if another vowel is found in the stem
        if (word.endsWith("ed") || word.endsWith("ing")) {
            word = removeEd(word);
            System.out.println("'ed' or 'ing' removed: " + word);
        } else {
            word = recodeY(word);
        }
        System.out.println("Stem recoded: " + word);
        //index penultimate or get the last 2 index letter of stem.
        int index = word.length() - 2;
        return word;
    }
    //remove plurals
    public static String removePlurals(String word) {
        //if the word ends with 'ies' then remove it
        if (word.endsWith("ies")) {
            word = word.substring(0, word.length() - 3) + "y";
        }
        //if the word ends with 'es' then remove it
        if (word.endsWith("es")) {
            word = word.substring(0, word.length() - 2);
        }
        //if the word ends with 's' then remove it
        if (word.endsWith("s")) {
            word = word.substring(0, word.length() - 1);
        }
        return word;
    }
    //remove 'ed' or 'ing'
    public static String removeEd(String word) {
        //if the word ends with 'ed' then remove it
        if (word.endsWith("ed")) {
            word = word.substring(0, word.length() - 2);
        }
        //if the word ends with 'ing' then remove it
        if (word.endsWith("ing")) {
            word = word.substring(0, word.length() - 3);
        }
        return word;
    }
    //recodeY
    public static String recodeY(String word) {
        //if the word ends with 'y' then recode it
        if (word.endsWith("y")) {
            word = word.substring(0, word.length() - 1) + "i";
        }
        return word;
    }

}