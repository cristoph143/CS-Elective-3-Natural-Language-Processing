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

        return word;
    }
}