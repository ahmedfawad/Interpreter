

// for importing script manager
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ExpressionEvaluation {
	private String  line;

	public ExpressionEvaluation(String line1) {  // constructor
		super();
		this.line = line1;
	}

	public int getExpression() {
		CharSequence  seq1 = "/0";
		CharSequence  seq2 = "/ 0";	// for checking dividebyzero exception
		int  res = 0;
		try {
			if (line.contains(seq1) || line.contains(seq2)) {
				System.err.println(" Error: Can't Divide By 0: " + line);
				System.exit(0);
			} else {
				String result = rep_Variable(line);
				ScriptEngineManager manager = new ScriptEngineManager();
				ScriptEngine engine = manager.getEngineByName("JavaScript"); // importing javascript script manager for calculating expressions
				Object i = engine.eval(result);
				double u = Double.parseDouble(i.toString());
				res = (int) u;
			}
		} catch (ScriptException s) {
			System.err.println("Error occured while calculating expression at line_no :  " + line);
			System.exit(0);
		}
		return res;

	}
	public String  rep_Variable(String line) {
		String  capital_alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		char[]  charct = capital_alphabets.toCharArray();
		String  lower_alphabets = "abcdefghijklmnopqrstuvwxyz";
		char[] c = lower_alphabets.toCharArray();
		String  res1 = line;
		for (int i = 0; i < line.length(); i++) {
			if (Character.isLetter(line.charAt(i)) && (!Interpreter.given_variable.contains(Character.toString(line.charAt(i)).toLowerCase()) && !Interpreter.given_variable.contains(Character.toString(line.charAt(i)).toUpperCase()))) {
				System.err.println("Given Variable: " + line.charAt(i) + " cannot be found.");
				System.exit(0);
			}
		}
		for (int i = 0; i < Interpreter.operands.length; i++) {
			res1 = res1.replaceAll(Character.toString( charct[i]), Integer.toString(Interpreter.operands[i]));
			res1 = res1.replaceAll(Character.toString(c[i]), Integer.toString(Interpreter.operands[i]));

		}
		return res1;
	}

}
