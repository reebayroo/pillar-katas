import org.scalatest._
import romans._;
class RomansSpec extends FlatSpec with Matchers {
      // Your tests here
      val converter = new RomanConverter()
      "A Roman Converter " should " change I to 1 " in {
         converter.toInt("I") should be (1)
      }
	  "" should "change 'V' to  5" in { converter.toInt("V") should be (5)} 
	  "" should "change 'X' to  10" in { converter.toInt("X") should be (10)} 
	  "" should "change 'L' to  50" in { converter.toInt("L") should be (50)} 
	  "" should "change 'C' to  100 " in  { converter.toInt("C") should be (100)}
	  "" should "change 'D' to  500 " in  { converter.toInt("D") should be (500)}
	  "" should "change 'M' to  1000 " in  { converter.toInt("M") should be (1000)}
}