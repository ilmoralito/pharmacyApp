package ni.sb

class BrandProductOrder extends Item {
    Brand brand
    Detail detail

    static constraints = {
        brand()
        detail()
    }

    String toString() { product }
}
