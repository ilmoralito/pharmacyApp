package ni.sb

class PurchaseOrder implements Serializable {
    Distributor distributor
    String typeOfPurchase
    String invoiceNumber
    Date deadline
    BigDecimal balance
    Boolean enabled = true
    String paymentStatus
    User user

    Date dateCreated
    Date lastUpdated

    static constraints = {
        distributor()
        typeOfPurchase inListL: ["credit", "cash"], maxSize: "255"
        invoiceNumber()
        deadline validator: { deadline ->
            Date today = new Date()

            deadline >= today
        }
        balance(), minSize: 1.0
        paymentStatus inList: ["pending", "paid"]
        user()
    }

    static mapping = {
        sort dateCreated: "desc"
        items cascade: "all-delete-orphan"
    }

    List<Item> items
    static hasMany = [items: Item]

    String toString() { invoiceNumber }
}
