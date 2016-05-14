package ni.sb

class CashSale extends Sale {
    Client client
    BigDecimal moneyReceived

    static constraints = {
        moneyReceived min: 1.0, scale: 2, validator: { moneyReceived, sale ->
            moneyReceived >= sale.balance
        }
    }
}
