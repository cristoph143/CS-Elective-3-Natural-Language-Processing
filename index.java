import java.util.Scanner;

public class index {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                menu();
                int choice = sc.nextInt();
                sc.nextLine();
                System.out.println("Please enter the Sentence: ");
                String sentence = sc.nextLine();
                switch (choice) {
                    case 1:
                        String[] tokenized = space_tokenize(sentence);
                        // iterate through tokens
                        System.out.print("Tokenized Sentence: [");
                        for (String token : tokenized) {
                            //if it is the last token, remo the comma
                            if (token.equals(tokenized[tokenized.length - 1])) {
                                System.out.print(token);
                            } 
                            else {
                                System.out.print(token + ", ");
                            }
                        }
                        System.out.println("]");
                        break;
                    case 2:
                        System.out.println("You have chosen to view all students");
                        break;
                    case 3:
                        System.out.println("Goodbye");
                        System.exit(0);
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
        while (!sentence.isEmpty()) {
            // if the index of the str is whitespace then add the substring of the first
            // index until the -1 index before the whitespace
            if (sentence.indexOf(" ") == -1) {
                tokens = add(tokens, sentence.substring(0, sentence.length()));
                sentence = "";
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
}

// String sentence = "I have beautiful xxccxc! dssdh ddfde,u sds???";