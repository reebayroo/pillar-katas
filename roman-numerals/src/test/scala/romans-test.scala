import org.scalatest._
import romans._;
class RomansSpec extends FlatSpec with Matchers {
      // Your tests here
      val converter = new RomanConverter()
      behavior of "A Roman Number to Integer converter  "
	  it should "return 0 for empty string " in  { converter.toInt("") should be (0)}
      it should "change I to 1 "      in { converter.toInt("I") should be (1) }
	  it should "change 'V' to  5"     in { converter.toInt("V")    should be (5)} 
	  it should "change 'X' to  10"    in { converter.toInt("X")    should be (10)} 
	  it should "change 'L' to  50"    in { converter.toInt("L")    should be (50)} 
	  it should "change 'C' to  100 "  in { converter.toInt("C")    should be (100)}
	  it should "change 'D' to  500 "  in { converter.toInt("D")    should be (500)}
	  it should "change 'M' to  1000 " in { converter.toInt("M")    should be (1000)}
	  it should "change 'II' to 2"     in { converter.toInt("II")   should be (2)}
	  it should "change 'III' to 3"    in { converter.toInt("III")  should be (3)}
	  it should "change 'XXX' to 30"   in { converter.toInt("XXX")  should be (30)}
	  it should "change 'CCC' to 300"  in { converter.toInt("CCC")  should be (300)}
	  it should "change 'IV' to 4"     in { converter.toInt("IV")   should be (4)}
	  it should "change 'VII' to 7"    in { converter.toInt("VII")  should be (7)}
	  it should "change 'VIII' to 8"   in { converter.toInt("VIII") should be (8)}
	  it should "change 'IX' to 9"     in { converter.toInt("IX")   should be (9)}
	  it should "change 'XIII' to 13"  in { converter.toInt("XIII") should be (13)}
	  it should "change 'XIV' to 14"   in { converter.toInt("XIV")  should be (14)}
	  it should "change 'XV' to 15"    in { converter.toInt("XV")   should be (15)}
	  it should "change 'XL' to 40"    in { converter.toInt("XL")   should be (40)}
	  it should "change 'XC' to 90"    in { converter.toInt("XC")   should be (90)}
	  it should "change 'XCVIII' to 98" in { converter.toInt("XCVIII")   should be (98)}
	  it should "change 'XCIX' to 99" in { converter.toInt("XCIX")   should be (99)}
	  it should "change 'CMXCIX' to 999" in { converter.toInt("CMXCIX")   should be (999)}
	  it should "change 'MMMMCMXCIX' to 4999" in { converter.toInt("MMMMCMXCIX")   should be (4999)}
	  
      behavior of "A Integer to Roman Number converter  "
	  it should "return  empty string for 0" in { converter.toRoman(0) should be ("")}

}