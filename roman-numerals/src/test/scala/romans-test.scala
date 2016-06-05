import org.scalatest._
import romans._;
class RomansSpec extends FlatSpec with Matchers {
      // Your tests here
      val converter = new RomanConverter()
      "A Roman Converter " should " change I to 1 " in {
         converter.toInt("I") should be (1)
      }
      
}