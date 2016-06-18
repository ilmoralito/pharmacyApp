package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN", "ROLE_USER"])
class PaymentController {
    def springSecurityService
    def creditSaleService

    static allowedMethods = [
        index: "GET",
        create: "POST",
        show: "GET"
    ]

    def index(Long creditSaleId) {
        CreditSale creditSale = CreditSale.get(creditSaleId)

        if (!creditSale) {
            response.sendError 404
        }

        List<Payment> payments = Payment.where {
            creditSale.id == creditSaleId
        }.list(params)

        [
            payments: payments,
            creditSaleDescription: creditSale.getCreditSaleDescription(),
            creditSaleDetail: createCreditSaleDetail(creditSale)
        ]
    }

    def create() {
        CreditSale creditSale = CreditSale.get(params.creditSaleId)

        if (!creditSale) {
            response.sendError 404
        }

        BigDecimal balanceToDate = creditSaleService.getBalanceToDate(creditSale.payments, creditSale.balance)
        BigDecimal totalPayments = creditSaleService.getTotalPayments(creditSale.payments)
        BigDecimal amount = params.double("amount")

        if (creditSale.paidOut) {
            flash.message = "Denegado. Credito cancelado"
            redirect action:  "index", params: params

            return
        }

        if (amount > balanceToDate) {
            flash.message = "Error. Abono es mayor a saldo"
            redirect action:  "index", params: params

            return
        }

        Payment payment = new Payment(
            receiptNumber: params?.receiptNumber,
            amount: amount,
            reference: params?.reference,
            madeBy: params?.madeBy,
            madeByIdentityCard: params?.madeByIdentityCard,
            attendedBy: springSecurityService.currentUser
        )

        creditSale.addToPayments(payment)

        if ((totalPayments + amount) == creditSale.balance) {
            creditSale.paidOut = true
        }

        if (!creditSale.save(flush: true)) {
            creditSale.errors.allErrors.each { error ->
                log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
            }

            flash.bag = creditSale
        }

        flash.message = creditSale.hasErrors() ? "A ocurrido un error" : "Accion concluida"
        redirect action: "index", params: [creditSaleId: params.creditSaleId]
    }

    def show(Long id) {
        Payment payment = Payment.get(id)

        if (!payment) {
            response.sendError 404
        }

        [payment: payment]
    }

    private CreditSaleDetail createCreditSaleDetail(creditSale) {
        new CreditSaleDetail(
            invoiceNumber: creditSale.invoiceNumber,
            balance: creditSale.balance,
            balanceToDate: creditSaleService.getBalanceToDate(creditSale.payments, creditSale.balance),
            paidOut: creditSale.paidOut
        )
    }
}

class CreditSaleDetail {
    String invoiceNumber
    BigDecimal balance
    BigDecimal balanceToDate
    Boolean paidOut
}
