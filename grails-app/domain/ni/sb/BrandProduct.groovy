package ni.sb

import groovy.transform.ToString

@ToString
class BrandProduct extends Product {
    List<Brand> brands

    static constraints = {
        brands nullable: false, minSize: 1
    }

    static hasMany = [brands: Brand]

    static mapping = {
        sort "name"
    }
}
