package ni.sb

class PurchaseOrder implements Serializable {
    Distributor distributor
    User user
    String paymentType = "credit"
    String invoiceNumber
    Date deadline
    BigDecimal balance
    Boolean enabled = true
    String paymentStatus

    Date dateCreated
    Date lastUpdated

    static constraints = {
        paymentType inList: ["credit", "cash"], maxSize: 255
        invoiceNumber blank: false, unique: true
        deadline nullable: true, validator: { deadline, obj ->
            if (obj.paymentType == "credit") {
                Date today = new Date()

                deadline >= today
            }
        }
        balance minSize: 1.0
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
