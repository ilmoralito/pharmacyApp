package ni.sb

class CreditSale extends Sale {
    String invoiceNumber
    Employee employee

    static constraints = {
        invoiceNumber blank: false, unique: true
    }
}
