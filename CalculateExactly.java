import java.util.Scanner;

/**
 * CalculateExactly contains functions for arbitrary precision
 * arithmetic on real numbers. It also contains a sample
 * application to try out the class.
 *
 * There is no encapsulated container format. This unfortunately
 * requires checks in every operation.
 *
 * Numbers are contained in char[] arrays where digits are
 * stored as integer values 0 - 9 and sign (-) and period (.)
 * characters as their respective ASCII values. The format
 * is similar to the human-readable string representation, but
 * there are a few differences. For example, integers like 5
 * are always stored as 5.0 to reduce code complexity. Numbers
 * as operands are also padded to the same size in operations,
 * but the result is always reduced to the smallest possible
 * representation, i.e. 0.05 + 0.05 will return 0.1 and not 0.10.
 *
 * TODO: toString should return ints when there is only one decimal digit == 0
 * TODO: Implement subtract()
 * TODO: Support negative numbers, affects pad(), reduce() and other functions!
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
		a = pad(a, b);
		b = pad(b, a);

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
				remainder = 0;
			} else {
				res[i] = (char) (t % 10);
				remainder = (char) ((t - res[i]) / 10);
			}
		}

		// Add an extra digit if there is still a remainder
		if (remainder > 0) {
			char[] temp = new char[res.length+1];
			for (int i = 0; i < res.length; i++) {
				temp[i+1] = res[i];
			}
			temp[0] = remainder;
			res = temp;
		}

		return reduce(res);
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
	 * Pads a number with zeroes so that it has the same
	 * size as another number.
	 * @param n Number to pad
	 * @param ref Reference number with target pad size
	 * @return Number padded to have the same size as ref
	 */
	private static char[] pad(char[] n, char[] ref) {
		int[] curSize = getNumberSize(n);
		int[] refSize = getNumberSize(ref);

		// If n is more or equally precise than ref, nothing
		// needs to be done, ref should be padded to a instead.
		if (curSize[0] >= refSize[0] && curSize[1] >= refSize[1]) {
			return n;
		} else {
			return zeroPadArray(n, refSize[0] - curSize[0], refSize[1] - curSize[1]);
		}
	}

	/**
	 * Removes zeroes before and after to reduce a number
	 * to the smallest possible representation while
	 * maintaining the precision.
	 */
	private static char[] reduce(char[] n) {
		// The zero in 0.5 should not be removed
		int before = 0;
		for (int i = 0; i < n.length; i++)
			if (n[i] == 0 && n[i+1] != '.')
				before++;
			else
				break;

		// The zero in 1.0 should not be removed either
		int after = 0;
		for (int i = n.length - 1; i >= 0; i--)
			if (n[i] == 0 && n[i-1] != '.')
				after++;
			else
				break;

		// Return new array without them
		char[] temp = new char[n.length - before - after];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = n[i + before];
		}

		return temp;
	}

	/**
	 * Returns the amount of digits before and after the
	 * decimal period in the specified number.
	 * @param n A number
	 * @return An array with two values, the before and after digit count
	 */
	private static int[] getNumberSize(char[] n) {
		int before = 0;
		int after = 0;
		for (int i = 0; i < n.length; i++) {
			if (n[i] == '.') {
				before = i;
				after = n.length - i - 1;
				break;
			}
		}
		return new int[] {before, after};
	}

	/**
	 * Adds the specified amount of zeroes before and after
	 * the values in an existing array.
	 * @param arr Array to be padded
	 * @param before Amount of zeroes to prepend
	 * @param after Amount of zeroes to append
	 */
	private static char[] zeroPadArray(char[] arr, int before, int after) {
		char[] temp = new char[arr.length + before + after];

		for (int i = 0; i < temp.length; i++) {
			if (i < before) {
				temp[i] = 0;
			} else if (i > before + arr.length - 1) {
				temp[i] = 0;
			} else {
				temp[i] = arr[i - before];
			}
		}

		return temp;
	}

	/**
	 * Verifies that a string contains a valid real number
	 * and parses it into a correct char array.
	 * @param str String representation of a number
	 * @return Number in internal representation
	 */
	public static char[] parseString(String str) {
		str = str.trim();

		// There is no number to parse
		if (str.length() == 0) throw new NumberFormatException();

		// There is a sign character in the middle of the number
		if (str.lastIndexOf('-') > 0) throw new NumberFormatException();

		// There are multiple decimal periods
		if (str.indexOf('.') != str.lastIndexOf('.')) throw new NumberFormatException();

		char[] number = str.trim().toCharArray();

		// Verify that there are no illegal characters
		// and convert number digits to actual int values
		boolean decimal = false;
		for (int i = 0; i < number.length; i++) {
			char c = number[i];
			if ((c < '0' || c > '9') && c != '.' && c != '-')
				throw new NumberFormatException();

			if (c >= '0' && c <= '9')
				number[i] = (char) (c - '0');
			else if (c == '.')
				decimal = true;
		}

		// If there is a decimal period at the beginning, pad with a zero
		if (number[0] == '.')
			number = zeroPadArray(number, 1, 0);

		// Integers are internally represented as real numbers
		// to reduce edge cases and decrease code complexity.
		// e.g. 3 as input results in 3.0 internally
		if (!decimal) {
			char[] temp = new char[number.length+2];

			for (int i = 0; i < temp.length; i++) {
				if (i < number.length) {
					temp[i] = number[i];
				} else if (i == temp.length - 2) {
					temp[i] = '.';
				} else {
					temp[i] = 0;
				}
			}

			number = temp;
		}

		return number;
	}

	/**
	 * Converts a number in the internal char array representation
	 * back to a human-readable string.
	 * @param n Internal representation of a number
	 * @return Number in string representation
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

		// Numbers like 1.0 are displayed as 1
		if (t[t.length-2] == '.' && t[t.length-1] == '0')
			return new String(t, 0, t.length - 2);
		else
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