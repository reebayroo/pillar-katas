package redpencil

class RedPencilService {
	def evaluate(p:Product) {
		if (p == null) throw new IllegalArgumentException("Product Required")
		if (p.price == 0.00) throw new IllegalArgumentException("Price Required")
	}
}

class Product (val price:Double=0.00)