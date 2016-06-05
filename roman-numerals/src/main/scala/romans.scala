
package romans

class RomanConverter {
	val romanToIntMap = Map(
		'I' -> 1,
		'V' -> 5,
		'X' -> 10,
		'L' -> 50,
		'C' -> 100,
		'D' -> 500,
		'M' -> 1000
	)
	val intToRomanMap = romanToIntMap.map { case (k, v) => (v , k.toString) }

	def toInt(s: String):Int =  {
		convertToInt(s, 0) 
	}
	def convertToInt(s:String, sum:Int ): Int = s match {
			case "" => sum
			case _ => {
				if (s.tail.isEmpty()){
					return sum + convertToken(s.head)
				}
				val curr = convertToken(s.head)
				val next = convertToken(s.tail.head)
				convertToInt(s.tail, if (curr < next) sum - curr else sum + curr)
			}
		}
	def toRoman(i:Int)={ 
		if (i > 0 && i < 5000) 
			intToRomanMap(i)
		else 
			throw new IllegalArgumentException("Invalid Range")
	}

	def convertToken(s:Char): Int = 
		if (romanToIntMap.contains(s)) 
			romanToIntMap(s)
		else
			throw new IllegalArgumentException("Invalid roman numeral " + s)

}