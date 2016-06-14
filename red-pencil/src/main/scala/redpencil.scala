package redpencil
import com.github.nscala_time.time.Imports._


class RedPencilService {
	def evaluate(p:Product):RedPencilPromotion={
		if (p == null) throw new IllegalArgumentException("Product Required")
		if (p.prices == Nil) throw new IllegalArgumentException("Price Required")


		def priceChanged(currentPrice:Double, previousPrice:Double) = currentPrice <= previousPrice * 0.95 && currentPrice >= previousPrice * 0.70
		def debug(label:String, result:Boolean):Boolean = {
			//println(label, result)
			return result
		}
		def comparePrices(price:Price, previousPrice:Price):Boolean = debug ("priceChanged", priceChanged(price.price, previousPrice.price))
		def wasStableForLast30Days(currentPrice:Price, lastPrice:Price)= debug ( "stable" + lastPrice + currentPrice, currentPrice.start.isAfter(lastPrice.start + 29.days))
		def lastDate(lastPriceChange:Price): LocalDate = { 
			println("lastPrice", lastPriceChange)
			return lastPriceChange.start + 30.days
		}

		val prices = p.prices.sortWith( _.start > _.start )

		return prices.tail.headOption match {
			case Some(previousPriceChange) if (
				comparePrices(prices.head, previousPriceChange) &&
				wasStableForLast30Days(prices.head, previousPriceChange) ) => RedPencilPromotion(
				true,
				expiration=lastDate(prices.head) )
			case _ => null
		}
		

	}
}

case class Product (val prices:Price*)
case class Price(val price:Double=0.00, val start:LocalDate=LocalDate.now())
case class RedPencilPromotion(val active:Boolean, val expiration:LocalDate=LocalDate.now + 30.days)