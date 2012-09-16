Description
=======

This is a Java university assignment for bonus points, which has provided the specification and demands listed below. Although the interface proposed in the assignment is far from optimal, arbitrary precision arithmetic is a fun challenge nonetheless. A regular implementation would use proper storage classes for encapsulation and a more memory efficient language, like C.

Specification
=======

The following class should be implemented and used in the target application.

```java
class CalculateExactly {
	char[] add(char[] a, char[] b);
	char[] subtract(char[] a, char[] b);
	char[] multiply(char[] a, char[] b);
	char[] divide(char[] a, char[] b);
}
```

Requirements
=======

* Support for real numbers with sign of arbitrary length
* Numbers are input and stored in strings/character arrays
* Do not make use of non-primitives (e.g. Vectors, Strings)
* When ran as application, it should ask the user for 2 numbers and one of the 4 operations to be applied to them

License
=======

	Copyright (C) 2012 Alexander Overvoorde

	Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

	The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

The license is included to prevent fraud in this application while not preventing other people from learning from the code. Please do not use this code in a real application, instead resort to [BigDecimal](http://docs.oracle.com/javase/1.5.0/docs/api/java/math/BigDecimal.html) or the excellent [GMP library](http://gmplib.org/). Note that the implementation of this project is not based on or inspired by the implementation of the aforementioned implementations.