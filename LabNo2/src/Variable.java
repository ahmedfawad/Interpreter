import java.util.Arrays;

public class Variable {
	private String  given_operand;

	public Variable(String operand) {		// constructor
		given_operand = operand;

	}

	public void setVariable(int i) {		// setter function
		Interpreter.operands[array_search(given_operand)] = i;

	}

	public int getVariable() {				// getter function
		int  final_res = 0;
		final_res = Interpreter.operands[array_search(given_operand)];

		return final_res;
	}

	public int  array_search (String b) {
		String  capital_alphabets= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";	// for any capital letters
		char[] a = capital_alphabets.toCharArray();
		String  lower_alphabets = "abcdefghijklmnopqrstuvwxyz";		// for any small letters
		char[] c = lower_alphabets.toCharArray();
		int result = 0;
		if (b.length() == 1 && (Arrays.binarySearch(a, b.charAt(0)) >= 0)) {// compairing the provided arrays with  array a(uppercase)
			result = Arrays.binarySearch(a, b.charAt(0));
		} else if (b.length() == 1 && (Arrays.binarySearch(c, b.charAt(0)) >= 0)) {// compairing the provided arrays with  array c(lowercase)
			result = Arrays.binarySearch(c, b.charAt(0));
		} else {
			System.err.println("Error: " + given_operand + " the variable provided is not apporipirate");
			System.exit(0);
		}

		return result;
	}

}
