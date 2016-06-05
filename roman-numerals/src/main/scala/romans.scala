
package romans

class RomanConverter {
	def toInt(s: String):Int =  {
		s match {
			case "" => 0
			case _ => convertToken(s.head) + toInt(s.tail)
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