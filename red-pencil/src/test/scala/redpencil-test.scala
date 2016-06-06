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
	it should "not start when there are no price changes " in {
		val expected = RedPencilPromotion(false)
		service.evaluate(new Product(3.00)) should be (expected)
	}
	it should "not start when price drop is less than 5%" in {
		val expected = RedPencilPromotion(false)
		service.evaluate(new Product(98.00, List(new PriceHistory(100.00)))) should be (expected)
	}
	it should "start when there is a price drop of 5%" in {
		val expected = RedPencilPromotion(true)
		service.evaluate(new Product(95.00, List(new PriceHistory(100.00)))) should be (expected)
	}
	it should "start when there is a price drop of more 5%" in {
		val expected = RedPencilPromotion(true)
		service.evaluate(new Product(94.00, List(new PriceHistory(100.00)))) should be (expected)
	}
	it should "but no more than 30%" in {
		val expected = RedPencilPromotion(false)
		service.evaluate(new Product(69.00, List(new PriceHistory(100.00)))) should be (expected)
	}

}