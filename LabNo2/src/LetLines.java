import java.util.Scanner;

public class LetLines extends Lines {
	private String line;

	public LetLines(String stringLine) {	// constructor
		super(stringLine);
		this.line = stringLine;
	}

	private Variable  file_var ;

	private ExpressionEvaluation  expression;

	@Override
	public void calculate() {
		Scanner scan = new Scanner(line);
		scan.next();	// line number
		scan.next();	// scanning for let keyword
		String  variable = scan.next();
		file_var  = new Variable(variable); //assigning variable
		// checking for equal sign
		if (!scan.next().equals("=")) {
			System.err.println("= sign is expected at " + line);
			System.exit(0);
		}
		expression = new ExpressionEvaluation(line.substring(line.lastIndexOf("=") + 1));//pass everything after equal(=) sign  to ExpressionEvaluation Class
		file_var .setVariable(expression.getExpression());// calling getter and setter functions to assign variable
		Interpreter.given_variable.add(variable);//storing the variables in array list
		scan.close();

	}

}
