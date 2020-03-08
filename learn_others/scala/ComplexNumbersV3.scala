object ComplexNumbersV3 {
	def main (args: Array[String]) {
		var c = new Complex (1.2,3.4)
		println ("imaginary part: " + c.im)
		println (c.toString())
	}
}

class Complex (real: Double, imaginary: Double) {
	def re = real
	def im = imaginary
}