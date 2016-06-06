package redpencil

class RedPencilService {
	def evaluate(p:Product):RedPencilPromotion={
		if (p == null) throw new IllegalArgumentException("Product Required")
		if (p.price == 0.00) throw new IllegalArgumentException("Price Required")

		def priceChanged(price:Double) = p.price <= price * 0.95 && p.price >= price * 0.70
		 
		var lastChange = p.priceHistory.find(_!=null)
		if (lastChange.isDefined && priceChanged(lastChange.get.price) ) return RedPencilPromotion(true)
		return RedPencilPromotion(false)
	}
}

class Product (val price:Double=0.00, val priceHistory:List[PriceHistory]=List())
class PriceHistory(val price:Double=0.00)
case class RedPencilPromotion(val active:Boolean)