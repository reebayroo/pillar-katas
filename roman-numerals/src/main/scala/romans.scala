
package romans

class RomanConverter {
	def toInt(s: String):Int =  {
		convertToInt(s, 0) 
	
	}
	def convertToInt(s:String, sum:Int ): Int = {
		s match {
			case "" => sum
			case _ => {
				if (s.tail.isEmpty()){
					return sum + convertToken(s.head)
				}

				val curr = convertToken(s.head)
				val next = convertToken(s.tail.head)
				if (curr < next) 
					convertToInt(s.tail, sum - curr)
				else 
					convertToInt(s.tail, sum + curr)

			}
			case l => sum + convertToken(l.head)
		}
	}
	def convertToken(s:Char): Int = 
		s match {
			case 'I' => 1
			case 'V' => 5
			case 'X' => 10
			case 'L' => 50
			case 'C' => 100
			case 'D' => 500
			case 'M' => 1000
		}
	
}