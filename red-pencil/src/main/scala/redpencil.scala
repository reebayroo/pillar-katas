package redpencil

class RedPencilService {
	def evaluate(p:Product) {
		if (p == null) throw new IllegalArgumentException("Product Required")
	}
}

class Product {

}