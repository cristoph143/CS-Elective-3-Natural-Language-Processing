import java.util.Scanner;

public class normalization {
    public static void main(String[] args) {
        try (// enter a word
                Scanner input = new Scanner(System.in)) {
            System.out.print("Enter a word: ");
            String word = input.nextLine();
            String stem = stem(word);
            // print the word and the stem
            System.out.println("Word: " + word);
            System.out.println("Stem: " + stem);
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    // return the stem word using porter stemmer algorithm without using library
    public static String stem(String word) {
        // convert the word to lower case
        word = word.toLowerCase();
        System.out.println("Lower case: " + word);
        // Step1a. remove and recode the plurals
        word = removePlurals(word);
        // Step 1b.
        System.out.println("Plurals removed: " + word);
        if (word.endsWith("ed") || word.endsWith("ing")) {
            word = removeEd(word);
            System.out.println("'ed' or 'ing' removed: " + word);
        }
        System.out.println("Stem recoded: " + word);
        word = step1b(word);
        System.out.println("Step 1b: " + word);
        // Step 1c.
        word = recodeY(word);
        // Step 2.
        word = step2(word);
        System.out.println("Step 2: " + word);
        // Step 3.
        word = step3(word);
        System.out.println("Step 3: " + word);
        // Step 4.
        word = step4(word);
        System.out.println("Step 4: " + word);
        // Step 5a.
        word = step5a(word);
        System.out.println("Step 5a: " + word);
        // Step 5b.
        word = step5b(word);
        System.out.println("Step 5b: " + word);
        // return the stem word
        return word;
    }

    /*
     * If the second or third of the rules in Step 1b is successful, the following
     * is done:
     * AT → ATE conflat(ed) → conflate
     * BL → BLE troubl(ed) → trouble
     * IZ → IZE siz(ed) → size
     * (*d and not (*L or *S or *Z)) → single letter hopp(ing) → hop
     * tann(ed) → tan
     * fall(ing) → fall
     * hiss(ing) → hiss
     * fizz(ed) → fizz
     * (m=1 and *o) → E fail(ing) → fail
     * fil(ing) → file
     */
    public static String step1b(String word) {
        // if the word ends with 'ed'
        if (word.endsWith("ed")) {
            // if the word contains 'at' or 'bl' or 'iz'
            if (word.contains("at") || word.contains("bl") || word.contains("iz")) {
                // remove the ending
                word = word.substring(0, word.length()) + "e";
                System.out.println("'e' change: " + word);
                return word;
            }
        }
        else {
            // index penultimate or get the last 2 index letter of stem.
            int index = word.length() - 2;
            // if the word contains double consonant then remove the last letter.
            if (word.charAt(index) == 'p' || word.charAt(index) == 'n') {
                word = word.substring(0, word.length() - 1);
                System.out.println("Last letter removed: " + word);
                return word;
            }
        }
    // return the stem word
    return word;

    }

    private static String step2(String word) {
        // if word ends with "fulness" then change it to "ful", "ousness" then change it to "ous", "iveness" then change it to "ive"
        if (word.endsWith("fulness") || word.endsWith("ousness") || word.endsWith("iveness")) {
            word = word.substring(0, word.length() - 4);
            System.out.println("'ful/ous' change: " + word);
            return word;
        }
        // if word ends with "enci" then change it to "ence" , "anci" then change it to "ance","abli" then change it to "able"
        if (word.endsWith("enci") || word.endsWith("anci") || word.endsWith("abli")) {
            word = word.substring(0, word.length() - 1) + "e";
            System.out.println("'ence' change: " + word);
            return word;
        }
        // if word ends with "ational" then change it to "ate"
        if (word.endsWith("ational")) {
            word = word.substring(0, word.length() - 5) + "e";
            System.out.println("'ate' change: " + word);
            return word;
        }
        // if word ends with "tional" then change it to "tion"
        if (word.endsWith("tional")) {
            word = word.substring(0, word.length() - 2);
            System.out.println("'tion' change: " + word);
            return word;
        }
        // if word ends with "izer" then change it to "ize"
        if (word.endsWith("izer")) {
            word = word.substring(0, word.length() - 1);
            System.out.println("'ize' change: " + word);
            return word;
        }
        // if word ends with "alli" then change it to "al"
        if (word.endsWith("alli")) {
            word = word.substring(0, word.length() - 2);
            System.out.println("'al' change: " + word);
            return word;
        }
        // if word ends with "entli" then change it to "ent"
        if (word.endsWith("entli")) {
            word = word.substring(0, word.length() - 2);
            System.out.println("'ent' change: " + word);
            return word;
        }
        // if word ends with "eli" then change it to "e"
        if (word.endsWith("eli")) {
            word = word.substring(0, word.length() - 2);
            System.out.println("'e' change: " + word);
            return word;
        }
        // if word ends with "ousli" then change it to "ous"
        if (word.endsWith("ousli")) {
            word = word.substring(0, word.length() - 2);
            System.out.println("'ous' change: " + word);
            return word;
        }
        // if word ends with "ization" then change it to "ize"
        if (word.endsWith("ization")) {
            word = word.substring(0, word.length() - 5) + "e";
            System.out.println("'ize' change: " + word);
            return word;
        }
        // if word ends with "ation" then change it to "ate"
        if (word.endsWith("ation")) {
            word = word.substring(0, word.length() - 3) + "e";
            System.out.println("'ate' change: " + word);
            return word;
        }
        // if word ends with "ator" then change it to "ate"
        if (word.endsWith("ator")) {
            word = word.substring(0, word.length() - 2) + "e";
            System.out.println("'ate' change: " + word);
            return word;
        }
        // if word ends with "alism" then change it to "al"
        if (word.endsWith("alism")) {
            word = word.substring(0, word.length() - 3);
            System.out.println("'al' change: " + word);
            return word;
        }
        // if word ends with "aliti" then change it to "al"
        if (word.endsWith("aliti")) {
            word = word.substring(0, word.length() - 3);
            System.out.println("'al' change: " + word);
            return word;
        }
        // if word ends with "iviti" then change it to "ive"
        if (word.endsWith("iviti")) {
            word = word.substring(0, word.length() - 3) + "e";
            System.out.println("'ive' change: " + word);
            return word;
        }
        // if word ends with "biliti" then change it to "ble"
        if (word.endsWith("biliti")) {
            word = word.substring(0, word.length() - 5) + "le";
            System.out.println("'ble' change: " + word);
            return word;
        }
        return word;
    }

    private static String step3(String word) {
        //if words ends with 'icate' then remove the last 3 letters.
        if (word.endsWith("icate")) {
            word = word.substring(0, word.length() - 3);
            System.out.println("'icate' change: " + word);
            return word;
        }   
        //if words ends with 'ative' then remove the last 5 letters.
        if (word.endsWith("ative")) {
            word = word.substring(0, word.length() - 5);
            System.out.println("'ative' change: " + word);
            return word;
        }
        //if words ends with 'alize' then remove the last 3 letters.
        if (word.endsWith("alize")) {
            word = word.substring(0, word.length() - 3);
            System.out.println("'alize' change: " + word);
            return word;
        }
        //if words ends with 'iciti' then remove the last 3 letters.
        if (word.endsWith("iciti")) {
            word = word.substring(0, word.length() - 3);
            System.out.println("'iciti' change: " + word);
            return word;
        }
        //if words ends with 'ical' then remove the last 2 letters.
        if (word.endsWith("ical")) {
            word = word.substring(0, word.length() - 2);
            System.out.println("'ical' change: " + word);
            return word;
        }
        //if words ends with 'ful' then remove the last 3 letters.
        if (word.endsWith("ful")) {
            word = word.substring(0, word.length() - 3);
            System.out.println("'ful' change: " + word);
            return word;
        }
        //if words ends with 'ness' then remove the last 4 letters.
        if (word.endsWith("ness")) {
            word = word.substring(0, word.length() - 4);
            System.out.println("'ness' change: " + word);
            return word;
        }
        return word;
    }

    private static String step4(String word) {
        //if the words end with 'ement' then remove the last 5 letters.
        if (word.endsWith("ement")) {
            word = word.substring(0, word.length() - 5);
            System.out.println("'5' change: " + word);
            return word;
        }
        //if the words end with 'ance' then remove the last 4 letters.
        if (word.endsWith("ance") || word.endsWith("ence") || word.endsWith("able") 
        || word.endsWith("ible") || word.endsWith("ment") || word.endsWith("ness") 
        || word.endsWith("ship")) { 
            word = word.substring(0, word.length() - 4);
            System.out.println("'4' change: " + word);
            return word;
        }
        //if the words end with 'ant' then remove the last 3 letters.
        if (word.endsWith("ant") || word.endsWith("ent") || word.endsWith("ism")
        || word.endsWith("ate") || word.endsWith("iti") || word.endsWith("ous")
        || word.endsWith("ion") || word.endsWith("ive") || word.endsWith("ize")) {
            word = word.substring(0, word.length() - 3);
            System.out.println("'3' change: " + word);
            return word;
        }
        //if the words end with 'ou' then remove the last 2 letters.
        if (word.endsWith("ou") || word.endsWith("ou") || word.endsWith("er")
        || word.endsWith("al") || word.endsWith("er") || word.endsWith("ic")) {
            word = word.substring(0, word.length() - 2);
            System.out.println("'2' change: " + word);
            return word;
        }
        return word;
    }

    private static String step5a(String word) {
        //if the words end with 'e' then remove the last letter.
        if (word.endsWith("e")) {
            word = word.substring(0, word.length() - 1);
            System.out.println("'e' change: " + word);
            return word;
        }
        //if the words end with 'l' and the next letter is a vowel then remove the last letter.
        if (word.endsWith("l") && isVowel(word.charAt(word.length() - 2))) {
            word = word.substring(0, word.length() - 1);
            System.out.println("'l' change: " + word);
            return word;
        }
        return word;
    }

    private static String step5b(String word) {
        //if the words end with 'll' and the next letter is a vowel then remove the last 2 letters.
        if (word.endsWith("ll") && isVowel(word.charAt(word.length() - 2))) {
            word = word.substring(0, word.length() - 2);
            System.out.println("'ll' change: " + word);
            return word;
        }
        return word;
    }

    // remove plurals
    public static String removePlurals(String word) {
        /*
         * SSES → SS caresses → caress
         * IES → I ponies → poni
         * ties → ti
         * SS → SS caress → caress
         * S → cats → cat
         */
        // if the word ends with 'sses' then remove 'sses'
        if (word.endsWith("sses")) {
            word = word.substring(0, word.length() - 2);
            System.out.println("Plurals removed: " + word);
            return word;
        }
        // if the word ends with 'ies' then remove 'ies'
        else if (word.endsWith("ies")) {
            word = word.substring(0, word.length() - 3) + 'y';
            System.out.println("Plurals removed: " + word);
            return word;
        }
        // if the word ends with 'ss' then remove 'ss'
        else if (word.endsWith("ss")) {
            word = word.substring(0, word.length() - 1);
            System.out.println("Plurals removed: " + word);
            return word;
        }
        // if the word ends with 's' then remove 's'
        else if (word.endsWith("s")) {
            word = word.substring(0, word.length() - 1);
            System.out.println("Plurals removed: " + word);
            return word;
        }
        // if the word does not end with 's' then return the word
        else {
            return word;
        }
    }

    // remove 'ed' or 'ing'
    public static String removeEd(String word) {
        /*
         * (m>0) EED → EE feed → feed
         * agreed → agree
         * (*v*) ED → plastered → plaster
         * bled → bled
         * (*v*) ING → motoring → motor
         * sing → sing
         */
        // if the word ends with 'eed' then remove 'eed'
        if (word.endsWith("eed")) {
            // if the word contains 'eed' and the letter before 'eed' is not a vowel then
            // remove 'eed'
            if (word.length() > 3 && !isVowel(word.charAt(word.length() - 4))) {
                word = word.substring(0, word.length() - 1);
                System.out.println("'eed' removed: " + word);
                return word;
            }
        }
        // if the word ends with 'ed' then remove 'ed'
        else if (word.endsWith("ed")) {
            // if the word contains 'ed' and the letter before 'ed' is not a vowel then
            // remove 'ed'
            if (word.length() > 4 && !isVowel(word.charAt(word.length() - 3))) {
                word = word.substring(0, word.length() - 2);
                System.out.println("'ed' removed: " + word);
                return word;
            }
        }
        // if the word ends with 'ing' then remove 'ing'
        else if (word.endsWith("ing")) {
            // if the word contains 'ing' and the letter before 'ing' is not a vowel then
            // remove 'ing'
            if (word.length() > 4 && !isVowel(word.charAt(word.length() - 4))) {
                word = word.substring(0, word.length() - 3);
                System.out.println("'ing' removed: " + word);
                return word;
            }
        }
        // if the word does not end with 'ed' or 'ing' then return the word
        // return the stem word
        return word;
    }

    // check if the letter is a vowel
    public static boolean isVowel(char letter) {
        if (letter == 'a' || letter == 'e' || letter == 'i' || letter == 'o' || letter == 'u') {
            return true;
        } else {
            return false;
        }
    }

    // recodeY
    public static String recodeY(String word) {
        // if the word ends with 'y' then recode it
        if (word.endsWith("y")) {
            word = word.substring(0, word.length() - 1) + "i";
        }
        return word;
    }

    // mapToSingleSuffix
    public static String mapToSingleSuffix(String word) {
        // if the word ends with 'es' then remove it
        if (word.endsWith("es")) {
            word = word.substring(0, word.length() - 2);
        }
        // if the word ends with 's' then remove it
        if (word.endsWith("s")) {
            word = word.substring(0, word.length() - 1);
        }
        return word;
    }
}