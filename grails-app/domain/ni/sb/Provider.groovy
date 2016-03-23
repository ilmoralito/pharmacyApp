package ni.sb

import org.grails.databinding.BindUsing

class Provider implements Serializable {
    @BindUsing({ obj, source ->
        source["name"]?.toLowerCase()?.tokenize(" ")*.capitalize().join(" ")
    })
    String name
    String address
    String telephoneNumber
    // TODO: change status for enabled
    Boolean status = true

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name blank: false, unique: true
        address blank: false, unique: true
        telephoneNumber blank: false, unique: true, validator: { telephoneNumber ->
            if (telephoneNumber.size() != 8 || !telephoneNumber.isNumber()) {
                ["notMatch"]
            }
        }
        products nullable: true
    }

    List<Product> products
    static hasMany = [products: Product]

    static mapping = {
        version false
    }

    String contactInformation() {
        "$address, $telephoneNumber"
    }

    String toString() { name }
}
