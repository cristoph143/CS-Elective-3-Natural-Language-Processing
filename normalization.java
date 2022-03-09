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
        } else {
            // Step 1c.
            word = recodeY(word);
        }
        System.out.println("Stem recoded: " + word);
        word = step1b(word);

        /*
         * // index penultimate or get the last 2 index letter of stem.
         * int index = word.length() - 2;
         * // if words contains double suffix then map to single suffix
         * if (word.endsWith("es") || word.endsWith("s")) {
         * word = mapToSingleSuffix(word);
         * System.out.println("Map to single suffix: " + word);
         * return word;
         * } else {
         * // if word does not contain double suffix then index the final letter of the
         * // word.
         * index = word.length() - 1;
         * }
         * // if the word ending match with the stem then remove the ending.
         * if (word.endsWith("y") && word.charAt(index) == 'a') {
         * word = word.substring(0, index);
         * System.out.println("Stem removed: " + word);
         * return word;
         * } else {
         * // index penultimate or get the last 2 index letter of stem.
         * index = word.length() - 2;
         * }
         * // if the word ending match with the stem then remove the ending.
         * if (word.endsWith("e") && word.charAt(index) == 'a') {
         * // if it satisfy '<c>vcvc<v>' then remove the ending.
         * if (word.length() > 4 && word.charAt(word.length() - 4) == 'a' &&
         * word.charAt(word.length() - 3) == 'c') {
         * word = word.substring(0, index);
         * System.out.println("Stem removed: " + word);
         * return word;
         * }
         * } else {
         * // index penultimate or get the last 2 index letter of stem.
         * index = word.length() - 2;
         * }
         * // remove final 'e' only if more than one consonant is present in stem.
         * if (word.endsWith("e") && word.length() > 2 && word.charAt(index) != 'a') {
         * word = word.substring(0, index);
         * System.out.println("Stem removed: " + word);
         * }
         */
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
        // if the word contains 'at' or 'bl' or 'iz'
        if (word.contains("at") || word.contains("bl") || word.contains("iz")) {
            // remove the ending
            word = word.substring(0, word.length()) + "e";
            System.out.println("'e' change: " + word);
            return word;
        } else {
            // index penultimate or get the last 2 index letter of stem.
            int index = word.length() - 2;
            // if the word contains double consonant then remove the last letter.
            if (word.charAt(index) == 'l' || word.charAt(index) == 's' || word.charAt(index) == 'z') {
                word = word.substring(0, word.length() - 1);
                System.out.println("Last letter removed: " + word);
                return word;
            } else {
                // if the word contains double consonant then remove the last letter.
                if (word.charAt(index) == 'd') {
                    word = word.substring(0, word.length() - 1);
                    System.out.println("Last letter removed: " + word);
                    return word;
                }
            }
        }
        // return the stem word
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
            word = word.substring(0, word.length() - 2);
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
        else {
            return word;
        }
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