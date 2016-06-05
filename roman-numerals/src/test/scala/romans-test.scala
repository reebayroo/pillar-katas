import org.scalatest._
import romans._;
class RomansSpec extends FlatSpec with Matchers {
      // Your tests here
	val converter = new RomanConverter()
	behavior of "A Roman Number to Integer converter  "
	it should "be 0 for empty string " in  { converter.toInt("") should be (0)}
	it should "be 1 for 'I'"      in { converter.toInt("I") should be (1) }
	it should "be 5 for 'V'"     in { converter.toInt("V")    should be (5)} 
	it should "be 10 for 'X'"    in { converter.toInt("X")    should be (10)} 
	it should "be 50 for 'L'"    in { converter.toInt("L")    should be (50)} 
	it should "be 100 for 'C' "  in { converter.toInt("C")    should be (100)}
	it should "be 500 for 'D' "  in { converter.toInt("D")    should be (500)}
	it should "be 1000 for 'M' " in { converter.toInt("M")    should be (1000)}
	it should "be 2 for 'II'"     in { converter.toInt("II")   should be (2)}
	it should "be 3 for 'III'"    in { converter.toInt("III")  should be (3)}
	it should "be 30 for 'XXX'"   in { converter.toInt("XXX")  should be (30)}
	it should "be 300 for 'CCC'"  in { converter.toInt("CCC")  should be (300)}
	it should "be 4 for 'IV'"     in { converter.toInt("IV")   should be (4)}
	it should "be 7 for 'VII'"    in { converter.toInt("VII")  should be (7)}
	it should "be 8 for 'VIII'"   in { converter.toInt("VIII") should be (8)}
	it should "be 9 for 'IX'"     in { converter.toInt("IX")   should be (9)}
	it should "be 13 for 'XIII'"  in { converter.toInt("XIII") should be (13)}
	it should "be 14 for 'XIV'"   in { converter.toInt("XIV")  should be (14)}
	it should "be 15 for 'XV'"    in { converter.toInt("XV")   should be (15)}
	it should "be 40 for 'XL'"    in { converter.toInt("XL")   should be (40)}
	it should "be 90 for 'XC'"    in { converter.toInt("XC")   should be (90)}
	it should "be 98 for 'XCVIII'" in { converter.toInt("XCVIII")   should be (98)}
	it should "be 99 for 'XCIX'" in { converter.toInt("XCIX")   should be (99)}
	it should "be 999 for 'CMXCIX'" in { converter.toInt("CMXCIX")   should be (999)}
	it should "be 4999 for 'MMMMCMXCIX'" in { converter.toInt("MMMMCMXCIX")   should be (4999)}

	behavior of "A Integer to Roman Number converter  "
	it should "throw IllegalArgumentException if given zero " in {
		intercept[IllegalArgumentException] {
			converter.toRoman(0)
		}
	}
	it should "throw IllegalArgumentException if a number bigger than 4999 " in {
		intercept[IllegalArgumentException] {
			converter.toRoman(5000)
		}
	}
	it should "throw IllegalArgumentException for any number bigger than 4999 " in {
		intercept[IllegalArgumentException] {
			val r = scala.util.Random
			converter.toRoman(5000 + r.nextInt(1000))
		}
	}
	it should "be 'I' for 1" in { converter.toRoman(1) should be ("I")}
	it should "be 'V' for 5" in { converter.toRoman(5) should be ("V")}
	it should "be 'X' for 10" in { converter.toRoman(10) should be ("X")}
	it should "be 'L' for 50" in { converter.toRoman(50) should be ("L")}
	it should "be 'C' for 100" in { converter.toRoman(100) should be ("C")}
	it should "be 'D' for 500" in { converter.toRoman(500) should be ("D")}
	it should "be 'M' for 1000" in { converter.toRoman(1000) should be ("M")}

}