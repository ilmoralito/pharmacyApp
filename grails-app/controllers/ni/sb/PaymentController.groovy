package ni.sb

import grails.plugin.springsecurity.annotation.Secured

@Secured(["ROLE_ADMIN", "ROLE_USER"])
class PaymentController {
    def springSecurityService
    def creditSaleService

    static allowedMethods = [
        index: ["GET", "POST"]
    ]

    def index(Long creditSaleId) {
        CreditSale creditSale = CreditSale.get(creditSaleId)

        if (!creditSale) {
            response.sendError 404
        }

        if (request.post) {
            BigDecimal balanceToDate = creditSaleService.getBalanceToDate(creditSale)
            Payment payment = new Payment(
                receiptNumber: params?.receiptNumber,
                amount: params?.amount,
                reference: params?.reference,
                madeBy: params?.madeBy,
                madeByIdentityCard: params?.madeByIdentityCard,
                attendedBy: springSecurityService.currentUser
            )

            creditSale.addToPayments(payment)

            if (!creditSale.save()) {
                creditSale.errors.allErrors.each { error ->
                    log.error "[field: $error.field, defaultMessage: $error.defaultMessage]"
                }

                flash.bag = creditSale
                return [creditSale: creditSale]
            }

            flash.message = creditSale.hasErrors() ? "A ocurrido un error" : "Accion concluida correctamente"
        }

        [creditSale: creditSale, creditSaleDetail: createCreditSaleDetail(creditSale)]
    }

    private CreditSaleDetail createCreditSaleDetail(creditSale) {
        new CreditSaleDetail(
            invoiceNumber: creditSale.invoiceNumber,
            balanceToDate: creditSaleService.getBalanceToDate(creditSale)
        )
    }
}

class CreditSaleDetail {
    String invoiceNumber
    BigDecimal balanceToDate
}
