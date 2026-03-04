import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CW46_Scanner {
    public static void main(String[] args) {
        /*
         * Scanner class: used to read input from various sources (like keyboard, file, etc.)
         * 
         * Constructors:
         * - new Scanner(InputStream in)
         * - new Scanner(File file)
         * - new Scanner(String source) != string path to file
         * 
         * Methods:
         * - sc.nextInt(), sc.nextLine(), sc.nextDouble(), etc. - read different types of input
         * - sc.close() - close the scanner and free resources
         * - sc.hasNext(), sc.hasNextInt(), sc.hasNextLine() - check if there's more input available / EOF marker reached.
         * - sc.useDelimiter() - set a custom delimiter for input parsing
         * - sc.next() reads the next token (word) and sc.nextLine() reads the entire line
         * - sc.nextInt() throws InputMismatchException if the next token is not an int
         * 
         * ref: https://www.geeksforgeeks.org/scanner-class-in-java/
         */

        // IMP: PTR: sc.nextLine() after sc.nextInt() can cause issues due to the newline
        // character left in the input buffer; use sc.nextLine() to consume it before
        // reading the next line.
        
        // Standard scanner creation for keyboard input: Scanner sc = new Scanner(System.in);
        System.out.println("Scanner using file input:");
        Scanner sc = null;
        try {
            sc = new Scanner(new File("scannerinput.txt"));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
        
        System.out.println();
        
        System.out.println("Scanner using delimiter ','");
        // ref: https://stackoverflow.com/questions/28766377/how-do-i-use-a-delimiter-with-scanner-usedelimiter-in-java
        try {
            sc = new Scanner(new File("scannerinputdelimited.txt"));
            sc.useDelimiter(",");
            while (sc.hasNext()) {
                String token = sc.next();
                System.out.println(token);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (sc != null) {
                sc.close();
            }
        }

        System.out.println();

        System.out.println("Scanner using string as source");
        Scanner in = new Scanner("token1 token2 token3 token4");
        while (in.hasNext()) {
            String line = in.next(); // line separator regex = ".*(\r\n|[\n\r\u2028\u2029\u0085])|.+$"
            // \r\n — matches a Windows-style line ending (carriage return + line feed, i.e., CRLF).
            // [\n\r\u2028\u2029\u0085] — matches any one of the following single characters:
            // \n — Line Feed (LF, Unix/Linux/Mac line ending)
            // \r — Carriage Return (CR, old Mac line ending)
            // \u2028 — Unicode Line Separator
            // \u2029 — Unicode Paragraph Separator
            // \u0085 — Unicode Next Line (NEL)
            System.out.println(line);
        }
        in.close();

        System.out.println();

        System.out.println("Scanner using System.in");
        sc = new Scanner(System.in);
        System.out.println("Enter a num or 'done' to end input:");
        int sum = 0;
        // works fine with any given whitespace and newline
        while (sc.hasNext()) {
            if (sc.hasNextInt()) {
                int x = sc.nextInt(); // works fine with whitespace and newline as delimiter
                System.out.println(x);
                sum += x;
                System.out.println("Enter a num or 'done' to end input:");
            } else {
                String input = sc.next(); // works fine with whitespace and newline as delimiter
                System.out.println(input);
                if (input.equalsIgnoreCase("done")) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter an integer or type 'done' to finish.");
                }
            }
        }
        System.out.println(sum);
        sc.close();

        // qq - Does in.close() close System.in?
        // ans - Yes, calling in.close() will also close System.in.
        // calling close() on the Scanner, closes the underlying input stream (System.in). 
        // After that, you cannot read from System.in again in your program.
    }
}

/**
 * Output:
 * -------
 * Scanner using file input:
 * hey
 * this
 * is
 * a
 * sentence
 * 
 * Scanner using delimiter ','
 * line1:
 * hey
 * this
 * is
 * a
 * delimited
 * no-space
 * input
 * line2:
 * this
 * is
 * another
 * delimited
 * no-space
 * input
 * 
 * 
 * Scanner using string as source
 * token1
 * token2
 * token3
 * token4
 * 
 * Scanner using System.in
 * Enter a num or 'done' to end input:
 * 1
 * 1
 * Enter a num or 'done' to end input:
 * 
 * 
 * 
 * 3
 * 3
 * Enter a num or 'done' to end input:
 *  
 *  
 *  
 *  
 *  
 *  
 * 5
 * 5
 * Enter a num or 'done' to end input:
 * done
 * done
 * 9
 */