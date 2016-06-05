
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
			convertToRoman(""::Nil, i)
		else 
			throw new IllegalArgumentException("Invalid Range")
	}
	def convertToRoman(l:List[String], sum: Int ): String = {
		def fits(num: Int) = num % sum == 0 && sum >= num 

		if (sum <= 0) return l.reverse.mkString
		if (fits(1000)) return convertToRoman(intToRomanMap(1000) :: l, sum - 1000) 
		if (fits(500)) return convertToRoman(intToRomanMap(500) :: l, sum - 500)
		if (fits(100)) return convertToRoman(intToRomanMap(100) :: l, sum - 100)
		if (fits(50)) return convertToRoman(intToRomanMap(50) :: l, sum - 50)
		if (fits(10)) return convertToRoman(intToRomanMap(10) :: l, sum - 10)
		if (fits(5)) return convertToRoman(intToRomanMap(5) :: l, sum - 5)
		if (fits(3)) return convertToRoman("I" :: l, sum-1)
		if (fits(2)) return convertToRoman("I" :: l, sum-1)
		else return convertToRoman("I" :: l, sum-1)
	}

	def convertToken(s:Char): Int = 
		if (romanToIntMap.contains(s)) 
			romanToIntMap(s)
		else
			throw new IllegalArgumentException("Invalid roman numeral " + s)

}