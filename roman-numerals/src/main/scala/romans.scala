
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
		validate(i > 0 && i < 5000, "Invalid number: %s", i) 
		println("\n\n *** ")
		println(i)
		convertToRoman(10, 1,  ""::Nil , i)
	}
	def validate(b: Boolean, s:String, o:Any){
		if (!b){
			throw new IllegalArgumentException(s.format(o))
		}
	}
	def convertToRoman(decimal:Int, unit: Int, l:List[String], sum: Int ): String = {
		val digit = sum - ((sum / 10) * 10)
		val five  = decimal / 2
		println(decimal, five, unit, sum, l)

		if (sum == 0) return l.mkString
		if (digit == 9) return convertToRoman(decimal * 10, decimal, intToRomanMap(unit) :: intToRomanMap(decimal) :: l, (sum - 9) / 10)
		if (digit == 5) return convertToRoman(decimal * 10, decimal, intToRomanMap((five)) :: l, (sum - 5) / 10)
		if (digit == 4 && unit != 1000) return convertToRoman(decimal * 10, decimal, intToRomanMap(unit) :: intToRomanMap((five)) :: l , (sum - 4) / 10)
		if (digit == 4 && unit == 1000) return convertToRoman(decimal, unit, intToRomanMap(unit) :: l, sum - 1)
		if (digit > 0) return convertToRoman(decimal, unit, intToRomanMap(unit) :: l, sum - 1)
		return convertToRoman(decimal * 10, decimal, l, (sum/10))

	}

	def convertToken(s:Char): Int = { 
		validate(romanToIntMap.contains(s), "Invalid roman numeral %s" , s )
		romanToIntMap(s)
	}

}