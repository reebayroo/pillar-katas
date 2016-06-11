package redpencil
import com.github.nscala_time.time.Imports._;

class RedPencilService {
	def evaluate(p:Product):RedPencilPromotion={
		if (p == null) throw new IllegalArgumentException("Product Required")
		if (p.prices == Nil) throw new IllegalArgumentException("Price Required")

		def priceChanged(currentPrice:Double, previousPrice:Double) = currentPrice <= previousPrice * 0.95 && currentPrice >= previousPrice * 0.70
		def comparePrices(price:Price, previousPrice:Price):Boolean = priceChanged(price.price, previousPrice.price) 

		return p.prices.tail.headOption match {
			case Some(lastChanged) => RedPencilPromotion(comparePrices(p.prices.head, lastChanged))
			case None => RedPencilPromotion(false)
		}
		

	}
}

case class Product (val prices:Price*)
case class Price(val price:Double=0.00, val start:LocalDate=LocalDate.now())
case class RedPencilPromotion(val active:Boolean, val expiration:LocalDate=LocalDate.now + 30.days)