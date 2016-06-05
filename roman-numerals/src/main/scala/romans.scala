
package romans

class RomanConverter {
	private val romanIntMap = Map(
		'I' -> 1,
		'V' -> 5,
		'X' -> 10,
		'L' -> 50,
		'C' -> 100,
		'D' -> 500,
		'M' -> 1000
	)
	private val intRomanMap = romanIntMap.map { case (k, v) => (v , k.toString) }

	def toInt(s: String):Int =  {
		convertToInt(s, 0) 
	}
	private def convertToInt(s:String, sum:Int ): Int = s match {
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
		convertToRoman(10, 1,  ""::Nil , i)
	}
	private def validate(b: Boolean, s:String, o:Any){
		if (!b){
			throw new IllegalArgumentException(s.format(o))
		}
	}
	private def convertToRoman(decimal:Int, unit: Int, l:List[String], sum: Int ): String = {
		def minusAndShift(num:Int)=(sum-num)/10
		val digit = sum - ((sum / 10) * 10)
		val five  = decimal / 2
		if (sum == 0) l.mkString
		else if (digit == 9) convertToRoman(decimal * 10, decimal, intRomanMap(unit) :: intRomanMap(decimal) :: l, minusAndShift(9))
		else if (digit == 5) convertToRoman(decimal * 10, decimal, intRomanMap(five) :: l, minusAndShift(5))
		else if (digit == 4 && unit != 1000) convertToRoman(decimal * 10, decimal, intRomanMap(unit) :: intRomanMap((five)) :: l , minusAndShift(4))
		else if ((digit == 4 && unit == 1000) || (digit > 0)) convertToRoman(decimal, unit, intRomanMap(unit) :: l, sum - 1)
		else convertToRoman(decimal * 10, decimal, l, minusAndShift(0))
	}

	private def convertToken(s:Char): Int = { 
		validate(romanIntMap.contains(s), "Invalid roman numeral %s" , s )
		romanIntMap(s)
	}

}