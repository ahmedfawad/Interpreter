// ------------------------------------------------ Libraries----------------------------------------------------------------

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
// ------------------------------------------------ Libraries----------------------------------------------------------------


//-------------------------------------------- Main Interpreter Class----------------------------------------------------
public class Interpreter {
	private static Lines[] rows = new Lines[5000];	// creating array for holding lines read from file

	static ArrayList<String> given_variable = new ArrayList<String>();//  creating array list to store 
	public static int[] operands = new int[26];		// for storing operands
	public static int count;
	public static void main(String[] args) throws Exception
	{
		String a="input.txt";
		String z="input1.txt";
		String x="input2.txt";

		file_reader(a);
		//file_reader(x);
		//file_reader(z);
	}
//-------------------------------------------- Main Interpreter Class----------------------------------------------------

	//--------------------------------------------file reader----------------------------------------------------
	public static void file_reader(String file_name) throws IOException {
		FileInputStream in = new FileInputStream(file_name);	// given file as input
		Scanner file = new Scanner(in);

		while (file.hasNext()) {
			String row = file.nextLine();	// reading next line
			parser(row);	// parser function which performs string manipulation
		}
		file.close();

		for (count = 0; count <= 200; count++) {
			if (rows[count] != null) {
				rows[count].calculate();
			}
		}
		in.close();
	}
//-------------------------------------------- file reader----------------------------------------------------
//-------------------------------------------- Parser Function----------------------------------------------------

	// parser translates each line which it reads form file and raise exception in the case of any error
	public static void parser(String row) {
		Scanner scan = new Scanner(row);

		while (scan.hasNext()) {
			String a="";
			String no_Line = scan.next();	// reading the line number
			String line2 = scan.nextLine();	// reading the line part besides line number
			String full_line = no_Line + line2;	// 
			String[] parse = full_line.split("\\s+");  // splitting the line

			for (int i = 0; i < parse.length; i++) {
				if (parse[i].matches("[0-9]+") || parse[i].matches("[a-zA-Z]") || "Let".equals(parse[i]) || "Print".equals(parse[i]) || "=".equals(parse[i]) ||  "+".equals(parse[i]) || "-".equals(parse[i]) || "*".equals(parse[i]) || "/".equals(parse[i])) {
				} else {
					System.err.println("Syntax erorr:  " + full_line);
					System.exit(0);
				}
			}

			if (rows[Integer.parseInt(no_Line)] != null) {
				System.err.println("Duplicate Line Number Detected: " + full_line);
				System.exit(0);
			}
			try {
				Scanner scan1 = new Scanner(full_line);
				scan1.next();
				a = scan1.next();
				
				// checking for let full_line in the input file
				if (a.compareTo("Let") == 0) {
					rows[Integer.parseInt(no_Line)] = new LetLines(full_line);
				}
				
				// checking for print full_line in the input file
				else if (a.compareTo("Print") == 0) {
					rows[Integer.parseInt(no_Line)] = new PrintLines(full_line);
				}

				else {
					System.err.println("Statement Decleration Expected at: " + full_line);
					System.exit(0);
				}
			} catch (java.lang.NumberFormatException s) {
				System.err.println("Wrong line number present at: " + full_line);
				System.exit(0);
			}
		}
		scan.close();
	}
//-------------------------------------------- Parser Function ----------------------------------------------------
}
