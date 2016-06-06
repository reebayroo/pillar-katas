package redpencil

class RedPencilService {
	def evaluate(p:Product):RedPencilPromotion={
		if (p == null) throw new IllegalArgumentException("Product Required")
		if (p.price == 0.00) throw new IllegalArgumentException("Price Required")
		return RedPencilPromotion(false)
	}
}

class Product (val price:Double=0.00)

case class RedPencilPromotion(val active:Boolean)