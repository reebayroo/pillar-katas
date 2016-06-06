package redpencil

class RedPencilService {
	def evaluate(p:Product):RedPencilPromotion={
		if (p == null) throw new IllegalArgumentException("Product Required")
		if (p.price == 0.00) throw new IllegalArgumentException("Price Required")
		var history = p.priceHistory.find(_!=null)
		if (history.isDefined && ( history.get.price * 0.95 >= p.price)) return RedPencilPromotion(true)
		return RedPencilPromotion(false)
	}
}

class Product (val price:Double=0.00, val priceHistory:List[PriceHistory]=List())
class PriceHistory(val price:Double=0.00)
case class RedPencilPromotion(val active:Boolean)