
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
		convertToRoman(""::Nil, i)
	}
	def validate(b: Boolean, s:String, o:Any){
		if (!b){
			throw new IllegalArgumentException(s.format(o))
		}
	}
	def convertToRoman(l:List[String], sum: Int ): String = {
		def fits(num: Int) = sum % num != sum
		//println(l, sum)
		if (sum == 0) return l.reverse.mkString
		if (fits(1000)) return convertToRoman(intToRomanMap(1000) :: l, sum - 1000) 
		if (fits(500)) return convertToRoman(intToRomanMap(500) :: l, sum - 500)
		if (fits(100)) return convertToRoman(intToRomanMap(100) :: l, sum - 100)
		if (fits(99)) return convertToRoman(intToRomanMap(100) :: intToRomanMap(10)  :: l, sum - 90)
		
		if (fits(50)) return convertToRoman(intToRomanMap(50) :: l, sum - 50)
		if (fits(40)) return convertToRoman(intToRomanMap(50) :: intToRomanMap(10)  :: l, sum - 40)
		if (fits(10)) return convertToRoman(intToRomanMap(10) :: l, sum - 10)

		if (fits(9)) return convertToRoman(intToRomanMap(10) :: intToRomanMap(1) :: l, sum - 9)
		if (fits(5)) return convertToRoman(intToRomanMap(5) :: l, sum - 5)
		if (fits(4)) return convertToRoman(intToRomanMap(5) :: intToRomanMap(1) :: l, sum - 4)
		return convertToRoman(intToRomanMap(1) :: l, sum-1)
	}

	def convertToken(s:Char): Int = { 
		validate(romanToIntMap.contains(s), "Invalid roman numeral %s" , s )
		romanToIntMap(s)
	}

}