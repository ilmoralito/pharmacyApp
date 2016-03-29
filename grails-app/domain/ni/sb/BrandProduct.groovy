package ni.sb

class BrandProduct extends Product {
    static constraints = {
        brands nullable: false, minSize: 1
    }

    List<Brand> brands
    static hasMany = [brands: Brand]

    String toString() { name }
}
