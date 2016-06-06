import org.scalatest._
import redpencil._;
class RedPencilSpec extends FlatSpec with Matchers {
      // Your tests here
	val service = new RedPencilService()
	behavior of "A red pencil promotion "
	it should "expect a product" in {
		intercept[IllegalArgumentException] {
			service.evaluate(null)
		}
	}
	it should "expect a product with a price" in {
		intercept[IllegalArgumentException] {
			service.evaluate(new Product())
		}
	} 
	
}