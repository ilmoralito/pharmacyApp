package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN"])
class ItemController {
    static defaultAction = "stock"
    static allowedMethods = [
        stock: ["GET", "POST"]
    ]

    def stock() {
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

        List items = Item.list().findAll { item ->
            !(item instanceof MedicineOrder) && !(item instanceof BrandProductOrder)
        }.groupBy { it.product.provider }.collect { a ->
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

        [items: items, medicines: medicines, brandProducts: brandProducts]
    }
}
