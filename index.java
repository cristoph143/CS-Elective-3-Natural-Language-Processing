import java.util.Scanner;

public class index {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                menu();
                int choice = sc.nextInt();
                sc.nextLine();
                if (choice == 3)
                    System.exit(0);
                System.out.println("Please enter the Sentence: ");
                String sentence = sc.nextLine();
                switch (choice) {
                    case 1:
                        String[] tokenized = space_tokenize(sentence);
                        print(tokenized);
                        break;
                    case 2:
                        // input the regex expression
                        System.out.println("Please enter the regex expression: ");
                        String regex = sc.nextLine();
                        String[] tokenize = reg_tokenize(sentence, regex);
                        print(tokenize);
                        break;
                    default:
                        System.out.println("Invalid choice");
                        break;
                }

            }
        } catch (Exception e) {
            System.out.println("Error");
        }

    }

    // return the choice from the given menu
    public static void menu() {
        System.out.println("::What would you want to do::");
        System.out.println("1. White Space Tokenization (Tokenize a sequence of strings)");
        System.out.println("2. Regular Expression Tokenization (Tokenize a sequence of strings using a RegExp)");
        System.out.println("3. Quit");
        System.out.println("Enter your choice: ");
    }

    private static String[] space_tokenize(String sentence) {
        // if str is empty, return empty array
        if (sentence.isEmpty()) {
            return new String[0];
        }
        // declare a new variable for tokens
        String[] tokens = new String[0];
        // while str is not a whitespace
        // while (!sentence.isEmpty()) {
        //     // if the index of the str is whitespace then add the substring of the first
        //     // index until the -1 index before the whitespace
        //     if (sentence.indexOf(" ") == -1) {
        //         tokens = add(tokens, sentence.substring(0, sentence.length()));
        //         sentence = "";
        //     } else {
        //         tokens = add(tokens, sentence.substring(0, sentence.indexOf(" ")));
        //         sentence = sentence.substring(sentence.indexOf(" ") + 1);
        //     }
        // }
        
        // remove regexp from the sentence
        tokens = iterate(sentence);
        return tokens;
    }

    // return a array of token where it is tokenized using the reg_exp without using
    // library
    private static String[] reg_tokenize(String sentence, String regex) {
        // if str is empty, return empty array
        if (sentence.isEmpty()) {
            return new String[0];
        }
        // check if regexp is empty
        if (regex.isEmpty()) {
            return new String[0];
        }
        // declare a new variable for tokens
        String[] tokens = new String[0];
        // remove regexp from the sentence
        sentence = sentence.replaceAll(regex, " ");
        tokens = iterate(sentence);
        return tokens;
    }

    private static String[] iterate(String sentence) {
        // declare a new variable for tokens
        String[] tokens = new String[0];
        // while str is not a whitespace
        while (!sentence.isEmpty()) {
            // if the index of the str is whitespace then add the substring of the first
            // index until the -1 index before the whitespace
            if (sentence.indexOf(" ") == -1) {
                tokens = add(tokens, sentence.substring(0, sentence.length()));
                sentence = " ";
            } else {
                tokens = add(tokens, sentence.substring(0, sentence.indexOf(" ")));
                sentence = sentence.substring(sentence.indexOf(" ") + 1);
            }
        }
        return tokens;
    }

    private static String[] add(String[] tokens, String substring) {
        String[] newTokens = new String[tokens.length + 1];
        for (int i = 0; i < tokens.length; i++) {
            newTokens[i] = tokens[i];
        }
        newTokens[tokens.length] = substring;
        return newTokens;
    }

    // print tokens
    private static void print(String[] tokens) {
        System.out.print("Tokenized Sentence: [");
        for (String token : tokens) {
            // if it is the last token, remo the comma
            if (token.equals(tokens[tokens.length - 1])) {
                System.out.print(token);
            } else {
                System.out.print(token + ", ");
            }
        }
        System.out.println("]");
    }
}

// String sentence = "I have beautiful xxccxc! dssdh ddfde,u sds???";
// University of San Jose - Recoletos is a university here in cebu.
// String regexp = r'[-;,\s]'