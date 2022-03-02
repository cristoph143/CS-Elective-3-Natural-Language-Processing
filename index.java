import java.util.Scanner;

public class index {
    public static void main(String[] args) {
        //loop until the return value from menu function is 3
        while (menu() >= 0 && menu() < 3) {
            switch(menu()){
                case 1:
                    //store the value from input_string function
                    String input = input_string();
                    //calls space_tokenized and saved it to the new variable
                    String[] tokens = space_tokenized(input);
                    //calls print_tokens and saved it to the new variable
                    print_tokens(tokens);
                    break;
                case 2:
                    //store the value from input_string function
                    String input2 = input_string();
                    //scan a reg_exp expression and saved it to the new variable
                    System.out.println("Enter a regular expression: ");
                    Scanner scan = new Scanner(input2);
                    String reg_exp = scan.nextLine();
                    //calls reg_tokenize and saved it to the new variable
                    String[] tokens2 = reg_tokenize(input2, reg_exp);
                    //calls print_tokens and saved it to the new variable
                    print_tokens(tokens2);
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid input");
                    break;
            }
        }
    }

    //return the choice from the given menu
    public static int menu() {
        try (Scanner scan = new Scanner(System.in)) {
            System.out.println("1. White Space Tokenization (Tokenize a sequence of strings)");
            System.out.println("2. Regular Expression Tokenization (Tokenize a sequence of strings using a RegExp)");
            System.out.println("3. Quit");
            System.out.println("Enter your choice: ");
            int choice = scan.nextInt();
            return choice;
        }catch(Exception e){
            System.out.println("Error: " + e);
            return 0;
        }
    }

    public static String input_string(){
        //input_string
        try (Scanner scan = new Scanner(System.in)) {
            System.out.println("Enter a sequence of strings");
            String s = scan.nextLine();
            return s;
        }catch(Exception e){
            System.out.println("Error: " + e);
            return null;
        }
    }
    
    private static void print_tokens(String[] tokens) {
        //loop through the tokens array
        for (int i = 0; i < tokens.length; i++) {
            //print the value of the tokens array
            System.out.println(tokens[i]);
        }
    }

    // return a white space tokenized array of strings
    public static String[] space_tokenized(String s) {
        String[] tokens = s.split("\\s+");
        return tokens;
    }

    // return a regular expression tokenized array of strings
    public static String[] reg_tokenize(String s, String regex) {
        String[] tokens = s.split(regex);
        return tokens;
    }
}
