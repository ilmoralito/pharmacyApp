package ni.sb

import org.hibernate.transform.AliasToEntityMapResultTransformer

class Item implements Serializable {
    Product product
    Integer quantity
    BigDecimal purchasePrice
    BigDecimal sellingPrice

    Date dateCreated
    Date lastUpdated

    static constraints = {
        quantity min: 1
        purchasePrice min: 0.1, scale: 2
        sellingPrice min: 0.1, scale: 2, validator: { sellingPrice, item ->
            if (sellingPrice < item.purchasePrice) {
                "notValid"
            }
        }
    }

    static namedQueries = {
        stock {
            projections {
                groupProperty "product", "product"
                count "product", "count"
                sum "quantity", "quantity"
            }

            order("count", "desc")
            resultTransformer(AliasToEntityMapResultTransformer.INSTANCE)
        }
    }

    static belongsTo = [purchaseOrder: PurchaseOrder]

    String toString() { product.name }
}
