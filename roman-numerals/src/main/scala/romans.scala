
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
		convertToInt(s.toList, 0) 
	}
	private def convertToInt(s:List[Char], sum:Int ): Int = s match {
			case Nil => sum
			case head :: Nil => return sum + convertToken(head)
			case head::tail => { 
				val curr = convertToken(head)
				val next = convertToken(tail.head)
				convertToInt(tail, if (curr < next) sum - curr else sum + curr)
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
		def subtractAndShift(num:Int)=(sum-num)/10
		val digit = sum - ((sum / 10) * 10)
		val five  = decimal / 2
		if (sum == 0) l.mkString
		else digit match {
			case 9 => convertToRoman(decimal * 10, decimal, intRomanMap(unit) :: intRomanMap(decimal) :: l, subtractAndShift(9))
			case 5 => convertToRoman(decimal * 10, decimal, intRomanMap(five) :: l, subtractAndShift(5))
			case 4 if unit != 1000 => convertToRoman(decimal * 10, decimal, intRomanMap(unit) :: intRomanMap((five)) :: l , subtractAndShift(4))
			case 4 if unit == 1000 => convertToRoman(decimal, unit, intRomanMap(unit) :: l, sum - 1)
			case d if d > 0 => convertToRoman(decimal, unit, intRomanMap(unit) :: l, sum - 1)
			case _ => convertToRoman(decimal * 10, decimal, l, subtractAndShift(0))
		}
	}

	private def convertToken(s:Char): Int = { 
		validate(romanIntMap.contains(s), "Invalid roman numeral %s" , s )
		romanIntMap(s)
	}

}