package ni.sb

class MedicineOrder extends Item {
    Presentation presentation 
    String measure
    Date bash

    static constraints = {
        presentation nullable: false, validator: { presentation, item ->
            if (!item.product.presentations.contains(presentation)) {
                "notPresentationInSelectedProduct"
            }
        }
        measure nullable: false, validator: { measure, item ->
            if (!item.presentation.measures.contains(measure)) {
                "notMeasureInSelectedPresentation"
            }
        }
        bash nullable: false, validator: { bash ->
            Date date = new Date() + 60

            if (bash < date) {
                "notValid"
            }
        }
    }

    String toString() { product.name }
}
