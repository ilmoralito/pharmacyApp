package ni.sb

import groovy.transform.ToString

@ToString
class BrandProductOrder extends Item {
    Brand brand
    Detail detail

    static constraints = {
        brand()
        detail()
    }

    String getFullName() {
        "$product.name $brand.name $detail.name"
    }
}
