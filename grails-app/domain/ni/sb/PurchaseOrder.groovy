package ni.sb

class PurchaseOrder implements Serializable {
    Distributor distributor
    User user
    String invoiceNumber
    String paymentType = "credit"
    Date paymentDate
    String paymentStatus
    Boolean enabled = true

    Date dateCreated
    Date lastUpdated

    static constraints = {
        invoiceNumber blank: false, unique: true
        paymentType inList: ["credit", "cash"], maxSize: 255
        paymentDate nullable: true, validator: { paymentDate, obj ->
            println paymentDate?.class?.name

            if (obj.paymentType == "credit") {
                Date today = new Date().clearTime()

                paymentDate >= today
            }
        }
        paymentStatus inList: ["pending", "paid"]
        items nullable: false, minSize: 1
    }

    static mapping = {
        sort dateCreated: "desc"
        items cascade: "all-delete-orphan"
    }

    List<Item> items
    static hasMany = [items: Item]

    def beforeValidate() {
        paymentStatus = paymentType == "credit" ? "pending" : "paid"
    }

    String toString() { invoiceNumber }
}
