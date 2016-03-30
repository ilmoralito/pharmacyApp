package ni.sb

class PurchaseOrder implements Serializable {
    Distributor distributor
    String typeOfPurchase
    String invoiceNumber
    Date deadline
    BigDecimal balance
    Boolean enabled

    Date dateCreated
    Date lastUpdated

    static constraints = {

    }

    static mapping = {
        sort dateCreated: "desc"
        items cascade: "all-delete-orphan"
    }

    List<Item> items
    static hasMany = [items: Item]

    String toString() { invoiceNumber }
}
