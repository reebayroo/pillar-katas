import org.scalatest._
import romans._;
class RomansSpec extends FlatSpec with Matchers {
      // Your tests here
      val converter = new RomanConverter()
      "A Roman Converter " should " change I to 1 " in {
         converter.toInt("I") should be (1)
      }
	  "" should "return 0 for empty string " in  { converter.toInt("") should be (0)}
	  "" should "change 'V' to  5"     in { converter.toInt("V") should be (5)} 
	  "" should "change 'X' to  10"    in { converter.toInt("X") should be (10)} 
	  "" should "change 'L' to  50"    in { converter.toInt("L") should be (50)} 
	  "" should "change 'C' to  100 "  in { converter.toInt("C") should be (100)}
	  "" should "change 'D' to  500 "  in { converter.toInt("D") should be (500)}
	  "" should "change 'M' to  1000 " in { converter.toInt("M") should be (1000)}
	  "" should "change 'II' to 2"     in { converter.toInt("II") should be (2)}
	  "" should "change 'III' to 3"     in { converter.toInt("III") should be (3)}
	  "" should "change 'XXX' to 30"     in { converter.toInt("XXX") should be (30)}
	  "" should "change 'CCC' to 300"     in { converter.toInt("CCC") should be (300)}
	  "" should "change 'IV' to 4"     in { converter.toInt("IV") should be (4)}
	  "" should "change 'VII' to 7"     in { converter.toInt("VII") should be (7)}
	  "" should "change 'VIII' to 8"     in { converter.toInt("VIII") should be (8)}
	  "" should "change 'IX' to 9"     in { converter.toInt("IX") should be (9)}

}