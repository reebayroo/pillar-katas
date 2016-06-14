import org.scalatest._
import redpencil._;
import com.github.nscala_time.time.Imports._;
class RedPencilSpec extends FunSpec with Matchers {
	// Your tests here
	val service = new RedPencilService()
	val oldPrice = Price(100.00, LocalDate.now - 3.months)
	describe ( "Given the red pencil service ... " ) {
		describe ( "the evaluate method should... " ) {
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
		describe ( "when a product with a price is provided, a Red Pencil Promotion" ) {
		it ("should not exist when there were no price changes " ){
			//println ( "not has started when there are no price changes " )
			val product = Product(Price(3.00))
			service.evaluate(product) should be (None)
		}
		it ("should not exist when price drop was less than 5%" ){
			val product = Product(Price(98.00), oldPrice)
			service.evaluate(product) should be (None)
		}
		it ("should exist when there was a price drop of 5%" ){
				val expected = RedPencilPromotion()
				val product = Product(Price(95.00), oldPrice)
				service.evaluate(product) should be (Some(expected))
		}
		it ("should exist when there was a price drop of more 5%" ){
			val expected = RedPencilPromotion()
			val product = Product(Price(94.00), oldPrice)
			service.evaluate(product) should be (Some(expected))
		}
		it ("but no more than 30%" ){
			val product = Product(Price(69.00), oldPrice)
			service.evaluate(product) should be (None)
		}
		it ("should exist when previous price has been stable for at least 30 days" ){
			val expected = RedPencilPromotion()
			var product = Product(Price(80), Price(100.00, LocalDate.now - 30.days))
			service.evaluate(product) should be (Some(expected))
		}
		it ("should not exist if previous price wasn't stable in the last 30 days" ){
			var product = Product(Price(80.00), Price(100.00, LocalDate.now - 10.days))
			service.evaluate(product) should be (None)
		}
		it ("should have an expiration date. Default 30 days." ){
			val expected = RedPencilPromotion(LocalDate.now + 30.days)
			val product = Product(Price(80.00), oldPrice)
			service.evaluate(product) should be (Some(expected))
		}
		it ("... from the day of the last price change " ){
			println ( "... from the day of the last price change " )
			val expected = RedPencilPromotion(LocalDate.now - 10.days)
			val product = Product(Price(80.00, start=LocalDate.now - 40.days), oldPrice)
			service.evaluate(product) should be (Some(expected))		
		}
	}

}



}