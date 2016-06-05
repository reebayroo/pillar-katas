import org.scalatest._
import romans._;
class RomansSpec extends FlatSpec with Matchers {
      // Your tests here
	val converter = new RomanConverter()
	behavior of "A roman numerals to integer converter  "
	it should "return 0 for empty string " in  { converter.toInt("") should be (0)}
	it should "return 1 for 'I'"      in { converter.toInt("I") should be (1) }
	it should "return 5 for 'V'"     in { converter.toInt("V")    should be (5)} 
	it should "return 10 for 'X'"    in { converter.toInt("X")    should be (10)} 
	it should "return 50 for 'L'"    in { converter.toInt("L")    should be (50)} 
	it should "return 100 for 'C' "  in { converter.toInt("C")    should be (100)}
	it should "return 500 for 'D' "  in { converter.toInt("D")    should be (500)}
	it should "return 1000 for 'M' " in { converter.toInt("M")    should be (1000)}
	it should "return 2 for 'II'"     in { converter.toInt("II")   should be (2)}
	it should "return 3 for 'III'"    in { converter.toInt("III")  should be (3)}
	it should "return 30 for 'XXX'"   in { converter.toInt("XXX")  should be (30)}
	it should "return 300 for 'CCC'"  in { converter.toInt("CCC")  should be (300)}
	it should "return 4 for 'IV'"     in { converter.toInt("IV")   should be (4)}
	it should "return 7 for 'VII'"    in { converter.toInt("VII")  should be (7)}
	it should "return 8 for 'VIII'"   in { converter.toInt("VIII") should be (8)}
	it should "return 9 for 'IX'"     in { converter.toInt("IX")   should be (9)}
	it should "return 13 for 'XIII'"  in { converter.toInt("XIII") should be (13)}
	it should "return 14 for 'XIV'"   in { converter.toInt("XIV")  should be (14)}
	it should "return 15 for 'XV'"    in { converter.toInt("XV")   should be (15)}
	it should "return 40 for 'XL'"    in { converter.toInt("XL")   should be (40)}
	it should "return 90 for 'XC'"    in { converter.toInt("XC")   should be (90)}
	it should "return 98 for 'XCVIII'" in { converter.toInt("XCVIII")   should be (98)}
	it should "return 99 for 'XCIX'" in { converter.toInt("XCIX")   should be (99)}
	it should "return 999 for 'CMXCIX'" in { converter.toInt("CMXCIX")   should be (999)}
	it should "return 4999 for 'MMMMCMXCIX'" in { converter.toInt("MMMMCMXCIX")   should be (4999)}
	it should "throw IllegalArgumentException if given invalid Roman Numeral " in {
		intercept[IllegalArgumentException] {
			converter.toInt("W")
		}
	}

	behavior of "An integer to roman numerals converter  "
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
	it should "return 'I' for 1" in { converter.toRoman(1) should be ("I")}
	it should "return 'V' for 5" in { converter.toRoman(5) should be ("V")}
	it should "return 'X' for 10" in { converter.toRoman(10) should be ("X")}
	it should "return 'L' for 50" in { converter.toRoman(50) should be ("L")}
	it should "return 'C' for 100" in { converter.toRoman(100) should be ("C")}
	it should "return 'D' for 500" in { converter.toRoman(500) should be ("D")}
	it should "return 'M' for 1000" in { converter.toRoman(1000) should be ("M")}
	it should "return 'II' for 2" in { converter.toRoman(2) should be ("II")}
	it should "return 'II' for 3" in { converter.toRoman(3) should be ("III")}
	it should "return 'VI' for 6" in { converter.toRoman(6) should be ("VI")}
	it should "return 'VII' for 7" in { converter.toRoman(7) should be ("VII")}
	it should "return 'VIII' for 8" in { converter.toRoman(8) should be ("VIII")}
	it should "return 'IV' for 4" in { converter.toRoman(4) should be ("IV")}
	it should "return 'IX' for 9" in { converter.toRoman(9) should be ("IX")}
	it should "return 'XIV' for 14" in { converter.toRoman(14) should be ("XIV")}

}