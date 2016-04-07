package ni.sb

class MedicineOrder extends Item {
    Presentation presentation 
    Measure measure
    Date dueDate

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
        dueDate nullable: false, validator: { dueDate ->
            Date date = new Date() + 60

            if (dueDate < date) {
                "notValid"
            }
        }
    }

    String toString() { product.name }
}
