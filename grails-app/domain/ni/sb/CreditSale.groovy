package ni.sb

class CreditSale extends Sale {
    String invoiceNumber
    Employee employee
    Boolean canceled = false

    static constraints = {
        invoiceNumber blank: false, unique: true
    }

    List<Payment> payments
    static hasMany = [payments: Payment]
}
