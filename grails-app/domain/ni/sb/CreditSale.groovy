package ni.sb

class CreditSale extends Sale {
    Employee employee
    Boolean paidOut = false

    static constraints = {
        invoiceNumber blank: false, unique: true
    }

    List<Payment> payments
    static hasMany = [payments: Payment]

    String getCreditSaleDescription() {
        "#$invoiceNumber ${employee.fullName} ${employee.company.name}"
    }
}
