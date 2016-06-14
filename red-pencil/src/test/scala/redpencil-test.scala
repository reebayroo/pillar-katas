import org.scalatest._
import redpencil._;
import com.github.nscala_time.time.Imports._;
class RedPencilSpec extends FunSpec with Matchers {
	// Your tests here
	val service = new RedPencilService()
	val oldPrice = Price(100.00, LocalDate.now - 3.months)

	describe ( "Given red pencil service it should... " ) {
		it ("expect a product" ){
			intercept[IllegalArgumentException] {
				service.evaluate(null)
			}
		}
		it ("expect a product with a price" ){
			intercept[IllegalArgumentException] {
				service.evaluate(Product())
			}
		} 
	}
	describe ( "Given red pencil service would return and Red Pencil Promotion that... " ) {
		it ("has not started when there were no price changes " ){
			//println ( "not has started when there are no price changes " )
			val product = Product(Price(3.00))
			service.evaluate(product) should be (null)
		}
		it ("has not started when price drop was less than 5%" ){
			// println("\n not has started when price drop is less than 5%")
			val product = Product(Price(98.00), oldPrice)
			service.evaluate(product) should be (null)
		}
		it ("has started when there was a price drop of 5%" ){
		// println("\n has started when there was a price drop of 5%")
			val expected = RedPencilPromotion(true)
			val product = Product(Price(95.00), oldPrice)
			service.evaluate(product) should be (expected)
		}
		it ("has started when there was a price drop of more 5%" ){
			// println("\n has started when there was a price drop of more 5%")
			val expected = RedPencilPromotion(true)
			val product = Product(Price(94.00), oldPrice)
			service.evaluate(product) should be (expected)
		}
		it ("but no more than 30%" ){
			// println("\n but no more than 30%")
			val product = Product(Price(69.00), oldPrice)
			service.evaluate(product) should be (null)
		}
		it ("exists only if previous price has been stable for at least 30 days" ){
			// println("\n exists only if previous price has been stable for at least 30 days")
			val expected = RedPencilPromotion(true)
			var product = Product(Price(80), Price(100.00, LocalDate.now - 30.days))
			service.evaluate(product) should be (expected)
		}
		it ("not exist if previous price wasn't stable in the last 30 days" ){
			// println("\n not exist if previous price wasn't stable in the last 30 days")
			var product = Product(Price(80.00), Price(100.00, LocalDate.now - 10.days))
			service.evaluate(product) should be (null)
		}
		it ("have an expiration date" ){
			// println("\n have an expiration date")
			val expected = RedPencilPromotion(true, LocalDate.now + 30.days)
			val product = Product(Price(80.00), oldPrice)
			service.evaluate(product) should be (expected)
		}
		it ("have an expiration day of 30 days ..." ){
			val expected = RedPencilPromotion(true, LocalDate.now + 30.days)
			val product = Product(Price(80.00), oldPrice)
			service.evaluate(product) should be (expected)		
		}
		it ("have an expiration day of 30 days from the day of the last price change " ){
			println ( "... from the day of the last price change " )
			val expected = RedPencilPromotion(true, LocalDate.now - 10.days)
			val product = Product(Price(80.00, start=LocalDate.now - 40.days), oldPrice)
			service.evaluate(product) should be (expected)		
		}
	}



}