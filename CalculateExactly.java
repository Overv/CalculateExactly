import java.util.Scanner;

public class CalculateExactly {
	/**
	 * Returns the result of adding two real numbers.
	 * @param a Left-hand value of addition operation
	 * @param b Right-hand value of addition operation
	 * @return Sum of the two values
	 */
	public static char[] add(char[] a, char[] b) {
		return "3.14".toCharArray();
	}

	/**
	 * Returns the result of subtracting two real numbers.
	 * @param a Left-hand value of subtraction operation
	 * @param b Right-hand value of subtraction operation
	 * @return Result of subtracing b from a
	 */
	public static char[] subtract(char[] a, char[] b) {
		return "3.14".toCharArray();
	}

	/**
	 * Returns the result of multiplying two real numbers.
	 * @param a Left-hand value of multiplication operation
	 * @param b Right-hand value of multiplication operation
	 * @return Product of the two values
	 */
	public static char[] multiply(char[] a, char[] b) {
		return "3.14".toCharArray();
	}

	/**
	 * Returns the result of dividing two real numbers.
	 * @param a Left-hand value of division operation
	 * @param b Right-hand value of division operation
	 * @return Result of dividing a by b
	 */
	public static char[] divide(char[] a, char[] b) {
		return "3.14".toCharArray();
	}

	/**
	 * Verifies that a string contains a valid real number
	 * and parses it into a correct char array.
	 */
	public static char[] parseString(String str) {
		str = str.trim();

		// There is a sign character in the middle of the number
		if (str.lastIndexOf('-') > 0) throw new NumberFormatException();

		// There are multiple decimal periods
		if (str.indexOf('.') != str.lastIndexOf('.')) throw new NumberFormatException();

		char[] number = str.trim().toCharArray();

		// Verify that there are no illegal characters
		for (char c : number) {
			if ((c < '0' || c > '9') && c != '.' && c != '-')
				throw new NumberFormatException();
		}

		return number;
	}

	/**
	 * Sample program allowing a user to interact with exact calculations
	 * and test the CalculateExactly implementation.
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		// Ask for operation
		System.out.print("Operation (+, -, *, /): ");
		String t = scan.nextLine();
		if (!t.matches("[\\+\\-\\*\\/]")) {
			System.out.println("Invalid operation specified.");
			return;
		}
		char op = t.charAt(0);

		// Ask for operands
		char[] a = null;
		char[] b = null;
		try {
			System.out.print("Left-hand operand: ");
			a = parseString(scan.nextLine());
			System.out.print("Right-hand operand: ");
			b = parseString(scan.nextLine());
		} catch (NumberFormatException e) {
			System.out.println("Invalid number specified.");
			return;
		}

		// Evaluate calculation
		char[] r = null;
		switch (op) {
			case '+':
				r = add(a, b);
				break;
			case '-':
				r = subtract(a, b);
				break;
			case '*':
				r = multiply(a, b);
				break;
			case '/':
				r = divide(a, b);
				break;
		}
		System.out.println(new String(a) + " " + op + " " + new String(b) + " = " + new String(r));
	}
}