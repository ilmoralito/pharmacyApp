package ni.sb

import groovy.transform.ToString

@ToString
class MedicineOrder extends Item {
    Presentation presentation 
    Measure measure
    Date dueDate

    static constraints = {
        /*
        presentation validator: { presentation, item ->
            if (!item.product.presentations.contains(presentation)) {
                "notPresentationInSelectedProduct"
            }
        }
        measure validator: { measure, item ->
            if (!item.presentation.measures.contains(measure)) {
                "notMeasureInSelectedPresentation"
            }
        }
        */
        dueDate validator: { dueDate ->
            Date date = new Date() + 60

            if (dueDate < date) {
                "notValid"
            }
        }
    }
}
