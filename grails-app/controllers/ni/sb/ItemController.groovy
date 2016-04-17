package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class ItemController {
    static defaultAction = "itemStock"
    static allowedMethods = [
        itemStock: "GET",
        medicineStock: "GET",
        brandProductStock: "GET"
    ]

    def itemStock() {
        List<Item> items = Item.list().findAll { item ->
            !(item instanceof MedicineOrder) && !(item instanceof BrandProductOrder)
        }
        
        List data = items.groupBy { it.product.provider }.collect { a ->
            [
                labName: a.key,
                labProducts: a.value.collect { b ->
                    [
                        name: b,
                        quantity: b.quantity
                    ]
                }
            ]
        }

        [items: data]
    }

    def medicineStock() {
        List medicines = MedicineOrder.list().groupBy { it.product.provider }.collect { a ->
            [
                labName: a.key,
                labProducts: a.value.collect { b ->
                    [
                        name: b,
                        presentation: b.presentation,
                        measure: b.measure,
                        quantity: b.quantity
                    ]
                }
            ]
        }

        [medicines: medicines]
    }

    def brandProductStock() {
        List brandProducts = BrandProductOrder.list().groupBy { it.product.provider }.collect { a ->
            [
                labName: a.key,
                labProducts: a.value.collect { b ->
                    [
                        name: b,
                        brand: b.brand,
                        detail: b.detail,
                        quantity: b.quantity
                    ]
                }
            ]
        }

        [brandProducts: brandProducts]
    }
}
