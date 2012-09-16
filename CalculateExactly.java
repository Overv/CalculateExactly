import java.util.Scanner;

/**
 * CalculateExactly contains functions for arbitrary precision
 * arithmetic on real numbers. It also contains a sample
 * application to try out the class.
 *
 * Numbers are contained in char[] arrays where digits are
 * stored as integer values 0 - 9 and sign (-) and period (.)
 * characters as their respective ASCII values. There is no
 * container format. This unfortunately requires checks in
 * every operation.
 *
 * @author Alexander Overvoorde
 */
public class CalculateExactly {
	/**
	 * Returns the result of adding two real numbers.
	 * @param a Left-hand value of addition operation
	 * @param b Right-hand value of addition operation
	 * @return Sum of the two values
	 */
	public static char[] add(char[] a, char[] b) {
		// TODO: Support numbers of different length and negative numbers
		// TODO: Check input values for validness (can be integrated in pad function)

		char[] res = new char[a.length];
		char remainder = 0;

		for (int i = a.length - 1; i >= 0; i--) {
			// Sign and period characters are left alone
			if (a[i] > 9) {
				res[i] = a[i];
				continue;
			}

			// Digits are added onto each other
			char t = (char) (a[i] + b[i] + remainder);
			if (t < 9) {
				res[i] = t;
			} else {
				res[i] = (char) (t % 10);
				remainder = (char) (t - 10);
			}
		}

		// TODO: Add digit if remainder > 0

		return res;
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
		// and convert number digits to actual int values
		for (int i = 0; i < number.length; i++) {
			char c = number[i];
			if ((c < '0' || c > '9') && c != '.' && c != '-')
				throw new NumberFormatException();

			if (c >= '0' && c <= '9')
				number[i] = (char) (c - '0');
		}

		return number;
	}

	/**
	 * Converts a number in the internal char array representation
	 * back to a human-readable string.
	 */
	public static String toString(char[] n) {
		char[] t = new char[n.length];

		for (int i = 0; i < n.length; i++) {
			if (n[i] > 9) {
				t[i] = n[i];
			} else {
				t[i] = (char) (n[i] + '0');
			}
		}

		return new String(t);
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
		System.out.println(toString(a) + " " + op + " " + toString(b) + " = " + toString(r));
	}
}