import java.util.Scanner;

public class index {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.println("Enter your choice: ");
                int choice = sc.nextInt();
                sc.nextLine();
                System.out.println("Please enter the Sentence: ");
                String sentence = sc.nextLine();
                switch (choice) {
                    case 1:
                        space_tokenize(sentence);
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
    
    //return the array of tokens being tokenized without using libraries
    public static void space_tokenize(String str){
        String[] tokens = new String[100];
        int i = 0;
        for(int j = 0; j < str.length(); j++){
            if(str.charAt(j) == ' '){
                tokens[i] = str.substring(j, j+1);
                i++;
            }
        }
        // print_tokens(tokens);    
        System.out.println("The tokens are: " + tokens);
    }

    private static void print_tokens(String[] tokens) {
        for(int i = 0; i < tokens.length; i++){
            System.out.println(tokens[i] + "'dsdsd'");
        }
    }
    
}