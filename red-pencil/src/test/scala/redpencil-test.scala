import org.scalatest._
import redpencil._;
import com.github.nscala_time.time.Imports._;
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
			service.evaluate(Product())
		}
	} 
	it should "not start when there are no price changes " in {
		val expected = RedPencilPromotion(false)
		val product = Product(Price(3.00))
		service.evaluate(product) should be (expected)
	}
	it should "not start when price drop is less than 5%" in {
		val expected = RedPencilPromotion(false)
		val product = Product(Price(98.00), Price(100.00))
		service.evaluate(product) should be (expected)
	}
	it should "start when there is a price drop of 5%" in {
		val expected = RedPencilPromotion(true)
		val product = Product(Price(95.00), Price(100.00))
		service.evaluate(product) should be (expected)
	}
	it should "start when there is a price drop of more 5%" in {
		val expected = RedPencilPromotion(true)
		val product  = Product(Price(94.00), Price(100.00))
		service.evaluate(product) should be (expected)
	}
	it should "but no more than 30%" in {
		val expected = RedPencilPromotion(false)
		val product = Product(Price(69.00), Price(100.00))
		service.evaluate(product) should be (expected)
	}
	it should "exists only if previous price should've been stable for at least 30 days" in {
		val expected = RedPencilPromotion(false)
		var product = Product(Price(69.00), Price(100.00, LocalDate.now - 30.days))
		service.evaluate(product) should be (expected)
	}
	it should "exist if previous price wasn't stable in the last  30 days" in {
		val expected = RedPencilPromotion(false)
		var product = Product(Price(100.00), Price(100.00, LocalDate.now - 30.days))
		service.evaluate(product) should be (expected)
	}
	it should "have a expiration date" in {
		val expected = RedPencilPromotion(true, LocalDate.now + 30.days)
		val product = Product(Price(80.00), Price(100.00))
		service.evaluate(product) should be (expected)
	}


}