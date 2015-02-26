import java.util.Arrays;
import java.util.Scanner;

public class PrintLines extends Lines {
	private String  print_line;

	public PrintLines(String stringLine) {
		super(stringLine);
		print_line = stringLine;
	}

	private  Variable  var1 ;

	@Override
	public void calculate() {
		Scanner scan = new Scanner(print_line);
		scan.next(); // Line number
		scan.next(); // Print statement
		String  can_print = null;
		try {
			can_print = scan.next();
		} catch (java.util.NoSuchElementException s) {
			System.err.println("Error: [" + print_line + "] some variable is required for printing ");
			System.exit(0);
		}

		String[] splitStatement = print_line.split("\\s+");
		if (splitStatement.length > 3) {
			System.err.println("variable is not valid-lin_no: " + print_line);
			System.exit(0);
		}
		if (if_Var(can_print)) {
			var1 = new Variable(can_print); // variable
			System.out.println(var1.getVariable());
			scan.close();
		} else {
			System.err.println("variable is not valid-lin_no: " + print_line);
			System.exit(0);
		}

	}

	public boolean  if_Var(String s) {
		String  capital_alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		char[] characters = capital_alphabets.toCharArray();
		String  small_alphabets = "abcdefghijklmnopqrstuvwxyz";
		char[] lowchar = small_alphabets.toCharArray();
		for (int i = 0; i < s.length(); i++) {
			if (Character.isLetter(s.charAt(i)) && !Interpreter.given_variable.contains(Character.toString(s.charAt(i)).toLowerCase()) && !Interpreter.given_variable.contains(Character.toString(s.charAt(i)).toUpperCase())) {
				System.err.println("Given Variable: " + s.charAt(i) + " is not delcared at : " + print_line);
				System.exit(0);
			}
		}
		boolean result = false;
		if (s.length() > 1) {
			result = false;
		} else if ((Arrays.binarySearch(characters, s.charAt(0)) >= 0)) {
			result = true;
		} else if ((Arrays.binarySearch(lowchar, s.charAt(0)) >= 0)) {
			result = true;
		} else {
			result = false;
		}
		return result;

	}
}
