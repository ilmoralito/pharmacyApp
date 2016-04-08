package ni.sb

class BrandProductOrder extends Item implements Serializable {
    Brand brand
    String detail

    static constraints = {
        brand nullable: false
        detail blank: false
    }

    static mapping = {
        brand fetch: 'join'
    }

    String toString() { product.name }
}
