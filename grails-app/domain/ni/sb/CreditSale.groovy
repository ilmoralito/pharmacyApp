package ni.sb

class CreditSale extends Sale {
    String invoiceNumber
    Employee employee
    List<Payment> payments
    Boolean canceled = false

    static constraints = {
        invoiceNumber blank: false, unique: true
    }

    static hasMany = [payments: Payment]
}
